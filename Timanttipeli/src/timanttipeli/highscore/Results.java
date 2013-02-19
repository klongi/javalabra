/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timanttipeli.highscore;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Krista
 */
public class Results {
    public static final int LIST_MAX_SIZE = 10;
    private ArrayList<Result> resultList;
    private ResultReader reader;
    private ResultWriter writer;
    
    public Results() {
        resultList = new ArrayList<Result>();
        reader = new ResultReader(this, "src/timanttipeli/highscore/results.txt");
        writer = new ResultWriter("src/timanttipeli/highscore/results.txt");
        reader.lue();       
    }
    
    public void addToResultList(Result result) {
            resultList.add(result);
            Collections.sort(resultList);
            while (resultList.size() > LIST_MAX_SIZE) {
                resultList.remove(LIST_MAX_SIZE);
            }       
    }
    
   public void writeResults() {
       String results = "";
       for (Result result : resultList) {
           results += result.toString()+"\n";
       }
       writer.write(results);
   }

    public ArrayList<Result> getResultList() {
        return resultList;
    }
}
