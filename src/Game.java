import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    protected List<Player> players;
    protected char[][] grid;
    private int rows;
    private int cols;
    public Game parent;
    public int cost; 
    boolean win = false;
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
        state.parent = (this);
        for (Player player : newPlayers) {
            int newX = player.getX();
            int newY = player.getY();
            int xtar = player.getxTarget();
            int ytar  = player.getyTarget();
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
                if (isValidMove(tempX, tempY,xtar,ytar)) {
                    newX = tempX;
                    newY = tempY; 
                    if (newX == xtar && newY== ytar){
                        canMove = false;
                    }
                    
                } else {
                    canMove = false;
                }
            }
           

            player.setX(newX);
            player.setY(newY);
           
            if (check()) {
                win = true;
                canMove =false; 
            
        }
        }

       
        if (win) {
            System.out.println("Player has won!");
            return state; // Return the new state indicating a win
        }
        
        

        return state;
    } 
    public void setParent(Game parent) {
        this.parent = parent;
    }

    public Game getParent() {
        return this.parent;
    }

    private boolean isValidMove(int x, int y,int xtar, int ytar) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) return false;
        
        if (grid[x][y] == '1') return false; 
       
        for (Player otherPlayer : players) {
            if (otherPlayer.getX() == x && otherPlayer.getY() == y) return false; 
        }
     
        return true; 
    }
    
    public List<Game> generatePossibleMoves() {
        List<Game> moves = new ArrayList<>();
        if (players.isEmpty()){
            System.out.println("no players");
            return moves ;
        }
      else{
        for (String direction : Arrays.asList("UP", "DOWN", "LEFT", "RIGHT")) {
            Game possibleState = canMove(direction);
           
            if (!isSameState(possibleState)) {
                
                moves.add(possibleState);
            }
        }
       
        return moves;
    }
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
    
    public boolean check() {
        // ArrayList<Player> playersToRemove = new ArrayList<>();
        if (players.isEmpty()) {
            handleWinCondition();
            return true;
        }
        
        List<Player> playersToRemove = new ArrayList<>();
        for (Player player : new ArrayList<>(players)) {
            if (player.getX() == player.getxTarget() && player.getY() == player.getyTarget()) {
                System.out.println("Player reached target!");
                playersToRemove.add(player);
            }
        }
        
        players.removeAll(playersToRemove);
        
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
    
        // Print the grid without colors
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (hasPlayerAtPosition(players, i, j)) {
                    System.out.print("\u001B[43m" + grid[i][j] + "\u001B[0m"); // Cyan background for players
                } else {
                    System.out.print(grid[i][j] + " ");
                }
                System.out.print("| ");
            }
            System.out.println();
        }
    }
    
    private boolean hasPlayerAtPosition(List<Player> players, int x, int y) {
        for (Player player : players) {
            if (player.getX() == x && player.getY() == y) {
                return true;
            }
        }
        return false;
    }
}