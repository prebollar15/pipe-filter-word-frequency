package edu.depaul.cdm;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class StopWordFilter extends SimpleFilter<ArrayList<String>, ArrayList<String>> {
    public StopWordFilter(Pipe<ArrayList<String>> input, Pipe<ArrayList<String>> output) {
        super(input, output);
    }

    @Override
    protected ArrayList<String> transformOne(ArrayList<String> in) {
        System.out.println("REACHED STOP WORD FILTER");
        System.out.println("Start of StopWord Filter: " + TimeStamp.getTimeStamp());

        ArrayList<String> out = in;
       // System.out.print("\n---Before Word List: " + out.size()); 

        ArrayList<String> stopWords = new ArrayList<String>();
        stopWords = returnStopWords();

        for (int i = 0; i < (out.size()-1); ){
            
            if(stopWords.contains(out.get(i))){
                //System.out.println(wordList.get(i));
                out.remove(i);
                //System.out.println(wordList.get(i));

            }
            else{
                i++;
            }
        }

        //System.out.print("\n ---After Word List: " + out.size()); 
        
        System.out.println("End of StopWord Filter: " + TimeStamp.getTimeStamp());
        delayForDebug(100);
        return out;
    }

    private static ArrayList<String> returnStopWords(){

        ArrayList<String> stopWords = new ArrayList<String>();
        
        try (BufferedReader br = Files.newBufferedReader(Paths.get("/Users/Pedro/Downloads/stopwords.txt"))) {
            // read line by line
            String line;
            while ((line = br.readLine()) != null) {
                //split on non-alphabetical characters & remove blanks
                String[] words = line.split("\\W+");
                for (int i = 0; i<words.length; i++) {
                    if (!words[i].isEmpty()){
                        stopWords.add(words[i]);
                    }
                }
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        return stopWords;
    }

}