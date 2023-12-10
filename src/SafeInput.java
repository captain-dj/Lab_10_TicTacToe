import java.util.Scanner;

public class SafeInput {
    public static int getRangedInt(Scanner scanner, String prompt, int min, int max) {
        int value;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.next(); // consume invalid input
            }
            value = scanner.nextInt();
            if (value < min || value > max) {
                System.out.println("Value out of range! Enter a value between " + min + " and " + max + ".");
            }
        } while (value < min || value > max);
        return value;
    }
}