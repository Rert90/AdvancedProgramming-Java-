public class Lab1 {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java Lab1 <a> <b> <k>");
            return;
        }

        int a, b, k;
        try {
            a = Integer.parseInt(args[0]);
            b = Integer.parseInt(args[1]);
            k = Integer.parseInt(args[2]);
            if (a >= b || k < 0 || k > 9) {
                System.err.println("Invalid input. Ensure a < b and 0 <= k <= 9.");
                return;
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid input. Please enter integers for a, b, and k.");
            return;
        }

        long startTime = System.nanoTime();

        StringBuilder result = new StringBuilder();
        for (int i = a; i <= b; i++) {
            if (isKReducible(i, k)) {
                result.append(i).append(" ");
            }
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        System.out.println("K-reductible numbers in the interval [" + a + ", " + b + "] for k = " + k + ":");
        System.out.println(result.toString().trim());
        System.out.println("Running time: " + duration + " nanoseconds");
    }

    private static boolean isKReducible(int num, int k) {
        int sum = num;
        while (sum >= 10) {
            int tempSum = 0;
            while (sum > 0) {
                int digit = sum % 10;
                tempSum += digit * digit;
                sum /= 10;
            }
            sum = tempSum;
        }
        return sum == k;
    }
}
