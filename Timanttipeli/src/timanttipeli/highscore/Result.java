/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timanttipeli.highscore;

/**
 *
 * @author Krista
 */
public class Result implements Comparable<Result> {
    private String playerName;
    private int playerPoints;
    
    public Result(String playerName, int playerPoints) {
        this.playerName = playerName;
        this.playerPoints = playerPoints;
    }
    
    @Override
    public String toString() {
        return this.playerName + " : " + this.playerPoints;
    }

    @Override
    public int compareTo(Result result) {
        return result.getPoints() - this.playerPoints;
    }
    
    public int getPoints() {
        return this.playerPoints;
    }

}
