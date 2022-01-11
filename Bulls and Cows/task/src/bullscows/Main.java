package bullscows;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the length of the secret code:");
        String length = scanner.nextLine();
        if (!length.matches("\\d+") || Integer.parseInt(length) <= 0) {
            System.out.println("Error: " + length + " isn't a valid number.");
        } else {
            System.out.println("Input the number of possible symbols in the code:");
            String possibleNumber = scanner.nextLine();
            if (!possibleNumber.matches("\\d+") || Integer.parseInt(possibleNumber) <= 0) {
                System.out.println("Error: \"" + possibleNumber + "\"a isn't a valid number.");
            } else {
                int intLength = Integer.parseInt(length);
                int intPossibleNumber = Integer.parseInt(possibleNumber);
                if (intPossibleNumber > 36) {
                    System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                } else if (intLength > intPossibleNumber) {
                    System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.", intLength, intPossibleNumber);
                } else {
                    ArrayList<Character> secretCode = new ArrayList<>(getSecretCode(intLength, intPossibleNumber));
                    int turn = 1;
                    while (true) {
                        System.out.println("Turn " + turn + ":");
                        String input = scanner.next();
                        if (guessCode(secretCode, input) == intLength) {
                            System.out.println("Congratulations! You guessed the secret code.");
                            break;
                        }
                        turn++;
                    }
                }
            }
        }
    }

    static ArrayList<Character> getSecretCode(int length, int possibleNumber) {
        Random random = new Random();
        ArrayList<Character> secretCode = new ArrayList<>();
        if (length > 36) {
            System.out.println("Error: can't generate a secret number with a length of " + length + " because there aren't enough unique digits.");
        } else {
            String dictionary = "0123456789abcdefghijklmnopqrstuvwxyz";
            StringBuilder possibleDictionary = new StringBuilder();
            for (int i = 0; i < possibleNumber; i++) {
                possibleDictionary.append(dictionary.charAt(i));
            }
            System.out.print("The secret is prepared: ");
            for (int i = 0; i < length; i++) {
                System.out.print("*");
            }
            if (possibleNumber <= 10) {
                System.out.println(" (0-" + possibleDictionary.charAt(possibleNumber - 1) + ")");
            } else {
                System.out.println(" (0-9, a-" + possibleDictionary.charAt(possibleNumber - 1) + ").");
            }
            System.out.println("Okay, let's start a game!");
            while (secretCode.size() < length) {
                Character symbol = possibleDictionary.charAt(random.nextInt(length));
                if (!secretCode.contains(symbol)) {
                    secretCode.add(symbol);
                }
            }
        }
        return secretCode;
    }

    static int guessCode(ArrayList<Character> secretCode, String input) {
        ArrayList<Character> tryToGuess = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            tryToGuess.add(input.charAt(i));
        }
        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < tryToGuess.size(); i++) {
            if (tryToGuess.get(i) == secretCode.get(i)) {
                bulls++;
                continue;
            }
            if (secretCode.contains(tryToGuess.get(i))) {
                cows++;
            }
        }

        System.out.print("Grade: ");
        if (bulls > 0 && cows > 0) {
            System.out.println(bulls + " bull(s) and " + cows + " cow(s). ");
        } else if (bulls > 0) {
            System.out.println(bulls + " bull(s). ");
        } else if (cows > 0 ) {
            System.out.println(cows + " cow(s). ");
        } else {
            System.out.println("None. ");
        }

        return bulls;
    }
}