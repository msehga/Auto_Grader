package com.example.driver;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class markerTest {

    @Test
    void testParseMarkerFailureWhenInvalidInput() {
        // Given
        marker markerObj = new marker();

        String[][] inValidOutput = {{"Assignment 1 Methods Tests"}};

        // When
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            //Then
            Object obj = (Object) marker.parsemarker(inValidOutput);
        });
    }

    @Test
    void testParseMarkerFailureWhenInvalidInput2() {
        // Given
        marker markerObj = new marker();

        String[][] inValidOutput = {{"Assignment 1 Methods Tests"},
                                    {"test1"},
                                    {"test2"}
        };

        // When
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            Object obj = (Object) marker.parsemarker(inValidOutput);
        });
    }
    @Test
    void testParseMarkerSuccessfulWhenValidInput() {
        // Given
        String[][] input = {
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
        int totalGrade1 = 1;
        String comment1 = "isLeapYear(1900)\n\tfalse:  false";
        String methodName = "isLeapYear";
        int grade1 = 1;
        // When
        GradeObject obj = marker.parsemarker(input);

        assertEquals(totalGrade1,obj.totalGrade[0]);
        assertEquals(comment1,obj.comment[0].trim());
        assertEquals(methodName,obj.methodName[0]);
        assertEquals(grade1,obj.grade[0]);
    }

    @Test
    void testParseMarkerSuccessfulWhenValidInput2() {
        // Given
        String[][] input = {
                {"isNotLeapYear", "1900", "false", "true"},
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
        int totalGrade1 = 1;
        String comment1 = "isNotLeapYear(1900)\n\tfalse:  true";
        String methodName = "isNotLeapYear";
        int grade1 = 0;
        // When
        GradeObject obj = marker.parsemarker(input);

        assertEquals(totalGrade1,obj.totalGrade[0]);
        assertEquals(comment1,obj.comment[0].trim());
        assertEquals(methodName,obj.methodName[0]);
        assertEquals(grade1,obj.grade[0]);
    }

    @Test
    void testParseMarkerFailureWhenInputNull() {
        // Given
        marker markerObj = new marker();

        // When
        Exception exception = assertThrows(NullPointerException.class, () -> {
            Object obj = (Object) marker.parsemarker(null);
        });
    }

}