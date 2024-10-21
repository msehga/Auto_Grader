package com.example.driver;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TxtParserTest {

    @Test
    void testParseFileFailureWhenWrongFileIsInputted() {
        //Given
        File inFile = new File("unitTests/com/example/driver/resources/inDir/wrongName");
        TxtParser txtParser = new TxtParser();

        // When
        String[][] result = txtParser.parseFile(inFile);

        // Then
        assertNull(result);
    }

    @Test
    void testParseFileSuccessfulWhenFileIsInputted() {
        //Given
        String[][] validOutput = {
                {"Assignment 1 Methods Tests"},
                {""},
                {"Tests are of the form:"},
                {"  Test Operation"},
                {"  {expected value}: actual value"},
                {"================================================================================"},
                {"Testing isLeapYear"},
                {"----------------------------------------"},
                {"isLeapYear", "1900", "false", "false"},
                {"isLeapYear", "1996", "true", "true"},
                {"isLeapYear", "1999", "false", "false"},
                {"isLeapYear", "2000", "true", "true"},
                {"Testing isPalindrome"},
                {"isPalindrome", "racecar", "true", "true"},
                {"isPalindrome", "A man, a plan, a canal, Panama!", "true", "true"},
                {"isPalindrome", "David", "false", "false"},
                {"Testing isValid"},
                {"isValid", "a", "true", "true"},
                {"isValid", "_a", "true", "true"},
                {"isValid", "1a", "false", "false"},
                {"Testing pigLatin"},
                {"pigLatin", "David", "Avidday", "Avidday"},
                {"pigLatin", "arrow", "arrowway", "arrowway"},
                {"pigLatin", "yard", "ardyay", "ardyay"},
                {"Testing closest"},
                {"closest", "closest(0.0, -5.0, 5.0)", "-5.0", "-5.0"},
                {"closest", "closest(0.0, -10.0, 5.0)", "5.0", "5.0"},
                {"Testing sumPartialHarmonic"},
                {"sumPartialHarmonic", "sumPartialHarmonic(0)"},
                {"", "0.0", "0.0"},
                {"sumPartialHarmonic", "sumPartialHarmonic(1)"},
                {"", "1.0", "1.0"},
                {"sumPartialHarmonic", "sumPartialHarmonic(8)"},
                {"", "2.7178571428571425", "2.7178571428571425"},
                {"Testing allDigits"},
                {"allDigits", "a", "false", "false"},
                {"allDigits", "123", "true", "true"},
                {"allDigits", "12.3", "false", "false"},
                {"Testing validSn"},
                {"validSn", "SN/1234-567", "true", "true"},
                {"validSn", "SN/1234567", "false", "false"},
                {"validSn", "SN/123-4567", "false", "false"},
                {"Testing isPrime"},
                {"isPrime", "isPrime(7)"},
                {"", "true", "true"},
                {"isPrime", "isPrime(5)"},
                {"isPrime", "isPrime(9)"},
                {"", "false", "false"},
                {"Testing shift"},
                {"shift", "ABC", "ABC", "ABC"},
                {"shift", "ABC", "DEF", "DEF"},
                {"shift", "ABC", "EFG", "EFG"},
                {"Testing substitute"},
                {"substitute", "ABC", "AVI", "AVI", "true"},
                {"substitute", "XYZ", "XYD", "XYD", "true"}
        };
        File inFile = new File("unitTests/com/example/driver/resources/TextFile.txt");
        TxtParser txtParser = new TxtParser();

        // When
        String[][] result = txtParser.parseFile(inFile);

        // Then
        assertArrayEquals(result,validOutput);
    }

    @Test
    void testParseFileSuccessfulWhenFileIsInputted2() {
        //Given
        String[][] validOutput = {
                {"Assignment 1 Methods Tests"},
                {""},
                {"Tests are of the form:"},
                {"  Test Operation"},
                {"  {expected value}: actual value"},
                {"================================================================================"},
                {"Testing isLeapYear"},
                {"----------------------------------------"},
                {"isLeapYear", "1900", "false", "false"},
                {"isLeapYear", "1996", "true", "false"},
                {"isLeapYear", "1999", "false", "false"},
                {"isLeapYear", "2000", "true", "false"},
                {"Testing isPalindrome"},
                {"isPalindrome", "racecar", "true", "false"},
                {"isPalindrome", "A man, a plan, a canal, Panama!", "true", "true"},
                {"isPalindrome", "David", "false", "true"},
                {"Testing isValid"},
                {"isValid", "a", "true", "true"},
                {"isValid", "_a", "true", "true"},
                {"isValid", "1a", "false", "true"},
                {"Testing pigLatin"},
                {"pigLatin", "David", "Avidday", "Avidday"},
                {"pigLatin", "arrow", "arrowway", "arrowway"},
                {"pigLatin", "yard", "ardyay", "ardyay"},
                {"Testing closest"},
                {"closest", "closest(0.0, -5.0, 5.0)", "-5.0", "-5.0"},
                {"closest", "closest(0.0, -10.0, 5.0)", "5.0", "5.0"},
                {"Testing sumPartialHarmonic"},
                {"sumPartialHarmonic", "sumPartialHarmonic(0)"},
                {"", "0.0", "0.0"},
                {"sumPartialHarmonic", "sumPartialHarmonic(1)"},
                {"", "1.0", "1.0"},
                {"sumPartialHarmonic", "sumPartialHarmonic(8)"},
                {"", "2.7178571428571425", "2.7178571428571425"},
                {"Testing allDigits"},
                {"allDigits", "a", "false", "false"},
                {"allDigits", "123", "true", "true"},
                {"allDigits", "12.3", "false", "false"},
                {"Testing validSn"},
                {"validSn", "SN/1234-567", "true", "true"},
                {"validSn", "SN/1234567", "false", "false"},
                {"validSn", "SN/123-4567", "false", "false"},
                {"Testing isPrime"},
                {"isPrime", "isPrime(7)"},
                {"", "true", "true"},
                {"isPrime", "isPrime(5)"},
                {"isPrime", "isPrime(9)"},
                {"", "false", "false"},
                {"Testing shift"},
                {"shift", "ABC", "ABC", "ABC"},
                {"shift", "ABC", "DEF", "DEF"},
                {"shift", "ABC", "EFG", "EFG"},
                {"Testing substitute"},
                {"substitute", "ABC", "AVI", "AVI", "true"},
                {"substitute", "XYZ", "XYD", "XYD", "true"}
        };
        File inFile = new File("unitTests/com/example/driver/resources/TextFile2.txt");
        TxtParser txtParser = new TxtParser();

        // When
        String[][] result = txtParser.parseFile(inFile);

        // Then
        assertArrayEquals(result,validOutput);
    }

    @Test
    void testParseFileFailureWhenNoFileIsInputted() {
        //Given
        File inFile = new File("unitTests/com/example/driver/resources/inDir");
        TxtParser txtParser = new TxtParser();

        // When
        String[][] result = txtParser.parseFile(inFile);

        // Then
        assertNull(result);
    }

    @Test
    void testParseFileFailureWhenFileIsIncorrectPath() {
        //Given
        File inFile = new File("unitTests/com/example/driver/resource");
        TxtParser txtParser = new TxtParser();

        // When
        String[][] result = txtParser.parseFile(inFile);

        // Then
        assertNull(result);
    }

    @Test
    void testParse2dArrayFailureWhenInValidInput() {
        //Given
        String[][] inValidInput = {{"Assignment 1 Methods Tests"}};
        String[][] validOutput = {};
        TxtParser txtParser = new TxtParser();

        // When
        String[][] result = txtParser.parse2dArray(inValidInput);

        // Then
        assertArrayEquals(result,validOutput);
    }

    @Test
    void testParse2dSuccessfulWhenValidInputted() {
        //Given
        String[][] input = {
                {"Assignment 1 Methods Tests"},
                {""},
                {"Tests are of the form:"},
                {"  Test Operation"},
                {"  {expected value}: actual value"},
                {"================================================================================"},
                {"Testing isLeapYear"},
                {"----------------------------------------"},
                {"isLeapYear", "1900", "false", "false"},
                {"isLeapYear", "1996", "true", "true"},
                {"isLeapYear", "1999", "false", "false"},
                {"isLeapYear", "2000", "true", "true"},
                {"Testing isPalindrome"},
                {"isPalindrome", "racecar", "true", "true"},
                {"isPalindrome", "A man, a plan, a canal, Panama!", "true", "true"},
                {"isPalindrome", "David", "false", "false"},
                {"Testing isValid"},
                {"isValid", "a", "true", "true"},
                {"isValid", "_a", "true", "true"},
                {"isValid", "1a", "false", "false"},
                {"Testing pigLatin"},
                {"pigLatin", "David", "Avidday", "Avidday"},
                {"pigLatin", "arrow", "arrowway", "arrowway"},
                {"pigLatin", "yard", "ardyay", "ardyay"},
                {"Testing closest"},
                {"closest", "closest(0.0, -5.0, 5.0)", "-5.0", "-5.0"},
                {"closest", "closest(0.0, -10.0, 5.0)", "5.0", "5.0"},
                {"Testing sumPartialHarmonic"},
                {"sumPartialHarmonic", "sumPartialHarmonic(0)"},
                {"", "0.0", "0.0"},
                {"sumPartialHarmonic", "sumPartialHarmonic(1)"},
                {"", "1.0", "1.0"},
                {"sumPartialHarmonic", "sumPartialHarmonic(8)"},
                {"", "2.7178571428571425", "2.7178571428571425"},
                {"Testing allDigits"},
                {"allDigits", "a", "false", "false"},
                {"allDigits", "123", "true", "true"},
                {"allDigits", "12.3", "false", "false"},
                {"Testing validSn"},
                {"validSn", "SN/1234-567", "true", "true"},
                {"validSn", "SN/1234567", "false", "false"},
                {"validSn", "SN/123-4567", "false", "false"},
                {"Testing isPrime"},
                {"isPrime", "isPrime(7)"},
                {"", "true", "true"},
                {"isPrime", "isPrime(5)"},
                {"isPrime", "isPrime(9)"},
                {"", "false", "false"},
                {"Testing shift"},
                {"shift", "ABC", "ABC", "ABC"},
                {"shift", "ABC", "DEF", "DEF"},
                {"shift", "ABC", "EFG", "EFG"},
                {"Testing substitute"},
                {"substitute", "ABC", "AVI", "AVI", "true"},
                {"substitute", "XYZ", "XYD", "XYD", "true"}
        };
        String[][] validOutput = {
                {"isLeapYear", "1900", "false", "false"},
                {"isLeapYear", "1996", "true", "true"},
                {"isLeapYear", "1999", "false", "false"},
                {"isLeapYear", "2000", "true", "true"},
                {"isPalindrome", "racecar", "true", "true"},
                {"isPalindrome", "A man, a plan, a canal, Panama!", "true", "true"},
                {"isPalindrome", "David", "false", "false"},
                {"isValid", "a", "true", "true"},
                {"isValid", "_a", "true", "true"},
                {"isValid", "1a", "false", "false"},
                {"pigLatin", "David", "Avidday", "Avidday"},
                {"pigLatin", "arrow", "arrowway", "arrowway"},
                {"pigLatin", "yard", "ardyay", "ardyay"},
                {"closest", "closest(0.0, -5.0, 5.0)", "-5.0", "-5.0"},
                {"closest", "closest(0.0, -10.0, 5.0)", "5.0", "5.0"},
                {"allDigits", "a", "false", "false"},
                {"allDigits", "123", "true", "true"},
                {"allDigits", "12.3", "false", "false"},
                {"validSn", "SN/1234-567", "true", "true"},
                {"validSn", "SN/1234567", "false", "false"},
                {"validSn", "SN/123-4567", "false", "false"},
                {"shift", "ABC", "ABC", "ABC"},
                {"shift", "ABC", "DEF", "DEF"},
                {"shift", "ABC", "EFG", "EFG"}
        };
        TxtParser txtParser = new TxtParser();

        // When
        String[][] result = txtParser.parse2dArray(input);

        // Then
        assertArrayEquals(result,validOutput);
    }

    @Test
    void testParse2dSuccessfulWhenValidInputted2() {
        //Given
        String[][] input = {
                {"Assignment 1 Methods Tests"},
                {""},
                {"Tests are of the form:"},
                {"  Test Operation"},
                {"  {expected value}: actual value"},
                {"================================================================================"},
                {"Testing isLeapYear"},
                {"----------------------------------------"},
                {"isLeapYear", "1900", "false", "false"},
                {"isLeapYear", "1996", "true", "false"},
                {"isLeapYear", "1999", "false", "false"},
                {"isLeapYear", "2000", "true", "false"},
                {"Testing isPalindrome"},
                {"isPalindrome", "racecar", "true", "false"},
                {"isPalindrome", "A man, a plan, a canal, Panama!", "true", "true"},
                {"isPalindrome", "David", "false", "true"},
                {"Testing isValid"},
                {"isValid", "a", "true", "true"},
                {"isValid", "_a", "true", "true"},
                {"isValid", "1a", "false", "true"},
                {"Testing pigLatin"},
                {"pigLatin", "David", "Avidday", "Avidday"},
                {"pigLatin", "arrow", "arrowway", "arrowway"},
                {"pigLatin", "yard", "ardyay", "ardyay"},
                {"Testing closest"},
                {"closest", "closest(0.0, -5.0, 5.0)", "-5.0", "-5.0"},
                {"closest", "closest(0.0, -10.0, 5.0)", "5.0", "5.0"},
                {"Testing sumPartialHarmonic"},
                {"sumPartialHarmonic", "sumPartialHarmonic(0)"},
                {"", "0.0", "0.0"},
                {"sumPartialHarmonic", "sumPartialHarmonic(1)"},
                {"", "1.0", "1.0"},
                {"sumPartialHarmonic", "sumPartialHarmonic(8)"},
                {"", "2.7178571428571425", "2.7178571428571425"},
                {"Testing allDigits"},
                {"allDigits", "a", "false", "false"},
                {"allDigits", "123", "true", "true"},
                {"allDigits", "12.3", "false", "false"},
                {"Testing validSn"},
                {"validSn", "SN/1234-567", "true", "true"},
                {"validSn", "SN/1234567", "false", "false"},
                {"validSn", "SN/123-4567", "false", "false"},
                {"Testing isPrime"},
                {"isPrime", "isPrime(7)"},
                {"", "true", "true"},
                {"isPrime", "isPrime(5)"},
                {"isPrime", "isPrime(9)"},
                {"", "false", "false"},
                {"Testing shift"},
                {"shift", "ABC", "ABC", "ABC"},
                {"shift", "ABC", "DEF", "DEF"},
                {"shift", "ABC", "EFG", "EFG"},
                {"Testing substitute"},
                {"substitute", "ABC", "AVI", "AVI", "true"},
                {"substitute", "XYZ", "XYD", "XYD", "true"}
        };
        String[][] validOutput = {
            {"isLeapYear", "1900", "false", "false"},
            {"isLeapYear", "1996", "true", "false"},
            {"isLeapYear", "1999", "false", "false"},
            {"isLeapYear", "2000", "true", "false"},
            {"isPalindrome", "racecar", "true", "false"},
            {"isPalindrome", "A man, a plan, a canal, Panama!", "true", "true"},
            {"isPalindrome", "David", "false", "true"},
            {"isValid", "a", "true", "true"},
            {"isValid", "_a", "true", "true"},
            {"isValid", "1a", "false", "true"},
            {"pigLatin", "David", "Avidday", "Avidday"},
            {"pigLatin", "arrow", "arrowway", "arrowway"},
            {"pigLatin", "yard", "ardyay", "ardyay"},
            {"closest", "closest(0.0, -5.0, 5.0)", "-5.0", "-5.0"},
            {"closest", "closest(0.0, -10.0, 5.0)", "5.0", "5.0"},
            {"allDigits", "a", "false", "false"},
            {"allDigits", "123", "true", "true"},
            {"allDigits", "12.3", "false", "false"},
            {"validSn", "SN/1234-567", "true", "true"},
            {"validSn", "SN/1234567", "false", "false"},
            {"validSn", "SN/123-4567", "false", "false"},
            {"shift", "ABC", "ABC", "ABC"},
            {"shift", "ABC", "DEF", "DEF"},
            {"shift", "ABC", "EFG", "EFG"}
        };
        TxtParser txtParser = new TxtParser();

        // When
        String[][] result = txtParser.parse2dArray(input);

        // Then
        assertArrayEquals(result,validOutput);
    }

    @Test
    void testParse2dFileFailureWhenNullIsInputted() {
        //Given
        File inFile = new File("unitTests/com/example/driver/resource");
        TxtParser txtParser = new TxtParser();

        // When
        Exception exception = assertThrows(NullPointerException.class, () -> {
            //Then
            String[][] result = txtParser.parse2dArray(null);
        });
    }
}