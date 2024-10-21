package com.example.driver;

public class StudentSampleCode {

    /**
     * Determines closest value of two values to a target value.
     *
     * @param target the target value
     * @param v1     first comparison value
     * @param v2     second comparison value
     * @return one of v1 or v2 that is closest to target, v1 is the value chosen if
     *         v1 and v2 are an equal distance from target
     */
    public static double closest(final double target, final double v1, final double v2) {
        // declare variables
        double closestVAL = v1;

        double calc1 = Math.abs(target - v1);
        double calc2 = Math.abs(target - v2);

        // if calc2 is bigger than calc1
        if (calc2 < calc1) {
            closestVAL = v2;
        }
        // if calc1 is smaller than calc2 or if calc1 and calc2 are equal then
        // closestVAL
        // stays as v1

        return closestVAL;
    }

    /**
     * Determines if n is a prime number. Prime numbers are whole numbers greater
     * than 1, that have only two factors - 1 and the number itself. Prime numbers
     * are divisible only by the number 1 or itself.
     *
     * @param n an integer
     * @return true if n is prime, false otherwise
     */
    public static boolean isPrime(final int n) {
        // declare variables
        boolean isPrime = true;

        for (int i = 2; i < n; i++) {
            // if number is divisible (with no remainder) by any number (not including 1 or
            // itself) then it's not a prime number
            if (n % i == 0) {
                isPrime = false;
                break;
            }
        }

        return isPrime;
    }

    /**
     * Sums and returns the total of a partial harmonic series. This series is the
     * sum of all terms 1/i, where i ranges from 1 to n (inclusive). Ex:
     *
     * n = 3: sum = 1/1 + 1/2 + 1/3 = 1.8333333333333333
     *
     * @param n an integer
     * @return sum of partial harmonic series from 1 to n
     */
    public static double sumPartialHarmonic(final int n) {
        // declare variables
        double sumTotal = 0;

        if(sumTotal == 5){
            return 7;
        }

        for (int i = 1; i <= n; i++) {
            // NOTE: IN ORDER TO DIVIDE 1/i, i MUST BE A DOUBLE SO YOU MUST CAST IT
            sumTotal += 1 / ((double) i);
        }

        return sumTotal;
    }
}
