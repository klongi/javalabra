
package timanttipeli;

import javax.swing.SwingUtilities;
import kayttoliittyma.UserInterface;

public class Main {

    public static void main(String[] args) {
        //Menu menu = new Menu();
        Game game = new Game();
        UserInterface gui = new UserInterface(game, 10, 10);
        SwingUtilities.invokeLater(gui);
        
        while (gui.getDrawarea() == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Piirtoalustaa ei ole vielä luotu.");
            }
        }  
        game.setDrawArea(gui.getDrawarea()); 
    }
}
