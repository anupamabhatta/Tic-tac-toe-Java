
import AlgorithmPackage.ICellable;
import AuxPackage.AuxFormatter;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** Name: Anupama Bhatta
 *  Date: 05/06/2019
 *  Description: Develop Tic-Tac-Toe game that uses Algorithm Engine Library.
 */

public class UI_Game extends javax.swing.JFrame 
{
    private boolean userGoesFirst;
    
    private Grid grid;
    private JPanel pnlGrid;
    
    private Player playerUser;
    private Player playerAI;
    
    private int gridSize;
    private int difficultyLevel;
    
    private int aiCounter;
    private int userCounter;
    private int drawCounter;
    
    private final String userName;    

    public UI_Game(int size, String userName, boolean userGoesFirst, int difficultyLevel) 
    {      
        setFormUIDefaults();
        initComponents();
        this.userName = userName;
        this.userGoesFirst = userGoesFirst;
        
        gridSize = size;
        this.difficultyLevel = difficultyLevel;
        
        setPlayers(userName, userGoesFirst);
        
        int formWidth = this.getSize().width;
        lblResult.setSize(formWidth, 150);

        int panelSize = CellIcon.BASE_SIZE * size + 0;
        
        pnlGridContainer.setSize(formWidth, panelSize);
        
        pnlGrid = new JPanel();
        pnlGrid.setLayout(new java.awt.GridLayout(size, size));

        pnlGrid.setSize(panelSize, panelSize);
        pnlGrid.setMaximumSize(new Dimension (panelSize, panelSize));
        pnlGrid.setPreferredSize(new Dimension (panelSize, panelSize));

        initFirstMove(this.gridSize);

        int offset = (int) (formWidth - (double)panelSize)/2;
        pnlGridContainer.add(pnlGrid, new org.netbeans.lib.awtextra.AbsoluteConstraints(offset,0));
        
        this.setLayout(new java.awt.BorderLayout(5, 5));
        this.add(pnlHeader, java.awt.BorderLayout.PAGE_START);
        this.add(pnlLeft, java.awt.BorderLayout.LINE_START);
        this.add(pnlGrid, java.awt.BorderLayout.CENTER);
        this.add(pnlRight, java.awt.BorderLayout.LINE_END);
        this.add(pnlCount, java.awt.BorderLayout.PAGE_END);
        
        String val = "Level\n" + getDifficultyLevel(difficultyLevel);
        val = AuxFormatter.convertToHTML(val);
        lblDifficulty.setText(val);
        val = AuxFormatter.convertToHTML("Win by\n" + grid.getWinNumber());
        lblWinBy.setText(val);       
    }
    
    private void setFormUIDefaults()
    {
        setResizable(false);
        setPosition();
        setAlwaysOnTop(true);
        setBG("/images/WesleyanBG.jpg");
    }
    
    private void setPosition() //put the form in the middle of the screen
    {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
    }
    
    private void setBG(String sImageName)
    {
        setContentPane(new JLabel(new ImageIcon(getClass().getResource(sImageName))));
    }
    
    private void populateGrid(int size, int difficultyLevel)
    {
        pnlGrid.removeAll();
        
        grid = new Grid(size, difficultyLevel);
        
        for (int i=0; i<size; i++)
        {
            for (int n=0; n<size; n++)
            {
                Cell cell = new Cell(i, n);
                grid.addCell(cell);
                
                CellIcon cellIcon = new CellIcon(cell);
                    
                    cellIcon.addMouseListener(new java.awt.event.MouseAdapter()
                    {
                        public void mouseEntered(java.awt.event.MouseEvent evt)
                        {
                            if (!cellIcon.isEnabled())
                                return;
                            cellIcon.setHoverStyle(playerUser.getLabelText());
                        }
                    
                        public void mouseExited(java.awt.event.MouseEvent evt)
                        {
                            if (!cellIcon.isEnabled())
                                return;
                            cellIcon.setNormalStyle();
                        }
                    
                        public void mouseClicked(java.awt.event.MouseEvent evt)
                        {
                            if (!cellIcon.isEnabled())
                                return;

                            cellIcon.set(playerUser.getLabelText());
                            cellIcon.setOwner(playerUser);
                            grid.setCellOwner(cellIcon.getCell(),playerUser);
                            
                            if (!isGameOver())
                            {
                                Cell aiCell = grid.nextMove(playerAI);
                                selectCellForAi(aiCell);
                                isGameOver();
                            }
                    }                   
                });                      
                pnlGrid.add(cellIcon);
            }                    
        }
    }
    
    private String getDifficultyLevel(int diffLevel)
    {
        if (diffLevel==1)
            return "Easy";
        if (diffLevel==2)
            return "Medium";
        if (diffLevel==3)
            return "Hard";
        
        return "";
    }
    
    private void setPlayers(String userName, boolean userGoesFirst)
    {
        this.userGoesFirst = userGoesFirst;
        String userLabel = "";
        String compLabel = "";
        int userOwnerId = 0;
        int aiOwnerId = 0;
        
        if (userGoesFirst)
        {
            userLabel = "x";
            compLabel = "o";
            userOwnerId = 1;
            aiOwnerId = 2;
        }
        else
        {
            userLabel = "o";
            compLabel = "x";
            userOwnerId = 2;
            aiOwnerId = 1;
        }
        
        playerUser = new Player(userLabel, userGoesFirst, userOwnerId, userName);
        playerAI = new Player(compLabel, !userGoesFirst, aiOwnerId, "AI");
        
        lblUserName.setText(userName + "(" + userLabel + ")");
        lblAI.setText("Computer" + "(" + compLabel + ")");
        lblTie.setText("Ties");
        lblUserWinCount.setText("0");
        lblTieCount.setText("0");
        lblAIWinCount.setText("0");
    }
    
    private void initFirstMove(int size)
    {
        populateGrid(size, difficultyLevel);       
        if (!this.userGoesFirst)
        {
            Cell aiCell = grid.nextMove(playerAI);
            selectCellForAi(aiCell);
        }
    }
        
    private void selectCellForAi(Cell cell)
    {
        for (Component cellComponent: pnlGrid.getComponents())
        {
            if (cellComponent instanceof CellIcon)
            {
                CellIcon icon = (CellIcon)cellComponent;
                if (icon.equals(cell))
                {
                    grid.setCellOwner(cell, playerAI);
                    icon.set(playerAI.getLabelText());
                    icon.setForeground(new java.awt.Color(173, 5, 120));
                    this.repaint();
                    this.revalidate();
                    break;
                }
            }
        }
    }
    
    private boolean isGameOver()
    {
        boolean isOver = false;
        boolean aiWon = false;
        boolean userWon = grid.checkWinner(playerUser.getId());
        if (userWon)
        {
            this.userCounter++;
            lblUserWinCount.setText(userCounter + "");
            lblResult.setText(AuxFormatter.convertToHTML(playerUser.getName() + " won!"));
            highlightWinner(grid.getWinningCells(), true);
            isOver = true;
        }
        else
        {
            aiWon = grid.checkWinner(playerAI.getId());
            if (aiWon)
            {
                aiCounter++;
                highlightWinner(grid.getWinningCells(), false);
                lblResult.setText(AuxFormatter.convertToHTML("Computer won!"));
                lblAIWinCount.setText(aiCounter + "");
                isOver = true;
            }                   
        }
        if (!aiWon && !userWon)
        {
            if (grid.isGameOver())
            {
                showDraw();
                isOver = true;
            }
        }  
        return isOver;
    }
    
    public void showDraw()
    {
        for (Component cellComponent: pnlGrid.getComponents())
        {
            ((CellIcon)cellComponent).setEnabled(false);           
        }       
        drawCounter++;
        lblResult.setText(AuxFormatter.convertToHTML("It's a tie!"));
        lblTieCount.setText(String.valueOf(drawCounter));
        
        this.repaint();
        this.revalidate();
    }
    
    public void highlightWinner(ArrayList<ICellable> arWinCells, boolean needUser)
    {
        for (Component cellComponent: pnlGrid.getComponents())
            ((CellIcon)cellComponent).setEnabled(false);        
        for (ICellable cell: arWinCells)
        {
            for (Component cellComponent: pnlGrid.getComponents())
            {
                CellIcon icon = (CellIcon)cellComponent;
                if (icon.equals(cell))
                {
                    if (needUser)
                    {
                        icon.setForeground(new java.awt.Color(85, 162, 85));
                        icon.setBorder(javax.swing.BorderFactory.createLineBorder(Color.GREEN, 3));
                    }
                    else
                    {
                        icon.setForeground(new java.awt.Color(240, 30, 30));
                        icon.setBorder(javax.swing.BorderFactory.createLineBorder(Color.RED, 3));
                    }
                    
                    this.repaint();
                    this.revalidate();
                    break;
                }
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        lblReset = new javax.swing.JLabel();
        lblReplay = new javax.swing.JLabel();
        lblResult = new javax.swing.JLabel();
        pnlLeft = new javax.swing.JPanel();
        lblWinBy = new javax.swing.JLabel();
        pnlRight = new javax.swing.JPanel();
        lblDifficulty = new javax.swing.JLabel();
        pnlCount = new javax.swing.JPanel();
        pnlUserCounts = new javax.swing.JPanel();
        lblUserName = new javax.swing.JLabel();
        lblUserWinCount = new javax.swing.JLabel();
        pnlTieCounts = new javax.swing.JPanel();
        lblTieCount = new javax.swing.JLabel();
        lblTie = new javax.swing.JLabel();
        pnlAICounts = new javax.swing.JPanel();
        lblAIWinCount = new javax.swing.JLabel();
        lblAI = new javax.swing.JLabel();
        pnlNorth = new javax.swing.JPanel();
        pnlSouth = new javax.swing.JPanel();
        pnlGridContainer = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlHeader.setOpaque(false);
        pnlHeader.setPreferredSize(new java.awt.Dimension(663, 100));
        pnlHeader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblReset.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblReset.setForeground(new java.awt.Color(64, 40, 84));
        lblReset.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReset.setText("RESET");
        lblReset.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(64, 40, 84), 2));
        lblReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblResetMouseClicked(evt);
            }
        });
        pnlHeader.add(lblReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 80, 30));

        lblReplay.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblReplay.setForeground(new java.awt.Color(64, 40, 84));
        lblReplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReplay.setText("REPLAY");
        lblReplay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(64, 40, 84), 2));
        lblReplay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblReplayMouseClicked(evt);
            }
        });
        pnlHeader.add(lblReplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, 80, 30));

        lblResult.setFont(new java.awt.Font("Lucida Grande", 1, 25)); // NOI18N
        lblResult.setForeground(new java.awt.Color(64, 40, 84));
        lblResult.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblResult.setText("TIC-TAC-TOE");
        pnlHeader.add(lblResult, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 250, 80));

        getContentPane().add(pnlHeader, java.awt.BorderLayout.PAGE_START);

        pnlLeft.setOpaque(false);

        lblWinBy.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblWinBy.setForeground(new java.awt.Color(64, 40, 84));
        lblWinBy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWinBy.setText("Win Count");

        javax.swing.GroupLayout pnlLeftLayout = new javax.swing.GroupLayout(pnlLeft);
        pnlLeft.setLayout(pnlLeftLayout);
        pnlLeftLayout.setHorizontalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblWinBy, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
        );
        pnlLeftLayout.setVerticalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addComponent(lblWinBy, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 301, Short.MAX_VALUE))
        );

        getContentPane().add(pnlLeft, java.awt.BorderLayout.LINE_START);

        pnlRight.setOpaque(false);

        lblDifficulty.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblDifficulty.setForeground(new java.awt.Color(64, 40, 84));
        lblDifficulty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDifficulty.setText("Difficulty");

        javax.swing.GroupLayout pnlRightLayout = new javax.swing.GroupLayout(pnlRight);
        pnlRight.setLayout(pnlRightLayout);
        pnlRightLayout.setHorizontalGroup(
            pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRightLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDifficulty, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlRightLayout.setVerticalGroup(
            pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRightLayout.createSequentialGroup()
                .addComponent(lblDifficulty, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 301, Short.MAX_VALUE))
        );

        getContentPane().add(pnlRight, java.awt.BorderLayout.LINE_END);

        pnlCount.setOpaque(false);
        pnlCount.setPreferredSize(new java.awt.Dimension(663, 100));
        pnlCount.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlUserCounts.setOpaque(false);

        lblUserName.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblUserName.setForeground(new java.awt.Color(64, 40, 84));
        lblUserName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserName.setText("User Name");

        lblUserWinCount.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblUserWinCount.setForeground(new java.awt.Color(64, 40, 84));
        lblUserWinCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserWinCount.setText("User Count");

        javax.swing.GroupLayout pnlUserCountsLayout = new javax.swing.GroupLayout(pnlUserCounts);
        pnlUserCounts.setLayout(pnlUserCountsLayout);
        pnlUserCountsLayout.setHorizontalGroup(
            pnlUserCountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblUserWinCount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
        );
        pnlUserCountsLayout.setVerticalGroup(
            pnlUserCountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUserCountsLayout.createSequentialGroup()
                .addComponent(lblUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUserWinCount, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlCount.add(pnlUserCounts, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 23, 140, 70));

        pnlTieCounts.setOpaque(false);
        pnlTieCounts.setPreferredSize(new java.awt.Dimension(138, 55));

        lblTieCount.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblTieCount.setForeground(new java.awt.Color(64, 40, 84));
        lblTieCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTieCount.setText("Tie Count");

        lblTie.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblTie.setForeground(new java.awt.Color(64, 40, 84));
        lblTie.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTie.setText("Tie");

        javax.swing.GroupLayout pnlTieCountsLayout = new javax.swing.GroupLayout(pnlTieCounts);
        pnlTieCounts.setLayout(pnlTieCountsLayout);
        pnlTieCountsLayout.setHorizontalGroup(
            pnlTieCountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTieCountsLayout.createSequentialGroup()
                .addGroup(pnlTieCountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTieCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlTieCountsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTie, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlTieCountsLayout.setVerticalGroup(
            pnlTieCountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTieCountsLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(lblTie, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTieCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlCount.add(pnlTieCounts, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, -1, 70));

        pnlAICounts.setOpaque(false);
        pnlAICounts.setPreferredSize(new java.awt.Dimension(138, 58));

        lblAIWinCount.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblAIWinCount.setForeground(new java.awt.Color(64, 40, 84));
        lblAIWinCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAIWinCount.setText("AI Count");

        lblAI.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblAI.setForeground(new java.awt.Color(64, 40, 84));
        lblAI.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAI.setText("AI Name");

        javax.swing.GroupLayout pnlAICountsLayout = new javax.swing.GroupLayout(pnlAICounts);
        pnlAICounts.setLayout(pnlAICountsLayout);
        pnlAICountsLayout.setHorizontalGroup(
            pnlAICountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAI, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblAIWinCount, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
        );
        pnlAICountsLayout.setVerticalGroup(
            pnlAICountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAICountsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblAI, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAIWinCount, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlCount.add(pnlAICounts, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 130, 70));

        pnlNorth.setOpaque(false);

        javax.swing.GroupLayout pnlNorthLayout = new javax.swing.GroupLayout(pnlNorth);
        pnlNorth.setLayout(pnlNorthLayout);
        pnlNorthLayout.setHorizontalGroup(
            pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 657, Short.MAX_VALUE)
        );
        pnlNorthLayout.setVerticalGroup(
            pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );

        pnlCount.add(pnlNorth, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, 657, -1));

        pnlSouth.setOpaque(false);

        javax.swing.GroupLayout pnlSouthLayout = new javax.swing.GroupLayout(pnlSouth);
        pnlSouth.setLayout(pnlSouthLayout);
        pnlSouthLayout.setHorizontalGroup(
            pnlSouthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 657, Short.MAX_VALUE)
        );
        pnlSouthLayout.setVerticalGroup(
            pnlSouthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        pnlCount.add(pnlSouth, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 91, 657, -1));

        getContentPane().add(pnlCount, java.awt.BorderLayout.PAGE_END);

        pnlGridContainer.setOpaque(false);
        pnlGridContainer.setPreferredSize(new java.awt.Dimension(450, 335));
        pnlGridContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(pnlGridContainer, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblReplayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblReplayMouseClicked
        lblResult.setText("");
        populateGrid(this.gridSize, difficultyLevel);
        initFirstMove(this.gridSize);
    }//GEN-LAST:event_lblReplayMouseClicked

    private void lblResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblResetMouseClicked
        (new UI_Enter()).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblResetMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblAI;
    private javax.swing.JLabel lblAIWinCount;
    private javax.swing.JLabel lblDifficulty;
    private javax.swing.JLabel lblReplay;
    private javax.swing.JLabel lblReset;
    private javax.swing.JLabel lblResult;
    private javax.swing.JLabel lblTie;
    private javax.swing.JLabel lblTieCount;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JLabel lblUserWinCount;
    private javax.swing.JLabel lblWinBy;
    private javax.swing.JPanel pnlAICounts;
    private javax.swing.JPanel pnlCount;
    private javax.swing.JPanel pnlGridContainer;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlNorth;
    private javax.swing.JPanel pnlRight;
    private javax.swing.JPanel pnlSouth;
    private javax.swing.JPanel pnlTieCounts;
    private javax.swing.JPanel pnlUserCounts;
    // End of variables declaration//GEN-END:variables

}
