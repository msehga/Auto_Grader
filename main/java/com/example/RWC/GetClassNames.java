package com.example.RWC;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

public class GetClassNames {
    public static void main(String[] args) {
        // create array to store names of classes
        ArrayList<String> ClassNameList = new ArrayList<>();

        //get the root directory
        String projectRoot = System.getProperty("user.dir");
        projectRoot = projectRoot.replace("\\", "/"); // change all "\" to "/" in file path

        String packagePath = projectRoot + "/src/main/java/com/example/StudentFiles/cp213"; // path to the package we want to get the names from
        //TEST PATH: System.out.println("Project Root Path: " + packagePath);

        try {
            Path rootPath = Paths.get(packagePath); // get the path of the root file using the path we defined above

            /*use the Files.walkFileTree method to traverse the file tree starting from the
            repository root path implement a FileVisitor which specifies the required
            behavior at key points in the traversal*/
            Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    if (file.toString().endsWith(".java")) { // check if file has a .java extension
                        // get the name of the class as a string by calling getClassName method
                        String className = getClassName(file, rootPath);
                        ClassNameList.add(className); // save the name of the class to the Array List
                    }
                    return FileVisitResult.CONTINUE;
                }

            });

            //TEST CLASS NAMES FOUND:
            /*for (String classStr : ClassNameList) {
                System.out.println(classStr);
            }*/

        } catch (IOException e) { // catch exception if finding the root path doesn't work
            e.printStackTrace();
        }

    }

    /*helper method to get the class name based on the root path and the file we
    want to locate*/
    private static String getClassName(Path file, Path rootPath) {
        //constructs a relative path between rootPath and the object which represents the file we want to locate
        Path relativePath = rootPath.relativize(file);
        String className = relativePath.toString().replace(File.separator, ".").replace(".java", "");
        return className;
    }
}
