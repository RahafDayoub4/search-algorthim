// import java.util.List;
// import java.util.ArrayList;

// public class State {
//     private List<Player> players;
//     private char[][] grid; // Representation of the grid

//     public State(List<Player> players, char [][] grid) {
//         this.players = new ArrayList<>(players); // Create a copy of the players
//         this.grid = grid; // Copy grid or reference, depending on your needs
//     }

//     public List<Player> getPlayers() {
//         return players;
//     }

//     public char[][] getGrid() {
//         return grid;
//     }

    
//     @Override
//     public boolean equals(Object obj) {
//         if (this == obj) return true;
//         if (!(obj instanceof State)) return false;
//         State other = (State) obj;

//         // Compare players
//         if (!this.players.equals(other.players)) return false;

//         // Compare grid
//         for (int i = 0; i < grid.length; i++) {
//             for (int j = 0; j <grid[0].length; j++) {
//                 if (this.grid[i][j] != other.grid[i][j]) return false;
//             }
//         }
//         return true;
//     }
//     // public char[][] printGrid(){
//     //     for (int i =0 , i<grid.length ,i++){
//     //         for (int j =0 ; )
//     //     }
//     // }
//     @Override
//     public int hashCode() {
//         int result = players.hashCode();
//         for (char[] row : grid) {
//             for (char cell : row) {
//                 result = 31 * result + cell;
//             }
//         }
//         return result;
//     }
// }
