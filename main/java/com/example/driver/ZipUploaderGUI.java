package com.example.driver;

import org.apache.commons.io.FileUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ZipUploaderGUI extends JFrame {

    private static final String DESTINATION_FOLDER = "src/main/java/com/example/StudentCode/StudentFiles/";
    private static final String MAINFILE_Folder = "src/main/java/com/example/StudentCode/SampleMain";
    private static final String WEIGHTFILEFOLDER = "src/main/resources/static";
    private static final String CSVEXPORTEDFOLDER = "src/main/java/com/example/StudentCode/GradeExported";
    private static boolean assignments= false;
    private static boolean mainadded = false;
    private static boolean weightadded = false;
    private static boolean csvAdded = false;
    public JLabel label = null;

    public ZipUploaderGUI() {
        // Set up the main application window
        setTitle("Zip File Uploader");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create the button to upload the zip file
        JButton uploadButton = new JButton("Upload Student Assignments .zip from MyLS");
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File file) {
                        return file.getName().toLowerCase().endsWith(".zip") || file.isDirectory();
                    }

                    @Override
                    public String getDescription() {
                        return "Zip files (*.zip)";
                    }
                });

                int returnValue = fileChooser.showOpenDialog(ZipUploaderGUI.this);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    uploadZip(file);
                    assignments = true;
                }
            }
        });

        // Add the button to the main window
        JPanel panel = new JPanel();
        panel.setSize(500,700);
        panel.setLayout(new GridLayout(3,1,20,25));
        panel.add(uploadButton);
        getContentPane().add(panel);
        JButton csvFolder = new JButton("Exported Grade CSV");
        csvFolder.addActionListener(new csvExport());

        panel.add(csvFolder);
        JButton mainFileButton = new JButton("Sample Assignment .zip File");
        mainFileButton.addActionListener(new MainFileListener());
        panel.add(mainFileButton);
        JButton goButton = new JButton("Run");
        goButton.addActionListener(new run());
        panel.add(goButton);
        JButton weights = new JButton("Weight and Parameters");
        weights.addActionListener(new WeightFileListener());
        panel.add(weights);
        label = new JLabel();
        panel.add(label);

        // Show the GUI
        pack();
        setVisible(true);
    }
    private void uploadZip(File zipFile) {
        File destinationFolder = new File(DESTINATION_FOLDER);
        destinationFolder.mkdirs();
        try {
            Path destinationPath = destinationFolder.toPath().resolve(zipFile.getName());
            Files.copy(zipFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Zip file successfully uploaded.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
    private class MainFileListener implements ActionListener{
        @Override
        public void actionPerformed(final ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            File zipFile = null;
            fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                @Override
                public boolean accept(File file) {
                    return file.getName().toLowerCase().endsWith(".zip") || file.isDirectory();
                }

                @Override
                public String getDescription() {
                    return "Zip files (*.zip)";
                }
            });

            int returnValue = fileChooser.showOpenDialog(ZipUploaderGUI.this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                zipFile = fileChooser.getSelectedFile();

            }
            File destinationFolder = new File(MAINFILE_Folder);
            destinationFolder.mkdirs();
            try {

                ZipProcessor z = new ZipProcessor();
                z.unZip(zipFile,destinationFolder);
                System.out.println("Zip file successfully uploaded.");
                File javaFile = new File(destinationFolder.listFiles()[0].getPath() + "/src/cp213");
                new File("src/main/resources/MainFile").mkdirs();
                for(File f : javaFile.listFiles())
                {
                    if(f.getName().contains("Main") || f.getName().contains("main"))

                    {
                        if(new File("src/main/resources/MainFile").listFiles().length >0)
                        new File("src/main/resources/MainFile").listFiles()[0].delete();
                        Files.move(f.toPath(),new File("src/main/resources/MainFile/" + f.getName()).toPath());
                    }

                }
                File classFile = new File(destinationFolder.listFiles()[0].getPath() + "/bin/cp213");
                for(File f : classFile.listFiles())
                {
                    if(f.getName().contains("Main") || f.getName().contains("main"))
                    {
                        if(new File("target/classes/cp213").listFiles() != null)
                        new File("target/classes/cp213").listFiles()[0].delete();
                        Files.move(f.toPath(),new File("target/classes/cp213/" + f.getName()).toPath());
                    }

                }
                mainadded = true;
            } catch (IOException exception) {
                exception.printStackTrace();
                System.out.println("Error: " + exception.getMessage());
            }
        }

    }
    private class WeightFileListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                @Override
                public boolean accept(File file) {
                    return file.getName().toLowerCase().endsWith(".txt") || file.isDirectory();
                }

                @Override
                public String getDescription() {
                    return "Txt Files (*.txt)";
                }
            });

            int returnValue = fileChooser.showOpenDialog(ZipUploaderGUI.this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                File destinationFolder = new File(WEIGHTFILEFOLDER);
                destinationFolder.mkdirs();
                try {
                    Path destinationPath = destinationFolder.toPath().resolve(file.getName());
                    Files.copy(file.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
                    File f = destinationPath.toFile();
                    System.out.println("File deleting on exit" + f.getName());
                    f.deleteOnExit();
                    f.renameTo(new File(f.getParentFile().toPath()+"/SampleWeights.txt"));
                    System.out.println("Text file successfully uploaded.");
                    weightadded = true;
                } catch (IOException exception) {
                    exception.printStackTrace();
                    System.out.println("Error: " + exception.getMessage());
                }
            }

        }
    }
    private class csvExport implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                @Override
                public boolean accept(File file) {
                    return file.getName().toLowerCase().endsWith(".csv") || file.isDirectory();
                }

                @Override
                public String getDescription() {
                    return "CSV Files (*.csv)";
                }
            });

            int returnValue = fileChooser.showOpenDialog(ZipUploaderGUI.this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                File destinationFolder = new File("src/main/java/com/example/StudentCode/GradeExported");
                destinationFolder.mkdirs();
                try {
                    Path destinationPath = destinationFolder.toPath().resolve(file.getName());
                    Files.copy(file.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
                    File f = destinationPath.toFile();
                    System.out.println("File deleting on exit" + f.getName());
                    f.deleteOnExit();
                    System.out.println("Text file successfully uploaded.");
                    csvAdded = true;
                } catch (IOException exception) {
                    exception.printStackTrace();
                    System.out.println("Error: " + exception.getMessage());
                }
            }

        }
    }
    private class run implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            label.setForeground(Color.red);
            if(weightadded && assignments && mainadded && csvAdded) {
                label.setText("");
                mainfile.driver();
                try {
                    FileUtils.deleteDirectory(new File("src/main/java/com/example/StudentCode/SampleMain").listFiles()[0]);
                    new File("src/main/java/com/example/StudentCode/StudentFiles").listFiles()[0].delete();
                    getFrames()[0].dispatchEvent(new WindowEvent(getFrames()[0], WindowEvent.WINDOW_CLOSING));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            } else if (!csvAdded) {
                label.setText("Please add an exported grade file");

            } else if(!assignments)
                label.setText("Please add the student subission zip file");
            else if (!mainadded)
                label.setText("Please add a completed sample assignment");
            else if(!weightadded)
                label.setText("Please add a weights file");

        }

    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                ZipUploaderGUI z = new ZipUploaderGUI();


            }
        });
    }
}



