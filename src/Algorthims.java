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
        ArrayList<Game> visitedGames = new ArrayList();
        Queue<Game> queue = new ArrayDeque<>();
        
        root.setParent(null);
        queue.add(root);
        visitedGames.add(root);
        
         while (!queue.isEmpty()) {
            Game currentGame = queue.poll();
           List<Game> moves = currentGame.generatePossibleMoves();
         
            for (Game nextGame :moves) {
            
                if (nextGame == null) continue;
                 
                if (visitedGames.contains(nextGame)) {
                    continue;
                }

                queue.add(nextGame);
                visitedGames.add(nextGame);
    
                // No need to set parent here, it was done in generatePossibleMoves
    
                if (checkGoal(nextGame)) {
                    
                    ArrayList<Game> path = new ArrayList<>();
                    Game current = nextGame;
                    while (current != null) {
                        System.out.println("bfs");
                        path.add(current);
                        current = current.getParent();
                    }
                    Collections.reverse(path);
    
                    System.out.println("Goal reached!");
                //    ArrayList <Game> foit = printPath(visitedGames, path);
                //    for(Game game: foit){
                //        game.printGrid(game.grid, game.players);
                //    }
                    return path;
                }
            }
         }
    
        return null;
    }
    
    public ArrayList<Game> UCS(Game root) {
        PriorityQueue<Game> pq = new PriorityQueue();
        Set<Game> visitedGames = new HashSet<>();
        Queue<Game> queue = new ArrayDeque<>();
        
        root.setParent(null);
        pq.offer(root);
        visitedGames.add(root);
        
         while (!pq.isEmpty()) {
            Game currentGame = pq.poll();
           List<Game> moves = currentGame.generatePossibleMoves();
         
            for (Game nextGame :moves) {
            
                if (nextGame == null) continue;
                if (nextGame.cost > pq.size()  ){
                          continue;
                }
                if (visitedGames.contains(nextGame)) {
                    continue;
                }

                queue.add(nextGame);
                visitedGames.add(nextGame);
    
                // No need to set parent here, it was done in generatePossibleMoves
    
                if (checkGoal(nextGame)) {
                    
                    ArrayList<Game> path = new ArrayList<>();
                    Game current = nextGame;
                    while (current != null) {
                        System.out.println("bfs");
                        path.add(current);
                        current = current.getParent();
                    }
                    Collections.reverse(path);
    
                    System.out.println("Goal reached!");
                //    ArrayList <Game> foit = printPath(visitedGames, path);
                //    for(Game game: foit){
                //        game.printGrid(game.grid, game.players);
                //    }
                    return path;
                }
            }
         }
    
        return null;
    }
    

    // Method to check if the goal state is reached
    private boolean checkGoal(Game game) {
        // Check if all players have reached their targets
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
