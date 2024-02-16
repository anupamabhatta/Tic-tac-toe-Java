
import AlgorithmPackage.Algorithm;
import AlgorithmPackage.ICellable;
import java.util.ArrayList;


/** Name: Anupama Bhatta
 *  Date: 05/06/2019
 *  Description: Develop Tic-Tac-Toe game that uses Algorithm Engine Library.
 */

public class Grid 
{
    private ICellable[][] grid;
    private Algorithm algo;
    
        public Grid(int size, int difficultyLevel)
        {
            grid = new Cell[size][size];

            Algorithm.DIFFICULTY level = Algorithm.DIFFICULTY.EASY;
            
            if (difficultyLevel==2)
                level = Algorithm.DIFFICULTY.MEDIUM;
            if (difficultyLevel==3)
                level = Algorithm.DIFFICULTY.HARD;
            
            algo = new Algorithm(grid, level);                 
        }
        
        public void addCell(Cell cell)
        {
            grid[cell.getRow()][cell.getCol()] = cell;
        }
        
        public boolean isGameOver()
        {
            return algo.isGameOver();
        }
        
        public boolean checkWinner(int ownerID)
        {
            return algo.checkWinner(ownerID);
        }
         
        public Cell nextMove(Player player)
        {
            ICellable nextCell = algo.MakeMove(player.getId());
            if (nextCell==null)
            {
                String sErr = "";
            }
            
            Cell cell = getCellFromCellable(nextCell, player);

            return cell;
        } 
        
        private Cell getCellFromCellable(ICellable cell, Player player)
        {
            int iRow = cell.getRow();
            int iCol = cell.getCol();
            Cell c = (Cell)grid[iRow][iCol];
            c.setOwner(player);
            
            return c;
        }
        
        public int getWinNumber()
        {
            return algo.getNumberToWin();
        }  
        
        public ArrayList<ICellable> getWinningCells()
        {
            return algo.getWinningCells();
        }
        
        public void setCellOwner(Cell cell, Player player)
        {
            Cell c = getCellFromCellable(cell, player);
            algo.RegisterMove(cell, player.getId()); // register move
        }        
}
