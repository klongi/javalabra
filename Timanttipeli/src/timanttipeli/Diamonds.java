
package timanttipeli;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Diamonds luokka sisältää kaksiulotteisen taulukon, jossa timantit ovat. Luokassa on myös lähes kaikki pelilogiikkaan liittyvät metodit.
 * @author Krista
 */
public class Diamonds{
    private Diamond[][] diamondGraph;
    
    /**
     * Konstruktorille annetaan parametrina haluttu timanttiruudukon korkeus ja leveys
     * Konstruktori arpoo ruudukkoon satunnaisen väriset timantit
     * 
     * @param height
     * @param width 
     */
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
    

    private boolean areNextToEachOther(Coordinate c1, Coordinate c2) {
        if (c1.getRowNumber() == c2.getRowNumber() && (c1.getColumnNumber() == c2.getColumnNumber() - 1 || c1.getColumnNumber() == c2.getColumnNumber() + 1)) {
            return true;
        }
        if (c1.getColumnNumber() == c2.getColumnNumber() && (c1.getRowNumber() == c2.getRowNumber() + 1 || c1.getRowNumber() == c2.getRowNumber() - 1)) {
            return true;
        }
        return false;
    }
    
     /**
     * Metodi vaihtaa kahden vierekkäisen timantin paikkaa taulukossa
     * 
     * Jos vaihtamalla paikkoja syntyy tilanne, että samassa rivissä tai sarakkeessa on kolme tai enemmän samanvärisiä timantteja,
     * poistetaan timantit. Muuten vaihdetaan timanttien paikkaa takaisin.
     * 
     * @param rowNumber
     * @param columnNumber
     * @param newRowNumber
     * @param newColumnNumber 
     */
    public void switchPlaces(Coordinate c1, Coordinate c2) {
        if (areNextToEachOther(c1, c2)) {
            System.out.println("ovat vierekkäin");
            doSwitch(c1, c2);
            if (diamondsThatGivePoints(c1, c2) > 0) {
                checkWhatToDelete(c1, c2);
            } else {
                doSwitch(c1, c2);
            }
        }
    }
    
    private void doSwitch(Coordinate c1, Coordinate c2) {
        Diamond tmp = diamondGraph[c1.getRowNumber()][c1.getColumnNumber()];
        diamondGraph[c1.getRowNumber()][c1.getColumnNumber()] = diamondGraph[c2.getRowNumber()][c2.getColumnNumber()];;
        diamondGraph[c2.getRowNumber()][c2.getColumnNumber()] = tmp;
    }
    
    private int diamondsThatGivePoints(Coordinate c1, Coordinate c2) {
        int count = 0;
        if (countNeighboursWithSameColorOnSameRow(c2).size() >= 3 || countNeighboursWithSameColorOnSameColumn(c2).size() >= 3) {
            count++;
        }
        if (countNeighboursWithSameColorOnSameRow(c1).size() >= 3 || countNeighboursWithSameColorOnSameColumn(c1).size() >= 3) {
            count++;
        }
        return count;
    }
    
    private void checkWhatToDelete(Coordinate c1, Coordinate c2) {
        if (c1.compareTo(c2) < 0) {
            ArrayList c2NeighboursRow = countNeighboursWithSameColorOnSameRow(c2);
            ArrayList c2NeighboursColumn = countNeighboursWithSameColorOnSameColumn(c2);
            if (c2NeighboursRow.size() >= 3 || c2NeighboursColumn.size() >= 3) {
                if (c2NeighboursRow.size() >= 3) {
                    destroyDiamonds(c2NeighboursRow);
                }
                if (c2NeighboursColumn.size() >= 3) {
                    destroyDiamonds(c2NeighboursColumn);
                }
            }
        }
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
    public ArrayList countNeighboursWithSameColorOnSameColumn(Coordinate c){
        boolean[] checked = new boolean[diamondGraph.length];
        ArrayList<Coordinate> coordinatesOfSameColoredOnSameColumn = new ArrayList<Coordinate>();
        Color color = diamondGraph[c.getRowNumber()][c.getColumnNumber()].getColor();
        foundSameColor(c.getRowNumber(), c.getColumnNumber(), c.getRowNumber(), color, checked, coordinatesOfSameColoredOnSameColumn);

        return recursiveNeighbourCheckOnSameColumn(c.getRowNumber(), c.getColumnNumber(), color, checked, coordinatesOfSameColoredOnSameColumn);
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
    public ArrayList countNeighboursWithSameColorOnSameRow(Coordinate c){
        boolean[] checked = new boolean[diamondGraph[0].length];
        ArrayList<Coordinate> coordinatesOfSameColoredOnSameRow = new ArrayList<Coordinate>();
        Color color = diamondGraph[c.getRowNumber()][c.getColumnNumber()].getColor();
        foundSameColor(c.getRowNumber(), c.getRowNumber(), c.getColumnNumber(), color, checked, coordinatesOfSameColoredOnSameRow);

        return recursiveNeighbourCheckOnSameRow(c.getRowNumber(), c.getColumnNumber(), color, checked, coordinatesOfSameColoredOnSameRow);
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
    
     public void setColor(int rownumber, int columnnumber, int color) {
        diamondGraph[rownumber][columnnumber].setColor(color);
    }
    
    public Diamond[][] getDiamondArray() {
        return diamondGraph;
    }
}
