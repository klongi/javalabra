
package kayttoliittyma;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import timanttipeli.Diamond;
import timanttipeli.Game;
import timanttipeli.highscore.Result;

/**
 * DrawArealle piirretään timantit
 * 
 * @author Krista
 */

public class DrawArea extends JPanel{
    private Game game;
    private int height;
    
    public DrawArea(Game game, int height, int width){
        this.game = game;
        this.setLayout(new GridLayout(height, width, 0, 0));
        this.setPreferredSize(new Dimension(405,450));
        this.setVisible(true);
        this.setBackground(Color.black);
        
    }
    
    @Override
    protected void paintComponent(Graphics gfx){
        if (game.running()) {
            paintDiamonds(gfx);
        } else {
            paintHighScore(gfx);
        }
       
    }
    
    private void paintHighScore(Graphics gfx) {
        System.out.println("jshiushyusguysgtf");
        ArrayList<Result> resultList = game.getResults().getResultList();
        gfx.setColor(Color.BLACK);
        gfx.fillRect(0, 0, 405, 450);
        gfx.setColor(Color.red);
        gfx.drawString("TIMANTTIPELI", 20, 20);
        gfx.drawString("Highscore list:", 20, 40);
        for (int i = 0; i < resultList.size(); i++) {
            gfx.drawString(resultList.get(i).toString(), 20, 40 + (i+1)*20);
        }
        
    }
    
    private void paintDiamonds(Graphics gfx) {
        if (game.getDiamonds() == null) {
         return;
        }
        super.paintComponent(gfx);
        gfx.setColor(Color.BLACK);
        gfx.fillRect(0, 0, 405, 450);
        Diamond[][] diamondGraph = game.getDiamonds().getDiamondArray();
        for(int i = 0; i < diamondGraph.length; i++){
            for (int j = 0; j < diamondGraph[0].length; j++) {
                 gfx.setColor(diamondGraph[i][j].getColor());
                 gfx.fill3DRect(5+40*j, 5+40*i, 35, 35, true);       
            }
        }
        gfx.drawString(game.getPlayer().toString(), 20, 425);
        gfx.drawString("Time: " + game.getTimeRemaining(), 220, 425);
        
    }
}
