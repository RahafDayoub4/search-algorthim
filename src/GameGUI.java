// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.KeyAdapter;
// import java.awt.event.KeyEvent;
// import java.util.ArrayList;
// import java.util.List;

// public class GameGUI {
//     private JFrame frame;
//     private JButton[][] buttons;
//     private Game game;
//     private int rows;
//     private int cols;
//     ArrayList <Player> players = new ArrayList<>();
    
//     public GameGUI(int numPlayers, char[][] grid) {
//         this.rows = grid.length;
//         this.cols = grid[0].length;
//         this.game = new Game(grid,players,5,7);
//         buttons = new JButton[rows][cols];
//         players.add(new Player(0,0,0,6));
//         frame = new JFrame("Zero Squares Game");
//         frame.setLayout(new GridLayout(rows, cols));
//         initializeButtons();
//         frame.setSize(400, 400);
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setVisible(true);
        
//         // Key listener for player movement
//         frame.addKeyListener(new KeyAdapter() {
//             @Override
//             public void keyPressed(KeyEvent e) {
//                 switch (e.getKeyCode()) {
//                     case KeyEvent.VK_UP:
//                         movePlayers("UP");
//                         if (game.checkAllPlayersReachedTargets()) {
//                             JOptionPane.showMessageDialog(frame, "Target Reached!", "Victory", JOptionPane.INFORMATION_MESSAGE);
                            
//                         }
//                         break;
//                     case KeyEvent.VK_DOWN:
//                         movePlayers("DOWN");
//                         if (game.checkAllPlayersReachedTargets()) {
//                             JOptionPane.showMessageDialog(frame, "Target Reached!", "Victory", JOptionPane.INFORMATION_MESSAGE);
                            
//                         }
//                         break;
//                     case KeyEvent.VK_LEFT:
//                         movePlayers("LEFT");
//                         if (game.checkAllPlayersReachedTargets()) {
//                             JOptionPane.showMessageDialog(frame, "Target Reached!", "Victory", JOptionPane.INFORMATION_MESSAGE);
                            
//                         }
//                         break;
//                     case KeyEvent.VK_RIGHT:
//                         movePlayers("RIGHT");
//                         if (game.checkAllPlayersReachedTargets()) {
//                             JOptionPane.showMessageDialog(frame, "Target Reached!", "Victory", JOptionPane.INFORMATION_MESSAGE);
                            
//                         }
//                         break;
//                 }
                
//                 updateButtons(); 
//             }
//         });
       
//         frame.setFocusable(true);
//         updateButtons();}

//     private void initializeButtons() {
//         for (int i = 0; i < rows; i++) {
//             for (int j = 0; j < cols; j++) {
//                 buttons[i][j] = new JButton();
//                 buttons[i][j].setEnabled(false); // Disable button clicks
//                 frame.add(buttons[i][j]);
//             }
//         }
//     }

//     private void updateButtons() {
//         // Clear the previous state
//         for (int i = 0; i < rows; i++) {
//             for (int j = 0; j < cols; j++) {
//                 buttons[i][j].setText(""); // Clear previous text
//                 buttons[i][j].setBackground(Color.LIGHT_GRAY); // Reset background
//             }
//         }

//         Game currentState = game.currentState();
//         for (Player player : currentState.getPlayers()) {
//             buttons[player.getX()][player.getY()].setBackground(Color.RED);

//         char[][] grid = currentState.newState();
//         for (int i = 0; i < rows; i++) {
//             for (int j = 0; j < cols; j++) {
//                 buttons[i][j].setText(String.valueOf(grid[i][j])); 
//             }
//         }
//     }
//     }
//     private void printPossibleState (){
//         game.printCurrentAndPossibleStates();
//     }

//     private void movePlayers(String direction) {
//         game.canMove(direction); 
//     }
// }