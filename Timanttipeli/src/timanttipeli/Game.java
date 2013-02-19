package timanttipeli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import kayttoliittyma.DrawArea;
import kayttoliittyma.InfoField;
import kayttoliittyma.UserInterface;
import timanttipeli.highscore.Results;

/**
 * Game hallinnoi peliä
 * 
 * Luokka luo pelialustan(diamonds taulukon) ja highscore listan
 * 
 * @author Krista
 */
public class Game {

    public static final int TIMER_START_VALUE = 180;
    
    private Diamonds diamonds;
    private DrawArea drawArea;
    private UserInterface gui;
    private Player player;
    private Coordinate clickedCoordinate;
    private Timer timer;
    private int timeremaining;
    private boolean running;
    private Results results;
   

    /**
     * 
     */
    public Game() {
        player = new Player();
        timer = new Timer(1000, new TimerListener(this));
        results = new Results();
    }
    
    public void newGame(int height, int width) {
        running = true;
        diamonds = new Diamonds(height, width);
        timeremaining = 20;
        timer.start();
    }
    
    public void endGame() {
        running = false;
        if (timer.isRunning()) {
            timer.stop();
        }
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
            player.addPoints(diamonds.getPointsToBeGiven());
        }
    }

    public Diamonds getDiamonds() {
        return diamonds;
    }
    
    public DrawArea getDrawarea() {
        return this.drawArea;
    }
    
    public void setDrawArea(DrawArea area) {
        this.drawArea = area;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public int getTimeRemaining() {
        return timeremaining;
    }
    
    public boolean running() {
        return running;
    }
    
    public void reduceTimeRemaining() {
        timeremaining--;
    }
    
    public Results getResults() {
        return this.results;
    }
}
