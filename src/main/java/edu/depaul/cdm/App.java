package edu.depaul.cdm;

import java.util.ArrayList;
import java.util.List;


/*
    Utilized the Pipe/Filter Data Structure from https://gist.github.com/roryokane/9606238 for Pipes, Filters, Sink & Main Runner
*/
public class App 
{
    public static void main( String[] args ){

        // create the pipes
        final Pipe<ArrayList<String>> newGenToFilter = new PipeImpl<ArrayList<String>>();
        final Pipe<ArrayList<String>> newFilterToStemmer = new PipeImpl<ArrayList<String>>();
        final Pipe<ArrayList<String>> newFilterToFrequency = new PipeImpl<ArrayList<String>>();
        final Pipe<List> newFilterToOut = new PipeImpl<List>();

        // create components that use the pipes
        final Generator<ArrayList<String>> generator = new FileGenerator(newGenToFilter);
        final Filter<ArrayList<String>, ArrayList<String>> filter = new StopWordFilter(newGenToFilter, newFilterToStemmer);
        final Filter<ArrayList<String>, ArrayList<String>> filter2 = new StemmingFilter(newFilterToStemmer, newFilterToFrequency);
        final Filter<ArrayList<String>, List> filter3 = new FrequencyFilter(newFilterToFrequency, newFilterToOut);

        final Sink<List> sink = new ListSink(newFilterToOut);

        // start all components
        generator.start();
        filter.start();
        filter2.start();
        filter3.start();
        sink.start();

        System.out.println("runner finished");
    }
}
