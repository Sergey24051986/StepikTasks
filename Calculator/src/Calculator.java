import java.io.File;
import java.util.*;

public class Calculator {

    Calculator() {

        try {
            Scanner in = new Scanner(new File("Calculator/input.txt"));
            int num1 = in.nextInt();
            String operator = in.next();
            int num2 = in.nextInt();

            if (!operator.matches("[*/+-]")) {
                throw new Exception("Operation Error!");
            }

            if (operator.equals("+")) {
                System.out.println(num1 + num2);
            } else if (operator.equals("-")) {
                System.out.println(num1 - num2);
            } else if (operator.equals("*")) {
                System.out.println(num1 * num2);
            } else if (operator.equals("/")) {
                System.out.println(num1 / num2);
            }

        } catch(InputMismatchException e) {
            System.out.println("Error! Not number");
        } catch(ArithmeticException e) {
            System.out.println("Error! Division by zero");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        new Calculator();

    }
}
