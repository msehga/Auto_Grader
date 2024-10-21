package com.example.driver;


//import java.util.HashMap;

import com.example.RWC.ReservedWordCounter;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import cp213.*;

import static com.example.driver.mainfile.CSVPrinter;
import static com.example.driver.mainfile.StudentMarkPrinter;


public class mainfile {
    public static PrintStream stdout = System.out;
    public static PrintStream StudentMarkPrinter;
    public static PrintStream CSVPrinter;
    /**
     * Driver/ main method for the program
     */
    public static void driver() {
        try {

            Scanner sc = new Scanner(System.in);
            String infilepath = "src/main/java/com/example/StudentCode/StudentFiles";
            new File("src/main/java/com/example/StudentCode/StudentGrades").mkdirs();
            for(File f : new File("src/main/java/com/example/StudentCode/StudentGrades").listFiles())
                f.delete();
            File CSV = new File("src/main/java/com/example/StudentCode/StudentGrades/GradeFile.csv");
            CSVPrinter = new PrintStream(new FileOutputStream(CSV));
            System.setOut(CSVPrinter);
            File gradeExported = new File("src/main/java/com/example/StudentCode/GradeExported").listFiles()[0];
            ArrayList<StudentInfo> Students = new ArrayList<>();
            Scanner scanner = new Scanner(gradeExported);
            String Firstline = scanner.nextLine();
            Firstline = Firstline.replace("Last Name,","");
            Firstline = Firstline.replace("First Name,","");
            while(scanner.hasNextLine())
            {
                Students.add(new StudentInfo(scanner.nextLine()));
            }
            scanner.close();
            gradeExported.delete();

            System.out.println(Firstline);
            System.setOut(stdout);
            ZipProcessor Unzip = new ZipProcessor();
            File destDir = new File("src/main/java/com/example/StudentCode/EachAssignment");

            //Unzips File
            Unzip.unZip(new File(infilepath).listFiles()[0],destDir);

            //We can either take weights as a txt file or as input that is converted to txt file
            String weightFile = "src/main/resources/static/SampleWeights.txt";
            //System.out.println("Please enter filepath of downloaded zip file");
            File weights = new File(weightFile);
            Pattern pattern = Pattern.compile("\\R");
            List<String> aa = pattern.splitAsStream(Files.readString(weights.toPath())).toList();
            String[] key1 = new String[aa.size()];
            aa.toArray(key1);
            Parameters[] keys = new Parameters[key1.length];
            for (int i = 0; i < key1.length; i++) {
                keys[i] = new Parameters(key1[i].split("\\|"));
                System.out.println(keys[i].toString());
            }

            HashMap<String, Parameters> ParameterMap = new HashMap<>();
            for (Parameters key : keys) {
                ParameterMap.put(key.getMethodname(), key);
            }

            //Marks a student- Going to be inside some type of loop


            System.setOut(stdout);
            HashMap<String,ForEach> students= new HashMap<>();
            String[] files = destDir.list();// List of Students
            String studentName = "";
            String studentNo = null;
            File assignCode;
            File stuCode;
            String pkgpath;
            ArrayList<String> studentClassFiles = null;
            ZipProcessor unzipForEach = new ZipProcessor();
            File staticRepository = new File("src/main/resources/MainFile/");
            File mainFile = staticRepository.listFiles()[0];
            File individualFiles = new File("src/main/java/cp213/" + mainFile.getName());
            System.out.println("Files moved to: " + Files.move(mainFile.toPath(),individualFiles.toPath()));

            for(String file : files)//Each individual Student
            {
                if(file.contains(".zip")) {
                    studentClassFiles = new ArrayList<>();


                    File StudentFile = new File("src/main/java/com/example/StudentCode/EachAssignment/" + file);

                    File assignementFile = new File("src/main/java/com/example/StudentCode/AssignmentCode/");
                    unzipForEach.unZip(StudentFile, assignementFile);
                    String[] studentFile = file.split("-");
                    //need to see the folder name for parse structure
                    studentName = studentFile[2].substring(1,studentFile[2].length()-1);
                    for(StudentInfo student : Students)
                    {
                        System.out.printf("%s,%s\n",studentName,student.name);
                        if(student.name.equals(studentName))
                            studentNo = student.orgDefinedId;
                    }
                    assignCode = new File("src/main/java/com/example/StudentCode/AssignmentCode").listFiles()[0];
                    System.out.println(assignCode.getPath());
                    pkgpath = assignCode.getPath() + "/src/cp213";

                    students.put(studentName, new ForEach(studentNo, studentName, ParameterMap, pkgpath));


                }
                else
                {
                    new File("src/main/java/com/example/StudentCode/EachAssignment/" + file).delete();
                }
            }
            System.out.println("Files moved to: " + Files.move(individualFiles.toPath(),new File("src/main/resources/MainFile/" + individualFiles.getName()).toPath()));




        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
class ForEach{
    private final String studentNo;
    private final String name;

    /**
     * Runs for each student/submission
     * @param studentNo Student Number
     * @param name Student First and Last Name
     */
    public ForEach(String studentNo, String name, HashMap<String,Parameters> ParameterMap, String pkgpath) throws IOException {
        new File("src/main/java/com/example/StudentCode/StudentGrades/").mkdirs();
        File StuOutput = new File("src/main/java/com/example/StudentCode/StudentGrades/" + name + "_" + studentNo + ".txt");




        StudentMarkPrinter = new PrintStream(new FileOutputStream(StuOutput));
        System.out.println(pkgpath);
        this.studentNo = studentNo;
        this.name = name;

        File f = new File(pkgpath);

        for(File individualFiles : f.listFiles())
        {
            if(!individualFiles.getName().contains("Main"))
            System.out.println("Files moved to: " + Files.move(individualFiles.toPath(),new File("src/main/java/cp213/" + individualFiles.getName()).toPath()));
            else
                individualFiles.delete();
        }
        File l = new File("src" + File.separator+pkgpath.substring(3).replaceFirst("src","bin"));

        for(File individualFiles : l.listFiles())
        {
            if(!individualFiles.getName().contains("Main"))
            System.out.println("Files moved to: " + Files.move(individualFiles.toPath(),new File("target/classes/cp213/" + individualFiles.getName()).toPath()));
        }
        f = new File("src/main/java/cp213");
        System.out.println(f.getPath());
        String[] studentFiles = f.list();
        ArrayList<String> studentClassFiles = new ArrayList<>();
        for(String string : studentFiles)
            studentClassFiles.add( f.getPath() + "/" + string);
        String[] strings = new String[studentFiles.length];
        strings = studentClassFiles.toArray(strings);
        markStudent(ParameterMap,strings);

        f = new File("src/main/java/cp213");//Executed .java Files
        for(File individualFiles : f.listFiles())
        {
            if(!(individualFiles.getName().contains("Main") || individualFiles.getName().contains("TestFile")))
                System.out.println("File Deleted: " + individualFiles.delete());


        }
        l = new File("target/classes/cp213/");//Executed .class Files
        for(File individualFiles : l.listFiles())
        {
            if(!(individualFiles.getName().contains("Main") || individualFiles.getName().contains("TestFile")))
                System.out.println("File Deleted: " + individualFiles.delete());
        }

        FileUtils.deleteDirectory(new File("src/main/java/com/example/StudentCode/AssignmentCode").listFiles()[0]);
        new File("src/main/java/com/example/StudentCode/EachAssignment").listFiles()[0].delete();



    }
    public void markStudent(HashMap<String,Parameters> ParameterMap, String[] filepaths) {
        try {

            int totalmarks = 0;
            int overallgrade = 0;
            String Main="";
            HashMap<String, ReservedWordCounter> Reflection = new HashMap<>();
            for(String file : filepaths)
            {

                if(file.contains("Main"))
                    Main = file;
                else if(file.contains("TestFile"))
                ;
                else
                    Reflection.putAll(RWCDriver.drive(file));
            }
            PrintStream stdout = System.out;
            PrintWriter printWriter = new PrintWriter("src/main/java/com/example/TextFile.txt");
            System.out.println(Main);
            File mainFile = new File(Main);
            System.out.println("MainFile path = "+new File(Main).getPath() + " " + Boolean.toString(mainFile.exists()));
            Main = Main.substring(Main.lastIndexOf("cp213"),Main.lastIndexOf(".")).replace('/','.');
            System.out.println(Main);
            String[] params = null;
            for(String s : mainFile.getParentFile().list())
            {
                System.out.println(s);
            }
            Class<?> c = Class.forName(Main);

            c.getDeclaredMethod("main",String[].class).invoke(null,(Object) params);//Runs the "Main" file
            TxtParser txt = new TxtParser();

            String[][] txtParse = txt.parseFile(new File("src/main/java/com/example/TextFile.txt"));//Parses the output

            //Removes the extraneous Lines
            String[][] txtParsed = txt.parse2dArray(txtParse);
            System.setOut(stdout);

            //Actually marks the assignments
            GradeObject tObject = marker.mark(txtParsed, Reflection, ParameterMap);
            //Prints Output
            //Need to Create Set from keys to hold methodnames
            String[] s = new String[ParameterMap.size()];
            ParameterMap.keySet().toArray(s);
            HashMap<String, Integer> MarkSubtotals = new HashMap<>();
            HashMap<String, Integer> MarkTotals = new HashMap<>();
            // Marks Assignement

            for (int i = 0; i < ParameterMap.size(); i++) {
                System.setOut(StudentMarkPrinter);
                MarkSubtotals.put(s[i], 0);
                MarkTotals.put(s[i], 0);
                System.out.println("Testing method " + s[i] + ":");
                for (int j = 0; j < tObject.getSize(); j++) {
                    if (tObject.getMethodName(j).equals(s[i])) {
                        System.out.printf("%s%100d/%d\n", tObject.getComment(j), tObject.getGrade(j), tObject.getTotalGrade(j));
                        MarkSubtotals.put(tObject.getMethodName(j), MarkSubtotals.get(tObject.getMethodName(j)) + tObject.getGrade(j));
                        MarkTotals.put(tObject.getMethodName(j), MarkTotals.get(tObject.getMethodName(j)) + tObject.getTotalGrade(j));
                    }

                }

                System.out.printf("\n%s%85d/%d\n", "Weighted Mark: ", MarkSubtotals.get(s[i]) * ParameterMap.get(s[i]).getWeight(), MarkTotals.get(s[i]) * ParameterMap.get(s[i]).getWeight());
                System.out.println("-".repeat(100));
            }
            System.out.println("=".repeat(100));

            for (int i = 0; i < MarkTotals.size(); i++) {
                totalmarks += MarkTotals.get(s[i]) * ParameterMap.get(s[i]).getWeight();
                overallgrade += MarkSubtotals.get(s[i]) * ParameterMap.get(s[i]).getWeight();
            }

            System.out.printf("\nTotal grade: %87d/%d\n%103.2f", overallgrade, totalmarks, (double) overallgrade / totalmarks * 100);
            System.out.println("%");
            System.setOut(CSVPrinter);
            System.out.printf("#%s,%d,#\n",studentNo,overallgrade);
            System.setOut(stdout);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
class StudentInfo
{
    public final String orgDefinedId;
    public final String name;
    public StudentInfo(String infoString)
    {
        String[] idList = infoString.split(",");
        orgDefinedId = idList[0].substring(1);
        name = idList[2] + " " + idList[1];
    }

}

