package com.example.cp317g1.cp317;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReservedWordCounter {

    private int numContinue;
    private int numReturn;
    private int numBreaks;
    private final String filePath;


    public ReservedWordCounter(final String fPath){
        this.numContinue = 0;
        this.numReturn = 0;
        this.numBreaks = 0;

        this.filePath = fPath;

        //execute the below function any time an instance of this class is created
        MethodWordIterator();

    }

    public void MethodWordIterator(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filePath)); //buffer reader so we can read from text file with method we want to examine
            String line;

            while((line = reader.readLine()) != null){
                //split the string in line into words using white spaces as the delimiter
                String[] words = line.split("\\s+");

                //loop through each word in string and count the keywords continue; return; and break;
                for(String examineWord : words){
                    if(examineWord.equals("continue;")) {
                        numContinue++;
                    } else if(examineWord.equals("return;") || examineWord.equals("return")){
                        numReturn++;
                    } else if(examineWord.equals("break;")){
                        numBreaks++;
                    }
                }

            }//end of while loop

            reader.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }//end of MethodWordIterator()



    public int getNumContinue(){
        return numContinue;
    }

    public int getNumReturn(){
        return numReturn;
    }

    public int getNumBreaks(){
        return numBreaks;
    }

}
