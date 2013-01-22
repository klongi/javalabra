
package timanttipeli;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JPanel;

/**
 *
 * @author Krista
 */
public class Diamonds extends JPanel {
    private Diamond[][] diamondGraph;
    private JPanel gamearea;
    
    public Diamonds(int height, int width){
        diamondGraph = new Diamond[height][width];
        
        gamearea = new JPanel(new GridLayout(10, 10, 0, 0));
        gamearea.setSize(400, 400);
        gamearea.setPreferredSize(new Dimension(400, 400));
        gamearea.setVisible(true);
        gamearea.setBackground(Color.black);
        
        initialiseDiamonds(-1);   
    }

    private void initialiseDiamonds(int color){
        for (int i = 0; i < diamondGraph.length; i++) {
            for (int j = 0; j < diamondGraph[0].length; j++) {
                diamondGraph[i][j] = new Diamond(color);
                diamondGraph[i][j].repaint();
                gamearea.add(diamondGraph[i][j]);
            }         
        }
    }
    
    public void setColor(int i, int j, int color){
        diamondGraph[i][j].setColor(color);
    }
    
    public Color getColor(int i, int j){
        return diamondGraph[i][j].getColor();
    }
  
    /**
     * Tarkistaa onko annetut koordinaatit pelialueella, ja sen jälkeen onko koordinaateissa sijaitsevan timantit väri sama kuin parametrina annettu väri
     * @param i
     * @param j
     * @param color
     * @return 
     */
    public boolean isSameColor(int i, int j, Color color){
        if (j < diamondGraph[0].length && j >= 0 && i < diamondGraph.length && i >= 0 && diamondGraph[i][j] != null){
            if (diamondGraph[i][j].getColor() == color)
                return true;
        }
        return false;
    }

    /**
     * Luo booleantaulukon, johon merkitään ne ruudut jotka on jo todettu samanvärisiksi
     * Luo ArrayListin johon lisätään samanväristen naapureiden koordinaatit samalla rivillä
     * Kutsuu rekursiivista metodia, joka laskee samassa sarakkeessa vieressä olevat samanväriset timantit
     * @param i
     * @param j
     * @return 
     */
    public ArrayList countNeighboursWithSameColorOnSameColumn(int i, int j){
        boolean[] checked = new boolean[diamondGraph.length];
        ArrayList<Coordinate> coordinatesOfSameColoredOnSameColumn = new ArrayList<Coordinate>();
        Color color = diamondGraph[i][j].getColor();
        foundSameColor(i, j, i, color, checked, coordinatesOfSameColoredOnSameColumn);

        return recursiveNeighbourCheckOnSameColumn(i, j, color, checked, coordinatesOfSameColoredOnSameColumn);
    }
    
    private ArrayList recursiveNeighbourCheckOnSameColumn(int i, int j, Color color, boolean[] checked, ArrayList CoordinatesOfSameColoredOnSameColumn){
         if (isSameColor(i+1, j, color) && !checked[i+1]){
            foundSameColor(i+1, j, i+1, color, checked, CoordinatesOfSameColoredOnSameColumn);
            recursiveNeighbourCheckOnSameColumn(i+1, j, color, checked, CoordinatesOfSameColoredOnSameColumn);
        }
        if (isSameColor(i-1, j, color) && !checked[i-1]){
            foundSameColor(i-1, j, i-1, color, checked, CoordinatesOfSameColoredOnSameColumn);
            recursiveNeighbourCheckOnSameColumn(i-1, j, color, checked, CoordinatesOfSameColoredOnSameColumn);
        }  
        return CoordinatesOfSameColoredOnSameColumn;
    }
  
    /**
     * Luo booleantaulukon, johon merkitään ne ruudut jotka on jo todettu samanvärisiksi
     * Luo ArrayListin johon lisätään samanväristen naapureiden koordinaatit samassa kolumnissa
     * Kutsuu rekursiivista metodia, joka laskee samassa sarakkeessa vieressä olevat samanväriset timantit
     * @param i
     * @param j
     * @return 
     */
    public ArrayList countNeighboursWithSameColorOnSameRow(int i, int j){
        boolean[] checked = new boolean[diamondGraph[0].length];
        ArrayList<Coordinate> coordinatesOfSameColoredOnSameRow = new ArrayList<Coordinate>();
        Color color = diamondGraph[i][j].getColor();
        foundSameColor(i, j, j, color, checked, coordinatesOfSameColoredOnSameRow);

        return recursiveNeighbourCheckOnSameRow(i, j, color, checked, coordinatesOfSameColoredOnSameRow);
    }
 
     private ArrayList recursiveNeighbourCheckOnSameRow(int i, int j, Color color, boolean[] checked, ArrayList CoordinatesOfSameColoredOnSameRow){
        if (isSameColor(i, j+1, color) && !checked[j+1]){
            foundSameColor(i, j+1, j+1, color, checked, CoordinatesOfSameColoredOnSameRow);
            recursiveNeighbourCheckOnSameRow(i, j+1, color, checked, CoordinatesOfSameColoredOnSameRow);
        }
        if (isSameColor(i, j-1, color) && !checked[j-1]){
            foundSameColor(i, j-1, j-1, color, checked, CoordinatesOfSameColoredOnSameRow);
            recursiveNeighbourCheckOnSameRow(i, j-1, color, checked, CoordinatesOfSameColoredOnSameRow);         
        } 
       
        return CoordinatesOfSameColoredOnSameRow;      
    }
    
   private void foundSameColor(int i, int j, int checkCoordinate, Color color, boolean[] checked, ArrayList NeighbourCoordinates){
        checked[checkCoordinate] = true;
        NeighbourCoordinates.add(new Coordinate(i,j));
    }
    
   /**
    * *Tuhoaa annetuissa koordinaateissa olevan timantin, siirtää samassa sarakkeessa ylempänä olevia timantteja yhden alaspäin
    * ja luo ylimmäksi uuden timantin
    * @param rowNumber
    * @param columnNumber 
    */
    public void destroyDiamond(int rowNumber, int columnNumber){
        while (rowNumber>0){
            diamondGraph[rowNumber][columnNumber] = diamondGraph[rowNumber-1][columnNumber];
            rowNumber--;
        }
        diamondGraph[0][columnNumber] = new Diamond(-1);
    }
    
    /**
     * Tuhoaa kaikki parametrina annetun koordinaattilistan koordinaateissa sijaitsevat timantit
     * @param CoordinateList 
     */
    public void destroyDiamonds(ArrayList<Coordinate> CoordinateList){
        Collections.sort(CoordinateList);
        for(Coordinate coordinate : CoordinateList){
           destroyDiamond(coordinate.getRowNumber(), coordinate.getColumnNumber());
         }
    }
    
    public void update() {
        for (int i = 0; i < diamondGraph.length; i++) {
            for (int j = 0; j < diamondGraph[0].length; j++) {
                diamondGraph[i][j].repaint();
            }
        }
    }
    
    public JPanel getGamearea() {
        return this.gamearea;
    }
    
    public void paintDiamonds(){
        for (int i = 0; i < diamondGraph.length; i++) {
            for (int j = 0; j < diamondGraph[0].length; j++) {
                diamondGraph[i][j].repaint();
            }
        }
    }
}
