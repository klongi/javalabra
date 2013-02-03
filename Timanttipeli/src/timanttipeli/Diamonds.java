
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
public class Diamonds{
    private Diamond[][] diamondGraph;
    private Coordinate clickedCoordinate;
    
    public Diamonds(int height, int width){
        diamondGraph = new Diamond[height][width];
        initialiseDiamonds(-1);   
    }

    private void initialiseDiamonds(int color){
        for (int i = 0; i < diamondGraph.length; i++) {
            for (int j = 0; j < diamondGraph[0].length; j++) {
                diamondGraph[i][j] = new Diamond(color);
            }         
        }
    }
    
    public void clicked(Coordinate coordinate){
        if (clickedCoordinate == null){
            System.out.println("asetetaan clickedCoordinate");
            clickedCoordinate = coordinate;
        }
        else {
            System.out.println("ei ollut null");
            if (areNextToEachOther(clickedCoordinate, coordinate)){
                System.out.println("ovat vierekkäin");
                switchPlaces(clickedCoordinate, coordinate);
            }
            clickedCoordinate = null; 
        }
    }
    
       /**
     * Metodi vaihtaa kahden vierekkäisen timantin paikkaa taulukossa
     * 
     * @param rowNumber
     * @param columnNumber
     * @param newRowNumber
     * @param newColumnNumber 
     */
    public void switchPlaces(Coordinate c1, Coordinate c2){
            //Color color = diamondGraph[c1.getColumnNumber()][c1.getColumnNumber()].getColor();
            //diamondGraph[c1.getRowNumber()][c1.getColumnNumber()].setColor(diamondGraph[c2.getRowNumber()][c2.getColumnNumber()].getColor());
            //diamondGraph[c2.getRowNumber()][c2.getColumnNumber()].setColor(color);
            Diamond tmp = diamondGraph[c1.getRowNumber()][c1.getColumnNumber()];
            diamondGraph[c1.getRowNumber()][c1.getColumnNumber()] = diamondGraph[c2.getRowNumber()][c2.getColumnNumber()];;
            diamondGraph[c2.getRowNumber()][c2.getColumnNumber()] = tmp;
    }
    
    private boolean areNextToEachOther(Coordinate c1, Coordinate c2){
        if (c1.getRowNumber() == c2.getRowNumber() && (c1.getColumnNumber() == c2.getColumnNumber() -1 || c1.getColumnNumber() == c2.getColumnNumber() +1)){
            return true;
        }
        if (c1.getColumnNumber() == c2.getColumnNumber() && (c1.getRowNumber() == c2.getRowNumber() + 1 || c1.getRowNumber() == c2.getRowNumber() -1)){
            return true;
        }
        return false;
    }
    
    public void setColor(int rownumber, int columnnumber, int color){
        diamondGraph[rownumber][columnnumber].setColor(color);
    }
    
  /**
   * Metodi palauttaa parametrina annetuissa koordinaateissa olevan timantin värin
   * @param rownumber
   * @param columnnumber
   * @return 
   */
    public Color getColor(int rownumber, int columnnumber){
        return diamondGraph[rownumber][columnnumber].getColor();
    }
  
    /**
     * Metodi tarkistaa ovatko annetut koordinaatit pelialueella ja onko koordinaateissa sijaitsevan timantin väri sama kuin parametrina annettu väri.
     * @param rownumber
     * @param columnnumber
     * @param color
     * @return 
     */
    public boolean isSameColor(int rownumber, int columnnumber, Color color){
        if (columnnumber < diamondGraph[0].length && columnnumber >= 0 && rownumber < diamondGraph.length && rownumber >= 0 && diamondGraph[rownumber][columnnumber] != null){
            if (diamondGraph[rownumber][columnnumber].getColor() == color)
                return true;
        }
        return false;
    }

    /**
     * Metodi laskee samassa sarakkeessa vieressä olevat samanväriset timantit
     * 
     * Luo booleantaulukon, johon merkitään ne ruudut jotka on jo tarkistettu ja todettu samanvärisiksi.
     * Luo ArrayListin johon lisätään samanväristen naapureiden koordinaatit samassa sarakkeessa.
     * Kutsuu rekursiivista metodia, joka laskee samassa sarakkeessa vieressä olevat samanväriset timantit.
     * 
     * @param rownumber
     * @param columnnumber
     * @return 
     */
    public ArrayList countNeighboursWithSameColorOnSameColumn(int rownumber, int columnnumber){
        boolean[] checked = new boolean[diamondGraph.length];
        ArrayList<Coordinate> coordinatesOfSameColoredOnSameColumn = new ArrayList<Coordinate>();
        Color color = diamondGraph[rownumber][columnnumber].getColor();
        foundSameColor(rownumber, columnnumber, rownumber, color, checked, coordinatesOfSameColoredOnSameColumn);

        return recursiveNeighbourCheckOnSameColumn(rownumber, columnnumber, color, checked, coordinatesOfSameColoredOnSameColumn);
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
     * Laskee samalla rivillä vieressä olevien samanväristen timanttien määrän
     * 
     * Luo booleantaulukon, johon merkitään ne ruudut jotka on jo todettu samanvärisiksi.
     * Luo ArrayListin johon lisätään samanväristen naapureiden koordinaatit samalla rivillä.
     * Kutsuu rekursiivista metodia, joka laskee samalla rivillä vieressä olevat samanväriset timantit.
     * 
     * @param rownumber
     * @param columnnumber
     * @return 
     */
    public ArrayList countNeighboursWithSameColorOnSameRow(int rownumber, int columnnumber){
        boolean[] checked = new boolean[diamondGraph[0].length];
        ArrayList<Coordinate> coordinatesOfSameColoredOnSameRow = new ArrayList<Coordinate>();
        Color color = diamondGraph[rownumber][columnnumber].getColor();
        foundSameColor(rownumber, columnnumber, columnnumber, color, checked, coordinatesOfSameColoredOnSameRow);

        return recursiveNeighbourCheckOnSameRow(rownumber, columnnumber, color, checked, coordinatesOfSameColoredOnSameRow);
    }
 
     private ArrayList recursiveNeighbourCheckOnSameRow(int rownumber, int columnnumber, Color color, boolean[] checked, ArrayList CoordinatesOfSameColoredOnSameRow){
        if (isSameColor(rownumber, columnnumber+1, color) && !checked[columnnumber+1]){
            foundSameColor(rownumber, columnnumber+1, columnnumber+1, color, checked, CoordinatesOfSameColoredOnSameRow);
            recursiveNeighbourCheckOnSameRow(rownumber, columnnumber+1, color, checked, CoordinatesOfSameColoredOnSameRow);
        }
        if (isSameColor(rownumber, columnnumber-1, color) && !checked[columnnumber-1]){
            foundSameColor(rownumber, columnnumber-1, columnnumber-1, color, checked, CoordinatesOfSameColoredOnSameRow);
            recursiveNeighbourCheckOnSameRow(rownumber, columnnumber-1, color, checked, CoordinatesOfSameColoredOnSameRow);         
        } 
       
        return CoordinatesOfSameColoredOnSameRow;      
    }
     
   private void foundSameColor(int rownumber, int columnnumber, int checkCoordinate, Color color, boolean[] checked, ArrayList NeighbourCoordinates){
        checked[checkCoordinate] = true;
        NeighbourCoordinates.add(new Coordinate(rownumber,columnnumber));
    }
    
   /**
    * Metodi tuhoaa yhden timantin
    * 
    * Tuhoaa annetuissa koordinaateissa olevan timantin, siirtää samassa sarakkeessa ylempänä olevia timantteja yhden alaspäin
    * ja luo ylimmäksi uuden timantin.
    * 
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
     * Metodi tuhoaa kaikki parametrina annetun koordinaattilistan koordinaateissa sijaitsevat timantit
     * @param CoordinateList 
     */
    public void destroyDiamonds(ArrayList<Coordinate> CoordinateList){
        Collections.sort(CoordinateList);
        for(Coordinate coordinate : CoordinateList){
           destroyDiamond(coordinate.getRowNumber(), coordinate.getColumnNumber());
         }
    }
    
    
    public Diamond[][] getDiamondArray() {
        return diamondGraph;
    }
}
