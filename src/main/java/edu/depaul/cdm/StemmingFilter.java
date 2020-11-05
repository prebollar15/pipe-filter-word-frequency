package edu.depaul.cdm;

import java.util.ArrayList;

public class StemmingFilter extends SimpleFilter<ArrayList<String>, ArrayList<String>> {
    public StemmingFilter(Pipe<ArrayList<String>> input, Pipe<ArrayList<String>> output) {
        super(input, output);
    }
    

	@Override
	protected ArrayList<String> transformOne(ArrayList<String> in) {
        System.out.println("REACHED STEMMING FILTER");
        System.out.println("Start of Stemming Filter: " + TimeStamp.getTimeStamp());

        ArrayList<String> out = in;

		for (int i = 0; i < out.size(); i++) {
            Stemmer s = new Stemmer();
            String word;

            char[] chars = out.get(i).toCharArray();

            for (char ch : chars) {
                s.add(ch);
            }
            s.stem();
            word = s.toString();
            out.set(i, word);
        }
        
        System.out.println("End of Stemming Filter: " + TimeStamp.getTimeStamp());
        delayForDebug(100);
        return out;
	}
}
    

