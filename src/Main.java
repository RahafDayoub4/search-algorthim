import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize the game
        // char[][] grid = {
        // {'1', '1', '1','1','1','1','1'},
        // {'0', '0', '0','0','1','1','1'},
        // {'0', '0', '0','0','0','1','1'},
        // {'0', '0', '0','0','0','0','0'},
        // };
        char [][]grid ={
      {'0','0','0'},
      {'0','1','0'},{'0','0','0'},
        };
        ArrayList<Player> players =new ArrayList<>(
           
        );
        players.add( new Player(0,0,0,1)
       );
    //    players.add( new Player(3,5,1,2)
    //    );
        Game game = new Game(grid, players, grid.length, grid[0].length);

        Scanner scanner = new Scanner(System.in);
        // while (true) {
        //     System.out.println("\nCurrent State:");
        //     game.printGrid(game.grid, game.players);
    
        //     System.out.println("\nPossible States after move:");
        // java.util.List <Game> possibleStates =   game.generatePossibleMoves();
    
        //     for (Game state : possibleStates) {
        //         game.printGrid(state.grid, state.players);
        //         System.out.println();
        //     }
    
        //     System.out.print("Enter direction (UP, DOWN, LEFT, RIGHT), or 'quit' to stop: ");
        //     String dir = scanner.nextLine().trim().toUpperCase();
    
        //     if ("QUIT".equals(dir)) {
        //         break;
        //     }
    
        //     // Process the move
        //     Game newState = game.canMove(dir);
    
        //     // Update the game state
        //     game.grid = newState.grid;
        //     game.players = newState.players;
        //     game.check();
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
