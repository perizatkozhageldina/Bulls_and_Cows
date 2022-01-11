import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.next();
            if (input.equals("0")) {
                break;
            } else {
                try {
                    int number = Integer.parseInt(input) * 10;
                    System.out.println(number);
                } catch (Exception e) {
                    System.out.println("Invalid user input: " + input);
                }
            }
        }
    }
}