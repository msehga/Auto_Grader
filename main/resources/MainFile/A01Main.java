package cp213;

/**
 * Sample tests for the Assignment 1 class methods. Not comprehensive - add your
 * own testing.
 *
 * @author Your name and id here
 * @version 2022-09-23
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class A01Main {
    // Constants
    private static final String LINE = "-".repeat(40);
    private static final String TEST_LINE = "=".repeat(80);

    public static final String CIPHERTEXT = "AVIBROWNZCEFGHJKLMPQSTUXYD"; // for testing substitute method

    public static void main(String[] args) {

        String outputFilePath = "src/main/java/com/example/TextFile.txt"; // Specify the output file path

        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath))) {
            writer.println("Assignment 1 Methods Tests");
            writer.println();
            writer.println("Tests are of the form:");
            writer.println("  Test Operation\n  {expected value}: actual value");
            writer.println();

            testIsLeapYear(writer);
            testIsPalindrome(writer);
            testIsValid(writer);
            testPigLatin(writer);
            testClosest(writer);
            testSumPartialHarmonic(writer);
            testAllDigits(writer);
            testValidSn(writer);
            testIsPrime(writer);
            testShift(writer);
            testSubstitute(writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test isLeapYear.
     */
    private static void testIsLeapYear(PrintWriter writer) {
        writer.println(TEST_LINE);
        writer.println("Testing isLeapYear");
        writer.println(LINE);

        Object[][] testData = { { 1900, false }, { 1996, true }, { 1999, false }, { 2000, true } };

        for (Object[] data : testData) {
            int year = (int) data[0];
            boolean expected = (boolean) data[1];
            boolean actual = LeapYear.isLeapYear(year);
            writer.println("isLeapYear"+"|" +year + "|"+expected + "|" + actual);
            writer.println();
        }
        writer.println();
    }

    /**
     * Test isPalindrome.
     */
    private static void testIsPalindrome(PrintWriter writer) {
        writer.println(TEST_LINE);
        writer.println("Testing isPalindrome");
        writer.println(LINE);

        Object[][] testData = { { "racecar", true }, { "A man, a plan, a canal, Panama!", true }, { "David", false } };

        for (Object[] data : testData) {
            String string = (String) data[0];
            boolean expected = (boolean) data[1];
            boolean actual = Strings.isPalindrome(string);
            writer.println("isPalindrome"+"|"+string +"|"+expected + "|" + actual);
            writer.println();
        }
        writer.println();
    }
    private static void testIsValid(PrintWriter writer) {
    	writer.println(TEST_LINE);
    	writer.println("Testing isValid");
    	writer.println(LINE);

    	Object[][] testData = { { "a", true }, { "_a", true }, { "1a", false } };

    	for (Object[] data : testData) {
    	    String string = (String) data[0];
    	    boolean expected = (boolean) data[1];
    	    boolean actual = Strings.isValid(string);
    	    writer.println("isValid"+"|"+string +"|"+expected + "|" + actual);
    	    writer.println();
    	}
    	writer.println();
        }
    /**
     * Test pigLatin.
     */
    private static void testPigLatin(PrintWriter writer) {
	writer.println(TEST_LINE);
	writer.println("Testing pigLatin");
	writer.println(LINE);

	Object[][] testData = { { "David", "Avidday" }, { "arrow", "arrowway" }, { "yard", "ardyay" } };

	for (Object[] data : testData) {
	    String string = (String) data[0];
	    String expected = (String) data[1];
	    String actual = Strings.pigLatin(string);
	    
	    writer.println("pigLatin"+"|"+string +"|"+expected + "|" + actual);
	    writer.println();
	}
	writer.println();
    }
    /**
     * Test closest.
     */
    private static void testClosest(PrintWriter writer) {
	writer.println(TEST_LINE);
	writer.println("Testing closest");
	writer.println(LINE);

	Object[][] testData = { { 0.0, -5.0, 5.0, -5.0 }, { 0.0, -10.0, 5.0, 5.0 } };

	for (Object[] data : testData) {
	    double target = (double) data[0];
	    double v1 = (double) data[1];
	    double v2 = (double) data[2];
	    double expected = (double) data[3];
	    double actual = Numbers.closest(target, v1, v2);
	    writer.println("closest"+"|"+String.format("closest(%.1f, %.1f, %.1f)", target, v1, v2) +"|"+expected + "|" + actual);
	    writer.println();
	}
	writer.println();
    }
    /**
     * Test isPrime.
     */
    private static void testIsPrime(PrintWriter writer) {
	writer.println(TEST_LINE);
	writer.println("Testing isPrime");
	writer.println(LINE);

	Object[][] testData = { { 7, true }, { 5, true }, { 9, false } };

	for (Object[] data : testData) {
	    int n = (int) data[0];
	    boolean expected = (boolean) data[1];
	    boolean actual = Numbers.isPrime(n);
	    writer.println("isPrime" + "|"+String.format("isPrime(%d)\n", n)+"|"+expected + "|" + actual);
	    writer.println();
	}
	writer.println();
    }

    /**
     * Test sumPartialHarmonic.
     */
    private static void testSumPartialHarmonic(PrintWriter writer) {
	writer.println(TEST_LINE);
	writer.println("Testing sumPartialHarmonic");
	writer.println(LINE);

	Object[][] testData = { { 0, 0.0 }, { 1, 1.0 }, { 8, 2.7178571428571425 } };

	for (Object[] data : testData) {
	    int n = (int) data[0];
	    double expected = (double) data[1];
	    double actual = Numbers.sumPartialHarmonic(n);
	    writer.println("sumPartialHarmonic"+"|"+String.format("sumPartialHarmonic(%d)\n", n)+"|"+expected + "|" + actual );
	    writer.println();
	}
	writer.println();
    }
    /**
     * Test allDigits.
     */
    private static void testAllDigits(PrintWriter writer) {
	writer.println(TEST_LINE);
	writer.println("Testing allDigits");
	writer.println(LINE);

	Object[][] testData = { { "a", false }, { "123", true }, { "12.3", false } };

	for (Object[] data : testData) {
	    String string = (String) data[0];
	    boolean expected = (boolean) data[1];
	    boolean actual = SerialNumber.allDigits(string);
	    writer.println("allDigits"+"|"+string +"|"+expected + "|" + actual);
	    writer.println();
	}
	writer.println();
    }
    /**
     * Test validSn.
     */
    private static void testValidSn(PrintWriter writer) {
	writer.println(TEST_LINE);
	writer.println("Testing validSn");
	writer.println(LINE);

	Object[][] testData = { { "SN/1234-567", true }, { "SN/1234567", false }, { "SN/123-4567", false } };

	for (Object[] data : testData) {
	    String string = (String) data[0];
	    boolean expected = (boolean) data[1];
	    boolean actual = SerialNumber.validSn(string);
	    writer.println("validSn"+"|"+string +"|"+expected + "|" + actual);
	    writer.println();
	}
	writer.println();
    }
    /**
     * Test shift.
     */
    private static void testShift(PrintWriter writer) {
	writer.println(TEST_LINE);
	writer.println("Testing shift");
	writer.println(LINE);

	Object[][] testData = { { "ABC", 0, "ABC" }, { "ABC", 3, "DEF" }, { "ABC", 30, "EFG" } };

	for (Object[] data : testData) {
	    String string = (String) data[0];
	    int n = (int) data[1];
	    String expected = (String) data[2];
	    String actual = Cipher.shift(string, n);
	    writer.println("shift"+"|"+string +"|"+expected + "|" + actual);
	}
	writer.println();
    }


    /**
     * Test substitute.
     */
    private static void testSubstitute(PrintWriter writer) {
        writer.println(TEST_LINE);
        writer.println("Testing substitute");
        writer.println(LINE);

        Object[][] testData = { { "ABC", "AVI" }, { "XYZ", "XYD" } };

        for (Object[] data : testData) {
            String string = (String) data[0];
            String expected = (String) data[1];
            String actual = Cipher.substitute(string, CIPHERTEXT);
            writer.println("substitute"+"|"+string+"|"+expected + "|" + actual + "|true");
            writer.println();
        }
        writer.println();
    }
}
