// Creates and prints a pair of dice from random numbers
// Created by Thomas Norris for EECS 2500-001 with Dr. Ledgard.

// Driver class
package createpairofdice;
public class CreatePairOfDice {
    public static void main(String[] args) {
        double die1Value, die2Value;
        die1Value = Math.ceil(Math.random()*6);
        die2Value = Math.ceil(Math.random()*6);
        Die.createDie1(die1Value);
        Die.createDie2(die2Value);
    }
}
