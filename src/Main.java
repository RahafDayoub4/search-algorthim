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
