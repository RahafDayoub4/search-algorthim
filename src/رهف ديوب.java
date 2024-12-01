import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize the game
        char[][] grid = {
        {'1', '1', '1','1','1','1','1'},
        {'0', '0', '0','0','1','1','1'},
        {'0', '0', '0','0','0','1','1'},
        {'0', '0', '0','0','0','0','0'},
        };
    //     char [][]grid ={
    //   {'0','0','0'},
    //   {'0','1','0'},{'0','0','0'},
    //     };
        ArrayList<Player> players =new ArrayList<>(
           
        );
        // players.add(new Player(0, 0, 1, 0));
        // players.add( new Player(4,5,2,1)
    //    );
       players.add( new Player(4,6,1,0)
       );
       players.add( new Player(4,5,2,2)
       );
        Game game = new Game(grid, players, grid.length, grid[0].length);

        Scanner scanner = new Scanner(System.in);
        // while (true) {
        //     System.out.println("\nCurrent State:");
        //     game.printGrid(game.grid, game.players);
    
        //     System.out.println("\nPossible States after move:");
        //     System.out.println("somthing1");
        //     try{
        // java.util.List <Game> possibleStates =   game.generatePossibleMoves();
        // System.out.println("somthing1");
            
            
        //     for (Game state : possibleStates) {
        //         System.out.println("somthing1");
        //         game.printGrid(state.grid, state.players);
        //         System.out.println("somthing1");
        //         System.out.println();
        //     }
        // }catch (Exception e) {
        //     e.printStackTrace();
        // }

        //     System.out.print("Enter direction (UP, DOWN, LEFT, RIGHT), or 'quit' to stop: ");
        //     String dir = scanner.nextLine().trim().toUpperCase();
    
        //     if ("QUIT".equals(dir)) {
        //         break;
        //     }
    
        //     // Process the move
        //     Game newState = game.canMove(dir);
        //     Game test = newState.parent; 
        //     System.out.println("parent ");
        //     test.printGrid(test.grid, test.players);
        //     // // Update the game state
        //     game.grid = newState.grid;
        //     game.players = newState.players;
        //     // game.check();
        //     if (game.players.isEmpty()){
        //         break;
        //     }
        // }


          Algorthims pathFinder = new Algorthims(game.grid);

        // Call BFS
        
        ArrayList<Game> path = pathFinder.BFS(game);
        
        if (path != null) {
            System.out.println("Found path to goal!");
            System.out.println("Path length: " + path.size());

            // Print the path
            for (int i = 0; i < path.size(); i++) {
                System.out.println("\nStep " + (i + 1) + ":");
                path.get(i).printGrid(path.get(i).grid, path.get(i).players);
            }
        } else {
            System.out.println("No path found to goal.");
        }
    }
}

//game class
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
//player class
import java.lang.annotation.Target;

import javax.swing.text.Position;

public class Player {
    private int currentX ,currentY;
    private char value; 
    private boolean filled; 
    private int xTar ;
    private int yTar;
    public Player(int x, int y, int xTar, int yTar) {
        this.currentX =x;
        this.currentY =y ;
        this.xTar = xTar;
        this.yTar = yTar;
        this.filled = true; // Default is not filled
    }
    public void setPosition(int x ,int y){
        this.currentX = x;
        this.currentY = y;
    }
    public void setX(int x){
         currentX = x;
    }
    public void setY(int y){
        currentY = y;
   }
   public void setXTar(int x){
    xTar = x;
}
public void setTarY(int y){
   yTar = y;
}
    public int getX(){
        return currentX;
    }
    public int getY(){
        return currentY;
    }
    public int getxTarget() {
        return xTar;
    }
    public int getyTarget() {
        return yTar;
    }
    public char getValue() {
        return value;
    }

    
}
//algorithm class
import java.util.*;

public class Algorthims {
    private char[][] grid;
    private int rows;
    private int cols;

    public Algorthims(char[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
    }

    public ArrayList<Game> BFS(Game root) {
        Set<Game> visitedGames = new HashSet<>();
        Queue<Game> queue = new ArrayDeque<>();
    
        root.setParent(null);
        queue.add(root);
        visitedGames.add(root); 
    
        while (!queue.isEmpty()) {
            Game currentGame = queue.poll();
            List<Game> moves = currentGame.generatePossibleMoves();
    
            for (Game nextGame : moves) {
                if (nextGame == null) continue;
    
                
                if (visitedGames.contains(nextGame)) {
                    continue;
                }
    
                queue.add(nextGame);
                visitedGames.add(nextGame);
    
               
                if (checkGoal(nextGame)) {
                    ArrayList<Game> path = new ArrayList<>();
                    Game current = nextGame;
    
                    
                    while (current != null) {
                        path.add(current);
                        current = current.getParent();
                    }
                    Collections.reverse(path); 
                    System.out.println("Goal reached!");
                    return path;
                }
            }
        }
    
        return null; 
    }
    
    
    
    public ArrayList<Game> DFS(Game root) {
        Set<Game> visitedGames = new HashSet<>();
        Stack<Game> stack = new Stack<>();
    
        root.setParent(null);
        stack.push(root);
        visitedGames.add(root);
        
        while (!stack.isEmpty()) {
           
            Game currentGame = stack.pop();
            List<Game> moves = currentGame.generatePossibleMoves();
    
            for (Game nextGame : moves) {
               
                if (nextGame == null) continue;
    
                if (visitedGames.contains(nextGame)) {
                    continue;
                }
    
                stack.push(nextGame);
                visitedGames.add(nextGame);
    
                if (checkGoal(nextGame)) {
                    ArrayList<Game> path = new ArrayList<>();
                    Game current = nextGame;
    
                    while (current != null) {
                        path.add(current);
                        current = current.getParent();
                    }
                    Collections.reverse(path);
                    System.out.println("Goal reached!");
                    return path;
                }
            }
        }
    
        return null; 
    }
    // rrecursive
    public ArrayList<Game> RDFS(Game root) {
        return dfsHelper(root, new HashSet<>());
    }
    
    private ArrayList<Game> dfsHelper(Game current, Set<Game> visited) {
        if (checkGoal(current)) {
            ArrayList<Game> path = new ArrayList<>();
            Game node = current;
            while (node != null) {
                path.add(node);
                node = node.getParent();
            }
            Collections.reverse(path);
            System.out.println("Goal reached!");
            return path;
        }
    
        if (visited.contains(current)) {
            return null;
        }
    
        visited.add(current);
    
        List<Game> moves = current.generatePossibleMoves();
        for (Game next : moves) {
            if (next != null) {
                ArrayList<Game> result = dfsHelper(next, visited);
                if (result != null) {
                    return result;
                }
            }
        }
    
        return null;
    }
    public ArrayList<Game> UCS(Game root) {
        PriorityQueue<Game> pq = new PriorityQueue<>((a, b) -> Double.compare(a.cost, b.cost));
        Map<Game, Game> visitedParents = new HashMap<>();
        
        
        root.cost = 0;
        pq.offer(root);

        while (!pq.isEmpty()) {
            Game currentNode = pq.poll();
            
          
            if (checkGoal(currentNode)) {
                return reconstructPath(visitedParents, root, currentNode);
            }

            List<Game> moves = currentNode.generatePossibleMoves();

            for (Game nextState : moves) {
                if (nextState == null) continue;

                int totalCost = currentNode.cost + nextState.cost;
                
                if (!visitedParents.containsKey(nextState) || totalCost < nextState.cost) {
                    nextState.cost = totalCost;
                    visitedParents.put(nextState, currentNode);
                    pq.offer(nextState);
                }
            }
        }

        return null; 
    }

    private ArrayList<Game> reconstructPath(Map<Game, Game> visitedParents, Game start, Game goal) {
        ArrayList<Game> path = new ArrayList<>();
        Game current = goal;
        while (current != null) {
            path.add(current);
            current = visitedParents.get(current);
        }
        Collections.reverse(path);
        return path;
    }
    
    private boolean checkGoal(Game game) {
        
        for (Player player : game.players) {
            if (player.getX() != player.getxTarget() || player.getY() != player.getyTarget()) {
                return false;
            }
        }
        return true;
    }

    // Helper method to print the path (optional for visualization)
    private ArrayList<Game> printPath(ArrayList<Game> visitedGames, ArrayList<Game> path) {
        // Optionally print or visualize the path
        System.out.println("Printing the path...");
        for (Game state : path) {
            // You can visualize each game's grid state here if needed
            state.printGrid(state.grid, state.players);
        }
        return path;
    }

}
