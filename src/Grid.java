import java.awt.List;
import java.util.ArrayList;


public class Grid {
    
    private char[][] grid;
    
    private int rows;
    private int cols;
    
    public Grid(char[][] predefinedGrid ) {
        
        this.rows = predefinedGrid.length;
        this.cols = predefinedGrid[0].length;
        this.grid = predefinedGrid;
    }
   
    public int getGridSize(){
        return grid.length;
    }
    
    public char getCell(int row, int col) {
        return grid[row][col];
    }
    public void setCell(int x, int y, char value) {
        grid[x][y] = value; 
    }
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
    public char[][] getGrid(){
        return grid;
    }
}
