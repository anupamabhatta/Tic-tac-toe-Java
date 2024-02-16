
import AlgorithmPackage.ICellable;
import java.util.Objects;

/** Name: Anupama Bhatta
 *  Date: 05/06/2019
 *  Description: Develop Tic-Tac-Toe game that uses Algorithm Engine Library.
 */

public class Cell implements ICellable
{
    private int row; 
    private int col;
    private int ownerID;
    private Player owner;

    public Cell(int row, int col, Player owner)
    {
        this.row = row;
        this.col = col;
        this.owner = owner;
    }
    
    public Cell(int row, int col)
    {
        this.row = row;
        this.col = col;
    }
    
    @Override
    public int getRow() 
    {
        return row;
    }

    @Override
    public int getCol() 
    {
        return col;
    }

    @Override
    public void setOwnerId(int ownerID) 
    {
        this.ownerID = ownerID;
    }

    @Override
    public int getOwnerId() 
    {
        return ownerID;
    }

    public void setOwner(Player owner) 
    {
        this.owner = owner;
    }

    public Player getOwner() 
    {
        return owner;
    }
    
    @Override
    public boolean isEmpty() 
    {
        return owner == null;
    }

    @Override
    public boolean isTaken() 
    {
        return owner != null;
    }

    @Override
    public int hashCode() 
    {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cell other = (Cell) obj;
        if (this.row != other.row) {
            return false;
        }
        if (this.col != other.col) {
            return false;
        }
        if (this.ownerID != other.ownerID) {
            return false;
        }
        if (!Objects.equals(this.owner, other.owner)) {
            return false;
        }
        return true;
    }
}
