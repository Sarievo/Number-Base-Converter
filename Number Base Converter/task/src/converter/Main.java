package converter;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter two numbers in format: {source base} {target base} (To quit type /exit) ");
            String choice = sc.next();
            if (!choice.equals("/exit")) {
                int targetBase = sc.nextInt();
                conversion(choice, targetBase);
            } else break;
        }
    }

    private static void conversion(String sourceBase, int targetBase) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.printf("Enter number in base %s to convert to base %s (To go back type /back) ", sourceBase, targetBase);
            String choice = sc.nextLine();
            if (!choice.equals("/back")) {
                if (choice.contains(".")) {
                    String[] parts = choice.split("\\.");
                    choice = parts[0];

                    BigInteger decimal = new BigInteger(choice, Integer.parseInt(sourceBase));
                    String fraPart = fractionalConvert(parts[1], sourceBase, targetBase);
                    System.out.printf("Conversion result: %s.%s\n\n", decimal.toString(targetBase), fraPart);
                } else {
                    BigInteger decimal = new BigInteger(choice, Integer.parseInt(sourceBase));
                    System.out.printf("Conversion result: %s\n\n", decimal.toString(targetBase));
                }
            } else break;
        }
    }

    private static String fractionalConvert(String fraPart, String sourceBase, int targetBase) {
        int times = 1;
        double fractional = 0;
        for (char e : fraPart.toCharArray()) {
            int i = Character.getNumericValue(e);
            fractional += i / (Math.pow(Double.parseDouble(sourceBase), times));
            times++;
        }

        StringBuilder targetResult = new StringBuilder();
        while (targetResult.length() < 5) {
            double deci = fractional * targetBase;
            int i = (int) Math.floor(deci);
            fractional = deci - i;

            if (i > 9) {
                targetResult.append(Character.forDigit(i, targetBase));
            } else targetResult.append(i);
        }
        return targetResult.toString();
    }
}