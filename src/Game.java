import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    protected List<Player> players;
    protected char[][] grid;
    private int rows;
    private int cols;
    public Game parent;
    public int cost; 
    public Game() {}

    public Game(char[][] grid, List<Player> players, int rows, int cols) {
        this.grid = grid;
        this.players = players;
        this.rows = rows;
        this.cols = cols;
        this.parent= null;
        this.cost = 1;
    }

    public Game canMove(String dir) {
        Game state = new Game();
        char[][] newGrid = new char[rows][cols];
        
        // Copy the grid state
        for (int i = 0; i < rows; i++) {
            System.arraycopy(grid[i], 0, newGrid[i], 0, cols);
        }

        ArrayList<Player> newPlayers = new ArrayList<>();
        
        // Create new player list by copying original players
        for (Player p : players) {
            newPlayers.add(new Player(p.getX(), p.getY(), p.getxTarget(), p.getyTarget()));
        }

        state.cols = cols;
        state.rows = rows;
        state.grid = newGrid;
        state.players = newPlayers;

        for (Player player : newPlayers) {
            int newX = player.getX();
            int newY = player.getY();
            boolean canMove = true;
            while (canMove) {
                int tempX = newX;
                int tempY = newY;

                switch (dir) {
                    case "UP":
                        tempX--;
                        break;
                    case "DOWN":
                        tempX++;
                        break;
                    case "LEFT":
                        tempY--;
                        break;
                    case "RIGHT":
                        tempY++;
                        break;
                }
                if (isValidMove(tempX, tempY)) {
                    newX = tempX;
                    newY = tempY; 
                    parent = (this);
                } else {
                    canMove = false;
                }
            }
           

            player.setX(newX);
            player.setY(newY);
        }

        return state;
    } 
    public void setParent(Game parent) {
        this.parent = parent;
    }

    public Game getParent() {
        return this.parent;
    }

    private boolean isValidMove(int x, int y) {
        
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) return false; 
        if (grid[x][y] == '1') return false; // Wall or invalid cell
        // Check if another player is already at the position
        for (Player otherPlayer : players) {
            if (otherPlayer.getX() == x && otherPlayer.getY() == y) return false; // Another player is at the position
        }
        for (Player player : players) {
           
            if (player.getX() == x && player.getY() == y && player.getX() == player.getxTarget() && player.getY() == player.getyTarget()) {
                // Player has reached its target, call check()
                System.out.println(x);
                System.out.println(y);
                playerReachedTarget(player);
                playersToRemove.add(player);
                return true; // Valid move
            }
        }
        
        playersToRemove.removeAll(players);
        return true;
    }
    
    public List<Game> generatePossibleMoves() {
        List<Game> moves = new ArrayList<>();
        for (String direction : Arrays.asList("UP", "DOWN", "LEFT", "RIGHT")) {
            Game possibleState = canMove(direction);
            // Only add the state if the player actually moved
            if (!isSameState(possibleState)) {
                possibleState.cost +=1;
                moves.add(possibleState);
            }
        }
        return moves;
    }

    private boolean isSameState(Game possibleState) {
        
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getX() != possibleState.players.get(i).getX() || 
                players.get(i).getY() != possibleState.players.get(i).getY()) {
                return false; 
            }
        }
        return true; 
    }
    ArrayList<Player> playersToRemove = new ArrayList<>();
    public void check() {
        System.out.println("Checking winning condition...");
       // ArrayList<Player> playersToRemove = new ArrayList<>();
        for (Player player : players) {
            System.out.println("Checking player at (" + player.getX() + ", " + player.getY() + ")");
            if (player.getX() == player.getxTarget() && player.getY() == player.getyTarget()) {
                System.out.println("Player reached target!");
                playerReachedTarget(player);
                playersToRemove.add(player);
            }
        }
        System.out.println("Removing " + playersToRemove.size() + " players");
        players.removeAll(playersToRemove);
    
        boolean allReached = checkAllPlayersReachedTargets();
        System.out.println("All players reached targets: " + allReached);
    
        if (allReached) {
            handleWinCondition();
        } else {
            System.out.println("No win condition met.");
        }
    }

    private void playerReachedTarget(Player player) {
        int xtarget = player.getxTarget();
        int ytarget = player.getyTarget();
        grid[xtarget][ytarget] = '0'; // Mark target as reached
    }

    protected boolean checkAllPlayersReachedTargets() {
        for (Player player : players) {
            if (player.getX() != player.getxTarget() || player.getY() != player.getyTarget()) {
                return false;
            }
        }
        return players.isEmpty();
    }

    private void handleWinCondition() {
        System.out.println("All players have reached their targets! You win!");
    }

    // Print the possible states after the move
    public void printPossibleStates() {
        List<Game> possibleStates = generatePossibleMoves();

        System.out.println("Current State:");
        printGrid(grid, players); // Print the current state

        System.out.println("\nPossible States after move:");
        for (Game state : possibleStates) {
            printGrid(state.grid, state.players);
            System.out.println();
        }
    }

    public void printGrid(char[][] grid, List<Player> players) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Print the grid with player positions
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean isPlayerCell = false;

                for (Player player : players) {
                    if (player.getX() == i && player.getY() == j) {
                        isPlayerCell = true;
                        break;
                    }
                }

                String cellContent = String.valueOf(grid[i][j]);

                if (isPlayerCell) {
                    System.out.print("\u001B[41m" + cellContent + "\u001B[0m"); // Red background for players
                } else {
                    System.out.print(cellContent + " "); // Normal cell
                }

                System.out.print("| ");
            }
            System.out.println();
        }
    }
}
