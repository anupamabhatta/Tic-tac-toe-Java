
import AlgorithmPackage.ICellable;

/** Name: Anupama Bhatta
 *  Date: 05/06/2019
 *  Description: Develop Tic-Tac-Toe game that uses Algorithm Engine Library.
 */

public class CellIcon extends javax.swing.JLabel 
{    
    public static final int BASE_SIZE = 60;    
    private Cell cell;

        public CellIcon(Cell cell) 
        {
            this.cell = cell;

            this.setBackground(new java.awt.Color(255, 255, 255));
            this.setForeground(new java.awt.Color(112, 71, 140));
            this.setSize(BASE_SIZE, BASE_SIZE);

            this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(112, 71, 140), 3));
            this.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            this.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            this.setFont(new java.awt.Font("Lucida", 1, 30));
        }

        public void setNormalStyle()
        {
            if (cell.isEmpty())
            {
                this.setBackground(new java.awt.Color(240, 240, 240));
                this.setForeground(new java.awt.Color(112, 71, 140));
                this.setText(null);
            }
        }
        
        public void setHoverStyle(String sText)
        {
            if (this.getText() == null || this.getText().equalsIgnoreCase(""))
            {
               this.setBackground(new java.awt.Color(112, 71, 140));
               this.setForeground(new java.awt.Color(255, 255, 255));
               this.setText(sText);
               this.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            }
            else
            {
                this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            }
        }

        public void set(String sText)
        { 
            this.setBackground(new java.awt.Color(255, 255, 255));
            this.setForeground(new java.awt.Color(64, 40, 84));
            this.setText(sText);
        }
        
        public void setOwner(Player owner)
        {
            cell.setOwner(owner);
        }
        
        public Cell getCell()
        {
            return cell;
        }
    
        public int getRow()
        {
            return cell.getRow();
        }
        
        public int getCol()
        {
            return cell.getCol();
        }
        
        public boolean equals(ICellable otherCell)
        {
            if (otherCell == null)
                return false;
            
            return (this.getRow() == otherCell.getRow() && this.getCol() == otherCell.getCol());
        }
}
