package timanttipeli;

import javax.swing.Timer;
import kayttoliittyma.DrawArea;
import kayttoliittyma.Interface;

/**
 * Game luo pelin ja kontrolloi sitä
 * 
 * Luokka luo käyttöliittymän ja Diamonds taulukon.
 * @author Krista
 */
public class Game extends Timer {

    private Diamonds diamonds;
    private DrawArea area;
    private boolean clicked;
    private Player player;
    private Coordinate clickedCoordinate;

    /**
     * Konstruktori kutsuu yliluokan konstruktoria, sekä luo Diamondsin.
     */
    public Game(String name) {
        super(1000, null);
        this.player = new Player(name);
        diamonds = new Diamonds(10, 10);
        Interface infa = new Interface(this);
        setDrawarea(infa.getDrawarea());
        start();
        GameLoop();
    }

    /**
     * Clicked määrittelee mitä tapahtuu, kun Listener havaitsee klikkauksen.
     *
     * Jotta jotain voidaan tehdä, vaaditaan kaksi klikkausta. Ensimmäinen
     * klikkaus tallennetaan clickedCoordinate -muuttujaan. Kun
     * clickedCoordinate ei ole null, tiedetään, että kahta eri timanttia on
     * klikattu. Jos on klikattu kahta timanttia, kutsutaan switchPlaces
     * metodia.
     *
     * @param coordinate
     */
    public void clicked(Coordinate coordinate) {
        if (clickedCoordinate == null) {
            clickedCoordinate = coordinate;
        } else {
            diamonds.switchPlaces(clickedCoordinate, coordinate);
            clickedCoordinate = null;
        }
    }

    /**
     * Metodi piirtää kaiken uudestaan, kun on tapahtunut muutoksia.
     */
    private void GameLoop() {
        while (true) {
            this.stop();
            //area.repaint();
            if (this.clicked) {
                area.repaint();
                this.clicked = false;
                this.start();
            }

        }
    }

    public Diamonds getDiamonds() {
        return diamonds;
    }

    private void setDrawarea(DrawArea area) {
        this.area = area;
    }

    public DrawArea getDrawarea() {
        return this.area;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
}
