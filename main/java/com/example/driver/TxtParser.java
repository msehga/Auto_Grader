package com.example.driver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TxtParser {

    String[][] parseFile(File file) { // manik was here.
        String[][] data = null;
        Set<String> uniqueLines = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;// manik

            while ((line = reader.readLine()) != null) {
                if (uniqueLines.add(line)) {
                    String[] elements = line.split("\\|");
                    if (data == null) {
                        // Initialize the 2D array with the first line
                        data = new String[1][elements.length];
                    } else {
                        // Expand the 2D array to accommodate the new line
                        String[][] newData = new String[data.length + 1][];
                        System.arraycopy(data, 0, newData, 0, data.length);
                        data = newData;
                    }
                    data[data.length - 1] = elements;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
        return data;
    }

    String[][] parse2dArray(String[][] result){
        ArrayList<String[]> parseList = new ArrayList<>();
        for(String[] strings : result) {
            if (strings.length == 4){
                parseList.add(strings);
            }
        }
        String[][] txtParsed = new String[parseList.size()][];
        txtParsed = parseList.toArray(txtParsed);
        return txtParsed;
    }


    public static void main(String[] args) {
        String fileName = "unitTests/com/example/driver/resources/TextFile2.txt";
        String filePath = System.getProperty("user.dir") + File.separator + fileName;
        File file = new File(filePath);
        TxtParser parser = new TxtParser();
        String[][] data = parser.parseFile(file);
        data = parser.parse2dArray(data);

        // Print the parsed data
        for (String[] line : data) {
            for (String element : line) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}