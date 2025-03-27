import java.io.*;
import java.util.*;

public class Calculator {

    Calculator() throws IOException {

        Scanner in = new Scanner(new File("Task 2.10 st1-6 - Calculator/step 6 - Final/input.txt"));
        PrintWriter writer = new PrintWriter("Task 2.10 st1-6 - Calculator/step 6 - Final/output.txt");

        while (in.hasNext()) {
            String str = in.nextLine();
            String[] arr = str.split(" ");

            try {
                double num1 = Integer.parseInt(arr[0]);
                String operator = arr[1];
                if (!operator.matches("[*/+-]")) {
                    throw new Exception(str + " = Operation Error!");
                }
                double num2 = Integer.parseInt(arr[2]);

                if (operator.equals("/") & num2 == 0) {
                    throw new Exception(str + " = Error! Division by zero");
                }

                if (operator.equals("+")) {
                    writer.println(str + " = " + (num1 + num2));
                } else if (operator.equals("-")) {
                    writer.println(str + " = " + (num1 - num2));
                } else if (operator.equals("*")) {
                    writer.println(str + " = " + (num1 * num2));
                } else if (operator.equals("/")) {
                    writer.println(str + " = " + (num1 / num2));
                }

            } catch (NumberFormatException e) {
                writer.println(str + " = Error! Not number");
            } catch (Exception e) {
                writer.println(e.getMessage());
            }
        }
        in.close();
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        new Calculator();
    }
}
