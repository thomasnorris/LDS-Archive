// Chooses a random number between 1 and 100 and has the user guess at it
// Created by Thomas Norrs for EECS 2500-001 with Dr. Ledgard.

package secretnumber;
import java.util.Random;
import java.util.Scanner;
public class SecretNumber {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        Random randomNum = new Random();
        int generatedNumber, guess, difference;
        
        // Generates a random number and has the user guess
        generatedNumber = randomNum.nextInt(100)+1;
        System.out.println("A number between 1 and 100 has been generated. "
                         + "Try and guess the number. Enter 0 to quit.");
        guess = stdin.nextInt();       
        
        while (guess != 0 && guess != generatedNumber) {
            difference = Math.abs(guess-generatedNumber);
            if (difference > 30) {
                System.out.println("Way Too High or Way Too Low. Try Again.");
                guess = stdin.nextInt();
            } else if (difference >= 10 && difference <= 30) { 
                System.out.println("High or Low. Try Again."); 
                guess = stdin.nextInt();
            } else if (difference < 10) {
                System.out.println("A Little High or A Little Low. "
                                 + "Try Again.");
                guess = stdin.nextInt();
            }
        }
        if (guess == generatedNumber && guess != 0) 
            System.out.println("Congrats! You've Guessed Correctly!");            
    }
}    

