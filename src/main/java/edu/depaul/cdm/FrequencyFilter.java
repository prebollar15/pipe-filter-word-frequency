package edu.depaul.cdm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrequencyFilter extends SimpleFilter<ArrayList<String>, List> {
    public FrequencyFilter(Pipe<ArrayList<String>> input, Pipe<List> output) {
        super(input, output);
    }

    @Override
	protected List transformOne(ArrayList<String> in) {
        System.out.println("REACHED THE FREQUENCY FILTER");
        System.out.println("Start of Frequency Filter: " + TimeStamp.getTimeStamp());

		Map<String, Integer> map = createMap(in);
        List sortedWordList = sortByValue(map);
        //printTopTen(sortedWordList);

        delayForDebug(100);
        System.out.println("Finished Frequency Filter: " + TimeStamp.getTimeStamp());
        return sortedWordList;
    }

    //take array List and add to a hashmap
    private static Map<String,Integer> createMap(ArrayList<String> list){
        
        Map<String, Integer> map = new HashMap<>();
        for (String i : list) { 
            Integer j = map.get(i); 
            map.put(i, (j == null) ? 1 : j + 1); 
        }
        return map;
    }

    //Sort the values in descending order
    //Using compare code from https://beginnersbook.com/2013/12/how-to-sort-hashmap-in-java-by-keys-and-values/
    private static List sortByValue(Map<String, Integer> map){
        
        List list2=new ArrayList(map.entrySet());
        Collections.sort(list2,new Comparator(){

            public int compare(Object obj1, Object obj2){
                return ((Comparable)((Map.Entry)(obj1)).getValue()).compareTo(((Map.Entry)(obj2)).getValue());
            }
        });

        Collections.reverse(list2);
        return list2;
    }

    //print out the top ten most frequently used words
    private static void printTopTen(List wordList){

        System.out.println("");
        System.out.println("The Top 10 words are: \n");
        
        for (int i = 0; i<10; i++){
        
            System.out.println(((Map.Entry) (wordList.get(i))).getKey() + ": with a count of "+ ((Map.Entry) (wordList.get(i))).getValue());
        }
        System.out.println("");
    }
}