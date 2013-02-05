package timanttipeli;

/**
 *Coordinate kertoo timantin sijainnin kaksiulotteisessa taulukossa
 * 
 * @author Krista
 */


public class Coordinate implements Comparable<Coordinate> {
    private int rowNumber;
    private int columnNumber;

    public Coordinate(int rowNumber, int columnNumber){
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }
    
    public int getRowNumber(){
        return this.rowNumber;
    }
    
    public int getColumnNumber(){
        return this.columnNumber;
    }
    @Override
    public int compareTo(Coordinate c) {
       if (this.rowNumber == c.rowNumber){
            return (this.columnNumber - c.columnNumber);
       }
       else {
           return this.rowNumber - c.rowNumber;
       }
    }
}
