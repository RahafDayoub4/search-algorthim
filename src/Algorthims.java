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
