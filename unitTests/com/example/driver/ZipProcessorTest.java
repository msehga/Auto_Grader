package com.example.driver;

import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

class ZipProcessorTest {

    // Format "test' + method being tested + success/failure + "When" + scenario

    // Input is teh zip file
    // The output is the unzipped file

    @Test
    void testUnZipSuccessfulWhenValidZip() {
        //Given
        File inFile = new File("unitTests/com/example/driver/resources/inDir/zipProcessorTxtTest.zip");
        File outFile = new File("unitTests/com/example/driver/resources/outDir");
        ZipProcessor zipProcessor = new ZipProcessor();

        // When
        boolean result = zipProcessor.unZip(inFile, outFile);

        // Then
        assertTrue(result);

        // Touch up
        File testFile = new File("unitTests/com/example/driver/resources/outDir/zipProcessorTxtTest.txt");
        if (testFile.exists()){
            testFile.delete();
        }
    }

    @Test
    void testUnZipSuccessfulWhenMovedToOutDirectory() {
        //Given
        File inFile = new File("unitTests/com/example/driver/resources/inDir/zipProcessorTxtTest.zip");
        File outFile = new File("unitTests/com/example/driver/resources/outDir");
        ZipProcessor zipProcessor = new ZipProcessor();

        // When
        boolean result = zipProcessor.unZip(inFile, outFile);

        // Then
        File testFile = new File("unitTests/com/example/driver/resources/outDir/zipProcessorTxtTest.txt");
        if (!testFile.exists()){
            fail("File does not exist in the out directory");
        }

        // Touch up
        testFile.delete();
    }

    @Test
    void testUnZipFailureWhenZipNotInputted() {
        //Given
        File inFile = new File("unitTests/com/example/driver/resources/inDir/wrongName");
        File outFile = new File("unitTests/com/example/driver/resources/outDir");
        ZipProcessor zipProcessor = new ZipProcessor();

        // When
        boolean result = zipProcessor.unZip(inFile, outFile);

        // Then
        assertFalse(result);
    }

    @Test
    void testUnZipFailureWhenWrongPathGiven() {
        //Given
        File inFile = new File("unitTests/com/example/driver/resources/inDir/wrongName.zip");
        File outFile = new File("unitTests/com/example/driver/resources/outDir");
        ZipProcessor zipProcessor = new ZipProcessor();

        // When
        boolean result = zipProcessor.unZip(inFile, outFile);

        // Then
        assertFalse(result);
    }

}