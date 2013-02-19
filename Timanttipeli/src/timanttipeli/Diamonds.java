package timanttipeli;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import kayttoliittyma.DrawArea;

/**
 * Diamonds -luokka sisältää kaksiulotteisen taulukon, jossa timantit ovat.
 * Luokassa on myös lähes kaikki pelilogiikkaan liittyvät metodit, kuten tarkistukset,
 * että suoritetaanko timanttien vaihto ja timanttien tuhoaminen.
 *
 * @author Krista
 */
public class Diamonds {

    private Diamond[][] diamondGraph;
    private int pointsToBeGiven;
    private Game game;
    private DrawArea drawArea;

    /**
     * Konstruktorille annetaan parametrina haluttu timanttiruudukon korkeus ja
     * leveys. Konstruktori kutsuu privaattia metodia, joka arpoo timanteille värit,
     * ja poistaa mahdollisesti syntyneet tilanteet, joissa olisi valmiina 3 tai enemmän
     * samanvärisiä timantteja.
     *
     *
     * @param height
     * @param width
     */
    public Diamonds(int height, int width, DrawArea drawArea) {
        this.drawArea = drawArea;
        diamondGraph = new Diamond[height][width];
        initialiseDiamonds(-1);
        
    }
    
    /*
     * Metodi luo timanttiruudukon jokaiseen ruudukkoon uuden timantin.
     * Tämän jälkeen poistetaan syntyneet kolmen timantin setit.
     * Lopuksi asetetaan aloituspisteiksi nolla.
     */

    private void initialiseDiamonds(int color) {
        for (int i = 0; i < diamondGraph.length; i++) {
            for (int j = 0; j < diamondGraph[0].length; j++) {
                diamondGraph[i][j] = new Diamond(color);
            }
        }
        removeSets();
        pointsToBeGiven = 0;
    }
    
    /*
     * Metodi poistaa ruudulla sijaitsevat setit, joissa on 3 tai enemmän samanvärisiä
     * timantteja vierekkäin. Poistaminen aloitetaan alalaidasta, ja ruudukko käydään läpi
     * niin monta kertaa, kunnes ruudukko käydään kerran läpi poistamatta enää mitään.
     */
    private void removeSets() {
        boolean deleted = false;
        for (int i = diamondGraph.length - 1; i >= 0; i--) {
            for (int j = diamondGraph[0].length - 1; j >= 0; j--) {
                Coordinate c = new Coordinate(i, j);
                if (countNeighboursWithSameColorOnSameRow(c).size() >= 3 || countNeighboursWithSameColorOnSameColumn(c).size() >= 3) {
                    startDeleting(c);
                    deleted = true;
                }
            }
        }
        if (deleted) {
            removeSets();
        }
    }

  /**
   * Metodi vaihtaa kahden vierekkäisen timantin paikkaa taulukossa
   *
   * Jos vaihtamalla koordinaateissa c1 ja c2 sijaitsevien timanttien paikkoja 
   * syntyy tilanne, että samassa rivissä tai sarakkeessa on kolme tai enemmän 
   * samanvärisiä timantteja, poistetaan timantit.
   * Muuten vaihdetaan timanttien paikkaa takaisin.
   * 
   * @param c1
   * @param c2 
   */
    public void switchPlaces(Coordinate c1, Coordinate c2) {
        if (areNextToEachOther(c1, c2)) {
            System.out.println("ovat vierekkäin");
            doSwitch(c1, c2);
            if (switchValid(c1, c2)) {
                checkWhatToDelete(c1, c2);
            } else {
                doSwitch(c1, c2);
            }
        }
    }

    /**
     * Metodi tarkistaa ovatko koordinaateissa c1 ja c2 sijaitsevat timantit
     * vierekkäin
     *
     * @param c1
     * @param c2
     * @return
     */
    public boolean areNextToEachOther(Coordinate c1, Coordinate c2) {
        if (c1.getRowNumber() == c2.getRowNumber() 
                && (c1.getColumnNumber() == c2.getColumnNumber() - 1 || c1.getColumnNumber() == c2.getColumnNumber() + 1)) {
            return true;
        }
        if (c1.getColumnNumber() == c2.getColumnNumber() 
                && (c1.getRowNumber() == c2.getRowNumber() + 1 || c1.getRowNumber() == c2.getRowNumber() - 1)) {
            return true;
        }
        return false;
    }
    
    /**
     * Metodi vaihtaa koordinaateissa c1 ja c2 sijaitsevien timanttien paikat
     * 
     * @param c1
     * @param c2 
     */

    public void doSwitch(Coordinate c1, Coordinate c2) {
        Diamond tmp = diamondGraph[c1.getRowNumber()][c1.getColumnNumber()];
        diamondGraph[c1.getRowNumber()][c1.getColumnNumber()] = diamondGraph[c2.getRowNumber()][c2.getColumnNumber()];
        diamondGraph[c2.getRowNumber()][c2.getColumnNumber()] = tmp;
    }

    /**
     * Metodi tarkistaa, onko koordinaateissa c1 ja c2 suoritettava timanttien vaihto sallittu
     * 
     * Vaihto on sallittu, jos syntyy tilanne, jossa samaan riviin tai 
     * sarakkeeseen tulee 3 saman väristä timanttia. Tällöin palautetaan true.
     * Muuten palautetaan false
     * 
     * @param c1
     * @param c2
     * @return 
     */
    public boolean switchValid(Coordinate c1, Coordinate c2) {
        int count = 0;
        if (countNeighboursWithSameColorOnSameRow(c2).size() >= 3 || countNeighboursWithSameColorOnSameColumn(c2).size() >= 3) {
            return true;
        }
        if (countNeighboursWithSameColorOnSameRow(c1).size() >= 3 || countNeighboursWithSameColorOnSameColumn(c1).size() >= 3) {
            return true;
        }
        return false;
    }
    /*
     * checkWHatToDelete ei vielä toimi ihan niinkuin pitäisi. Tällä hetkellä tekee poistamisen ensin
     * alemmalle timantille. Samalla ylemmän tilanne muuttuu, joten sen naapureita ei välttämättä poisteta, tai
     * saatetaan poistaa ihan mun värisiä jos syntyy sellainen tilanne.
     * Miten halutaan asian olevan??
     */

    private void checkWhatToDelete(Coordinate c1, Coordinate c2) {
        if (c1.compareTo(c2) < 0) {
            startDeleting(c2);
//            startDeleting(c1);
        } else {
            startDeleting(c1);
//            startDeleting(c2);
        }
    }
    /**
     * Metodi poistaa koordinaatin c ja sen naapurit
     * 
     * @param c 
     */
    public void startDeleting(Coordinate c) {
        ArrayList neighboursRow = countNeighboursWithSameColorOnSameRow(c);
        ArrayList neighboursColumn = countNeighboursWithSameColorOnSameColumn(c);
        if (neighboursRow.size() >= 3 && neighboursColumn.size() >= 3) {
            destroyDiamonds(combineLists(neighboursColumn, neighboursRow));
        } else if (neighboursRow.size() >= 3) {
            destroyDiamonds(neighboursRow);
        } else if (neighboursColumn.size() >= 3) {
            destroyDiamonds(neighboursColumn);
        }
        removeSets();
    }
    
    /**
     * Metodi yhdistää kaksi ArrayListiä siten, että uudessa listassa ei ole kahta
     * samaa koordinaattia. 
     * 
     * @param list1
     * @param list2
     * @return 
     */
    public ArrayList combineLists(ArrayList<Coordinate> list1, ArrayList<Coordinate> list2) {
        for (Coordinate c : list1) {
            if (!list2.contains(c)){
                list2.add(c);
            }
        }
        return list2;
    }

    /**
     * Metodi tarkistaa ovatko annetut koordinaatit pelialueella ja onko
     * koordinaateissa sijaitsevan timantin väri sama kuin parametrina annettu
     * väri.
     *
     * @param rownumber
     * @param columnnumber
     * @param color
     * @return
     */
    public boolean isSameColor(int rownumber, int columnnumber, Color color) {
        if (columnnumber < diamondGraph[0].length && columnnumber >= 0
                && rownumber < diamondGraph.length && rownumber >= 0 && diamondGraph[rownumber][columnnumber] != null) {
            if (diamondGraph[rownumber][columnnumber].getColor() == color) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodi laskee samassa sarakkeessa vieressä olevat samanväriset timantit
     *
     * Luo booleantaulukon, johon merkitään ne ruudut jotka on jo tarkistettu ja
     * todettu samanvärisiksi. Luo ArrayListin johon lisätään samanväristen
     * naapureiden koordinaatit samassa sarakkeessa. Kutsuu rekursiivista
     * metodia, joka laskee samassa sarakkeessa vieressä olevat samanväriset
     * timantit.
     *
     * @param rownumber
     * @param columnnumber
     * @return
     */
    public ArrayList countNeighboursWithSameColorOnSameColumn(Coordinate c) {
        boolean[] checked = new boolean[diamondGraph.length];
        checked[c.getRowNumber()] = true;
        ArrayList<Coordinate> coordinatesOfSameColoredOnSameColumn = new ArrayList<Coordinate>();
        Color color = diamondGraph[c.getRowNumber()][c.getColumnNumber()].getColor();
        coordinatesOfSameColoredOnSameColumn.add(c);

        return recursiveNeighbourCheckOnSameColumn(c.getRowNumber(), c.getColumnNumber(), color, checked, coordinatesOfSameColoredOnSameColumn);
    }

    private ArrayList recursiveNeighbourCheckOnSameColumn(int rownumber, int columnnumber, Color color, boolean[] checked, ArrayList CoordinatesOfSameColoredOnSameColumn) {
        if (isSameColor(rownumber + 1, columnnumber, color) && !checked[rownumber + 1]) {
            addToList(rownumber + 1, columnnumber, rownumber + 1, color, checked, CoordinatesOfSameColoredOnSameColumn);
            recursiveNeighbourCheckOnSameColumn(rownumber + 1, columnnumber, color, checked, CoordinatesOfSameColoredOnSameColumn);
        }
        if (isSameColor(rownumber - 1, columnnumber, color) && !checked[rownumber - 1]) {
            addToList(rownumber - 1, columnnumber, rownumber - 1, color, checked, CoordinatesOfSameColoredOnSameColumn);
            recursiveNeighbourCheckOnSameColumn(rownumber - 1, columnnumber, color, checked, CoordinatesOfSameColoredOnSameColumn);
        }
        return CoordinatesOfSameColoredOnSameColumn;
    }

    /**
     * Metodi laskee samalla rivillä vieressä olevien samanväristen timanttien määrän
     *
     * Luo booleantaulukon, johon merkitään ne ruudut jotka on jo todettu
     * samanvärisiksi. Luo ArrayListin johon lisätään samanväristen naapureiden
     * koordinaatit samalla rivillä. Kutsuu rekursiivista metodia, joka laskee
     * samalla rivillä vieressä olevat samanväriset timantit.
     *
     * @param rownumber
     * @param columnnumber
     * @return
     */
    public ArrayList countNeighboursWithSameColorOnSameRow(Coordinate c) {
        boolean[] checked = new boolean[diamondGraph[0].length];
        checked[c.getColumnNumber()] = true;
        ArrayList<Coordinate> coordinatesOfSameColoredOnSameRow = new ArrayList<Coordinate>();
        Color color = diamondGraph[c.getRowNumber()][c.getColumnNumber()].getColor();
        coordinatesOfSameColoredOnSameRow.add(c);

        return recursiveNeighbourCheckOnSameRow(c.getRowNumber(), c.getColumnNumber(), color, checked, coordinatesOfSameColoredOnSameRow);
    }

    private ArrayList recursiveNeighbourCheckOnSameRow(int rownumber, int columnnumber, Color color, boolean[] checked, ArrayList CoordinatesOfSameColoredOnSameRow) {
        if (isSameColor(rownumber, columnnumber + 1, color) && !checked[columnnumber + 1]) {
            addToList(rownumber, columnnumber + 1, columnnumber + 1, color, checked, CoordinatesOfSameColoredOnSameRow);
            recursiveNeighbourCheckOnSameRow(rownumber, columnnumber + 1, color, checked, CoordinatesOfSameColoredOnSameRow);
        }
        if (isSameColor(rownumber, columnnumber - 1, color) && !checked[columnnumber - 1]) {
            addToList(rownumber, columnnumber - 1, columnnumber - 1, color, checked, CoordinatesOfSameColoredOnSameRow);
            recursiveNeighbourCheckOnSameRow(rownumber, columnnumber - 1, color, checked, CoordinatesOfSameColoredOnSameRow);
        }

        return CoordinatesOfSameColoredOnSameRow;
    }
    
    /*
     * Metodi lisää löydetyn samanvärsien timantin koordinaatin koordinaattilistaa, ja merkitsee koordinaatin tarkistetuksi
     */

    private void addToList(int rownumber, int columnnumber, int checkCoordinate, Color color, boolean[] checked, ArrayList NeighbourCoordinates) {
        checked[checkCoordinate] = true;
        NeighbourCoordinates.add(new Coordinate(rownumber, columnnumber));
    }

    /**
     * Metodi tuhoaa yhden timantin
     *
     * Tuhoaa annetuissa koordinaateissa olevan timantin, siirtää samassa
     * sarakkeessa ylempänä olevia timantteja yhden alaspäin ja luo ylimmäksi
     * uuden timantin. Kasvattaa myös muuttujaa pointsToBeGiven kymmenellä.
     *
     * @param rowNumber
     * @param columnNumber
     */
    public void destroyDiamond(int rowNumber, int columnNumber) {
        diamondGraph[rowNumber][columnNumber].setColor(Color.BLACK);
       // this.drawArea.repaint();
        while (rowNumber > 0) {
            diamondGraph[rowNumber][columnNumber] = diamondGraph[rowNumber - 1][columnNumber];
            rowNumber--;
        }
        pointsToBeGiven += 10;
        diamondGraph[0][columnNumber] = new Diamond(-1);
    }

    /**
     * Metodi tuhoaa kaikki parametrina annetun koordinaattilistan
     * koordinaateissa sijaitsevat timantit
     *
     * @param CoordinateList
     */
    public void destroyDiamonds(ArrayList<Coordinate> CoordinateList) {
        Collections.sort(CoordinateList);
        for (Coordinate coordinate : CoordinateList) {
            destroyDiamond(coordinate.getRowNumber(), coordinate.getColumnNumber());
        }
    }
    
    /**
     * Metodi palauttaa annettavat pisteet ja samalla nollaa ne.
     * @return 
     */
    
    public int getPointsToBeGiven() {
        int palautettava = pointsToBeGiven;
        pointsToBeGiven = 0;
        return palautettava;
    }

    public Color getColor(Coordinate c) {
        return diamondGraph[c.getRowNumber()][c.getColumnNumber()].getColor();
    }

    public Color getColor(int rownumber, int columnnumber) {
        return diamondGraph[rownumber][columnnumber].getColor();
    }

    public void setColor(int rownumber, int columnnumber, int color) {
        diamondGraph[rownumber][columnnumber].setColor(color);
    }

    public Diamond[][] getDiamondArray() {
        return diamondGraph;
    }
    
}
