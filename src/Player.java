
/** Name: Anupama Bhatta
 *  Date: 05/06/2019
 *  Description: Develop Tic-Tac-Toe game that uses Algorithm Engine Library.
 */

public class Player 
{
    private String labelText;
    private boolean goesFirst;
    private int Id;
    private String name;
    
    public Player(String labelText, boolean goesFirst, int Id, String name)
    {
        this.labelText = labelText;
        this.goesFirst = goesFirst;
        this.Id = Id;
        this.name = name;
    }
    
    public String getLabelText()
    {
        return labelText;
    }

    public boolean isGoesFirst() 
    {
        return goesFirst;
    }

    public int getId() 
    {
        return Id;
    }

    public String getName() 
    {
        return name;
    }
    
}
