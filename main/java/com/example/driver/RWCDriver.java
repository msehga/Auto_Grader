package com.example.driver;

import java.io.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import com.example.RWC.*;
import cp213.*;

public class RWCDriver {
    /**
     *
     * @param javaFilePath Filepath of the folder currently being worked on
     * @return MethodData - a hashmap of reservedwordcounter data
     * @throws ClassNotFoundException Throws error if the specified class is not found
     */

    public static HashMap<String,ReservedWordCounter> drive(String javaFilePath) throws ClassNotFoundException {
        HashMap<String, ReservedWordCounter> MethodData = new HashMap<>(); //create hashmap to store objects that are easily accessible through their string name (key value)

        String pkgname = javaFilePath.substring(javaFilePath.indexOf("cp213"),javaFilePath.lastIndexOf(".")).replace('/','.').replace('\\','.');
        System.out.println(pkgname);
        Class<?>  c = Class.forName(pkgname);//Note- We can use the Class class to make this scalable, with c instead of <classObject>.getClass();

        Method[] studentMethods = c.getDeclaredMethods(); //use reflection to get all methods from the student's class

        /*
        loop through each method and create an object for that method as the object will contain the number of continues, returns, and breaks in that method.
        save each object to a hashmap having the key be the same of the method as a string so it can easily be referenced later on
         */
        for(Method method : studentMethods){
            //we want to send over the txt file with the source code of the method to the class ReservedWordCounter
            //Therefore, step 1 is to create the txt file for the indicated method
            String txtFilePath = "src/test/java/com/example/cp317g1/cp317/methodOutputFile.txt"; //the path to the file we want to output to

            String methodName = method.getName(); //the method we want to look at

            JavaMethodToTxt(javaFilePath, methodName, txtFilePath); //call method to extract the code pertaining to the indicated method and write it to a text file


            //send over the text file with the source code of the method we want to look into so we can iterate through it in the class ReservedWordCounter
            ReservedWordCounter obj = new ReservedWordCounter(txtFilePath);
            MethodData.put(method.getName(), obj); //store into hashmap so we can access the method's data later on by using the keyword which is the method's name

        }
        return MethodData;
        /*
        for(String methodName : MethodData.keySet()){
            System.out.println(methodName + "- number of continues: " + MethodData.get(methodName).getNumContinue());
            System.out.println(methodName + "- number of breaks: " + MethodData.get(methodName).getNumBreaks());
            System.out.println(methodName + "- number of returns: " + MethodData.get(methodName).getNumReturn() + "\n");
        }
         */



    }

    /**
     * This method takes the java code of a specified method and outputs it to a text file.
     * No comments in the code will be included in the text file.
     * @param javaFilePath the path to the java file that contains the student's code
     * @param methodName the name of the method we want the source code of
     * @param txtFilePath the file we want to output the source to in text format
     */
    public static void JavaMethodToTxt(String javaFilePath, String methodName, String txtFilePath){
        //NOTE: output file must be overwritten each time for new method source code

        try{
            FileWriter fileWriter = new FileWriter(txtFilePath); //FileWriter used to write characters to an output file
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter); //characters are written to internal buffer instead of disk and then all characters in buffer are written to disk

            BufferedReader bufferReader = new BufferedReader(new FileReader(javaFilePath)); //BufferedReader reads text from the character input stream
            String line; //variable used to store what's read the java file
            boolean isInsideMethod = false; //variable used to indicate when we are inside the desired method so outside code isn't copied

            while((line = bufferReader.readLine()) != null){ //will read from java file while there's code to be read
                line = line.trim(); //use trim to get rid of any leading and trailing white spaces
                if(line.startsWith("public") && line.contains(methodName) && line.endsWith("{")){ //start of method has been reached
                    isInsideMethod = true;
                    continue;
                }else if(line.startsWith("public") && line.endsWith("{")){ //another method has been reached indicating we have reached the end of the desired method
                    isInsideMethod = false;
                    continue;
                }

                //if we are inside the desired method and line is not a comment
                if(isInsideMethod && !line.startsWith("//") && !line.startsWith("/*") && !line.startsWith("*") && !line.endsWith("*/")){
                    bufferWriter.write(line); //write to output file indicated above
                    bufferWriter.newLine(); //go to next line
                }
            }

            bufferReader.close();
            bufferWriter.close();

        } catch(IOException e){ //catch any error that is thrown
            e.printStackTrace();
        }

    }

}
