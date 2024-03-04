package org.example;

public class Lab1 {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Lab1 <a> <b> <k>");
            return;
        }

        // Parse command line arguments
        int a, b, k;
        try {
            a = Integer.parseInt(args[0]);
            b = Integer.parseInt(args[1]);
            k = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid integers for a, b, and k.");
            return;
        }

        // Validate inputs
        if (a >= b) {
            System.out.println("Invalid input. a must be less than b.");
            return;
        }
        if (k < 0) {
            System.out.println("Invalid input. k must be a non-negative integer.");
            return;
        }

        long startTime = System.nanoTime();

        // Find k-reductible numbers
        StringBuilder result = new StringBuilder();
        for (int i = a; i <= b; i++) {
            if (isKReductible(i, k)) {
                result.append(i).append(" ");
            }
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Convert nanoseconds to milliseconds

        System.out.println("K-reductible numbers in [" + a + ", " + b + "] for k = " + k + ":");
        System.out.println(result);
        System.out.println("Execution time: " + duration + " milliseconds");
    }

    // Helper method to check if a number is k-reductible
    private static boolean isKReductible(int num, int k) {
        while (num != k && num > 9) {
            int sum = 0;
            String digits = String.valueOf(num);
            for (char digit : digits.toCharArray()) {
                int digitValue = Character.getNumericValue(digit);
                sum += digitValue * digitValue;
            }
            num = sum;
        }
        return num == k || num == 1;
    }
}
