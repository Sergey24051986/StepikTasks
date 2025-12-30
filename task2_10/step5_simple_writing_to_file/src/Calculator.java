import java.io.*;
import java.util.*;

public class Calculator {

    Calculator() throws IOException {

        Scanner in = new Scanner(new File("task2_10/step5_simple_writing_to_file/input.txt"));
        PrintWriter writer = new PrintWriter("task2_10/step5_simple_writing_to_file/output.txt");

        try {
            int num1 = in.nextInt();
            String operator = in.next();
            int num2 = in.nextInt();

            if (!operator.matches("[*/+-]")) {
                throw new Exception("Operation Error!");
            }

            if (operator.equals("+")) {
                writer.print(num1 + num2);
            } else if (operator.equals("-")) {
                writer.print(num1 - num2);
            } else if (operator.equals("*")) {
                writer.print(num1 * num2);
            } else if (operator.equals("/")) {
                writer.print(num1 / num2);
            }

        } catch (InputMismatchException e) {
            writer.print("Error! Not number");
        } catch (ArithmeticException e) {
            writer.print("Error! Division by zero");
        } catch (Exception e) {
            writer.print(e.getMessage());
        }

        in.close();
        writer.close();

    }

    public static void main(String[] args) throws IOException {
        new Calculator();
    }
}
