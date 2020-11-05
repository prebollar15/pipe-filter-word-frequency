package edu.depaul.cdm;

import java.util.List;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import java.util.Map;

public class ListSink extends Sink<List> {
    public ListSink(Pipe<List> input) {
        super(input);
    }

    @Override
    public void takeFrom(Pipe<List> pipe) {

        try {
            List in;
            while ((in = pipe.nextOrNullIfEmptied()) != null) {
                System.out.println("Start of List Sink: " + TimeStamp.getTimeStamp());
                System.out.println();
                System.out.println("The Top 10 words are:");
                for (int i = 0; i<10; i++){
                    //System.out.println(i);
                    System.out.println(((Map.Entry) (in.get(i))).getKey() + ": with a count of "+ ((Map.Entry) (in.get(i))).getValue());
                }   
                delayForDebug(300);
            }
            /* System.out.println("");
            System.out.println("The Top 10 words are: \n");
            for (int i = 0; i<10; i++){
                //System.out.println(i);
                System.out.println(((Map.Entry) (in.get(i))).getKey() + ": with a count of "+ ((Map.Entry) (in.get(i))).getValue());
        } */
            System.out.println("");
            System.out.println("sink finished");
            System.out.println("End of List Sink: " + TimeStamp.getTimeStamp());
            System.out.println("Ending of program: " + TimeStamp.getTimeStamp());

        } catch (InterruptedException e) {
            System.err.println("interrupted");
            e.printStackTrace();
        } finally {
            System.out.close();
        }
    }
}

