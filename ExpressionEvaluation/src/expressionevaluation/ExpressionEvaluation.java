// Expression evaluation using stacks
// Created by Thomas Norris for EECS 2500-001 with Dr. Ledgard

package expressionevaluation;
import java.util.*;
public class ExpressionEvaluation {
    public static void main(String[] args) {
        Stack operatorStack = new Stack();
        Stack valueStack = new Stack();
        Scanner userIn = new Scanner(System.in);
        String expression, thisOp, nextOp;
        int count, result, num1, num2;
        count = 0;
        
        System.out.println("Enter an expression using integers and the "
                         + "+, -, and * operators.\nNo parenthesis. "
                         + "Please note: two numbers entered without\nan "
                         + "operand will result in the individual numbers "
                         + "being interpreted as one.\nFor example, 1 1 "
                         + "will be interpreted as 11. Double check "
                         + "the expression before entering to avoid this.\n"
                         + "Enter the expression carefully: ");
        expression = userIn.nextLine();
        expression = expression.replaceAll(" ","");
        String splitExpression[] = expression.split("(?=[*])|(?<=[*])|"
                                                  + "(?=[+])|(?<=[+])|"
                                                  + "(?=[-])|(?<=[-])");
        
        while (count < splitExpression.length) {
            thisOp = splitExpression[count];
            if (thisOp.matches("\\d+")) {
                valueStack.push(thisOp);
            } else if (thisOp.equals("*")) {
                if (splitExpression[count+1].equals("-") || 
                    splitExpression[count+1].equals("+") ||
                    splitExpression[count+1].equals("*")) {
                    System.out.println("Error: There is an extra operator "
                                     + "in the expression. "
                                     + "Terminating program.");
                    System.exit(0);
                }
                nextOp = splitExpression[count+1];
                valueStack.push(nextOp);
                num1 = Integer.parseInt(valueStack.pop().toString());
                num2 = Integer.parseInt(valueStack.pop().toString());
                result = (num1 * num2);
                valueStack.push(result);
                count++;
            } else if (thisOp.equals("-") || thisOp.equals("+")) {
                if (splitExpression[count+1].equals("-") || 
                    splitExpression[count+1].equals("+") ||
                    splitExpression[count+1].equals("*")) {
                    System.out.println("Error: There is an extra operator "
                                     + "in the expression. "
                                     + "Terminating program.");
                    System.exit(0);
                }
                operatorStack.push(thisOp);
            } else {
                System.out.println("There is an invalid character "
                                 + "in the expression. Terminating "
                                 + "program.");
                System.exit(0);
            }
            count++;
        }
        while (!operatorStack.isEmpty()) {
            num1 = Integer.parseInt(valueStack.pop().toString());
            num2 = Integer.parseInt(valueStack.pop().toString());
            thisOp = (String) operatorStack.pop();
            if (thisOp.equals("+")) {
                result = (num1 + num2);
                valueStack.push(result);
            } else if (thisOp.equals("-")) {
                result = (num2 - num1);
                valueStack.push(result);
            }
        }
        System.out.println("The answer is: " + valueStack.pop());
    }
} 

