package edu.depaul.cdm;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        final Pipe<Integer> genToFilter = new PipeImpl<Integer>();
        final Pipe<String> filterToOut = new PipeImpl<String>();

        // create components that use the pipes
        final Generator<Integer> generator = new ExampleGenerator(genToFilter);
        final Filter<Integer, String> filter = new ExampleFilter(genToFilter, filterToOut);
        final Sink<String> sink = new ExampleSink(filterToOut);

        // start all components
        generator.start();
        filter.start();
        sink.start();

        System.out.println("runner finished");
    }
}
