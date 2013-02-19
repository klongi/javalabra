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
    
    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this.getClass() == o.getClass()) {
            if (this.rowNumber == ((Coordinate) o).rowNumber && this.columnNumber == ((Coordinate) o).columnNumber) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.rowNumber;
        hash = 41 * hash + this.columnNumber;
        return hash;
    }
    @Override
    public int compareTo(Coordinate c) {
       if (c == null) {
           return 1;
       }
       if (this.rowNumber == c.rowNumber){
            return (this.columnNumber - c.columnNumber);
       }
       else {
           return this.rowNumber - c.rowNumber;
       }
    }
}
