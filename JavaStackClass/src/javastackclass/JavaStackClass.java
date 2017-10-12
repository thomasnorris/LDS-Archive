// Introduction to the java stack class.
// Created by Thomas Norris for EECS 2500-001 with Dr. Ledgard.

package javastackclass;
import java.util.*;
public class JavaStackClass {
    public static void main(String[] args) {
        Stack wordStack = new Stack();
        Stack reverseWordStack = new Stack();
        Scanner userIn = new Scanner(System.in);
        String input;
        int count;
        count = 0;
        System.out.println("This program will take words one at a time "
                         + "and then print them in reverse order.\nEnter "
                         + "words one at a time. When you are done, type "
                         + "DONE.\nEnter first word: ");
        input = userIn.next();
        
        while (!input.equalsIgnoreCase("DONE")) {
            wordStack.push(input);
            System.out.println("Next word: ");
            input = userIn.next();
            count++;
        }  
        if (input.equalsIgnoreCase("DONE") && wordStack.isEmpty()) {
            System.out.println("The stack is empty. Terminating program.");
            System.exit(0);
        }
        else if (input.equalsIgnoreCase("DONE") && !wordStack.isEmpty()) {
            for (int i = 0; i < count; i++) 
                reverseWordStack.push(wordStack.pop());
            System.out.println("The words in reverse order are as follows: ");
            for (int i = 0; i < reverseWordStack.size(); i++)
                System.out.print(reverseWordStack.get(i)+" ");
            System.out.println("\n");
        }
    } 
}
