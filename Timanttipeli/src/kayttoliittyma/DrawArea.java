
package kayttoliittyma;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
        ArrayList<Result> resultList = game.getResults().getResultList();
        gfx.setColor(Color.BLACK);
        gfx.fillRect(0, 0, 405, 450);
        gfx.setColor(Color.orange);
        gfx.setFont(new Font("Arial Black", 1, 30));
        gfx.drawString("TIMANTTIPELI", 60, 30);
        gfx.setFont(new Font("Arial Black", 1, 15));
        gfx.drawString("Highscore list:", 30, 70);
        for (int i = 0; i < resultList.size(); i++) {
            gfx.drawString(resultList.get(i).toString(), 30, 80 + (i+1)*20);
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
                Image img = null;
                try {
                    File src = new File("src/kayttoliittyma/"+diamondGraph[i][j].getColor()+".png");
                    img = ImageIO.read(src);
                }
                catch(IOException e) {
                    System.out.println("kuvaa ei ladattu");
                }
                gfx.drawImage(img, 5+40*j, 5+40*i, this);
                //gfx.setColor(diamondGraph[i][j].getColor());
                //gfx.fill3DRect(5+40*j, 5+40*i, 35, 35, true);       
            }
        }
        gfx.setColor(Color.orange);
        gfx.setFont(new Font("Arial Black", 1, 15));
        gfx.drawString(game.getPlayer().toString(), 20, 425);
        gfx.drawString("Time: " + game.getTimeRemaining(), 310, 425);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(DrawArea.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
