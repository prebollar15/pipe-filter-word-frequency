package edu.depaul.cdm;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

public class FileGenerator extends Generator<ArrayList<String>> {

    String file;
    
    public FileGenerator(Pipe<ArrayList<String>> output) {
        super(output);
    }

    @Override
	public void generateInto(Pipe<ArrayList<String>> pipe) {
		inputFile();

        System.out.println("Beginning of program: " + TimeStamp.getTimeStamp()+"\n");

        System.out.println("Beginning of File Generator: " + TimeStamp.getTimeStamp()+"\n");

        ArrayList<String> fileResult = new ArrayList<String>();
        
        try (BufferedReader br = Files.newBufferedReader(Paths.get(file))) {

            // read line by line
            String line;
            while ((line = br.readLine()) != null) {
                //split on non-alphabetical characters & remove blanks
                String[] words = line.split("\\W+");

                for (int i = 0; i<words.length; i++) {
                    if (!words[i].isEmpty()){
                        //check to see if word is an integer
                        try {
                            Integer.parseInt( words[i] );
                        }
                        catch( Exception e ) {
                            fileResult.add(words[i].toLowerCase());
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        System.out.println("REACHED FILE GENERATOR");

        pipe.put(new ArrayList<String>(fileResult));
        delayForDebug(200);
        pipe.closeForWriting();
        System.out.println("Finished File Generator: " + TimeStamp.getTimeStamp());
    }    

    private String inputFile(){

        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the file location: ");
        
        file = input.nextLine();
        input.close();

        return file;
    }
}
