/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timanttipeli.highscore;

import java.util.ArrayList;

/**
 *
 * @author Krista
 */
public class Results {
    public static final int LIST_MAX_SIZE = 10;
    private ArrayList<Result> resultList;
    private FileReader reader;
    private FileWriter writer;
    
    public Results() {
        resultList = new ArrayList<Result>();
        reader = new FileReader(this, "src/timanttipeli/highscore/results.txt");
        writer = new FileWriter();
        reader.lue();       
    }
    
    public void addToResultList(Result result) {
        if (resultList.size() <= LIST_MAX_SIZE) {
            resultList.add(result);
        }
    }
    
    public ArrayList<Result> getResultList() {
        return resultList;
    }
}
