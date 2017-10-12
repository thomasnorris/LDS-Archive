// Sorting. Creates three files with 50000 integers sorts each depending
// on the user input.
// Created by Thomas Norris for EECS 2500-001 with Dr. Ledgard

package sorting;
import java.io.*;
import java.util.*;
public class Sorting {
    public static java.io.File file1 = new java.io.File("digitsInOrder.txt");
    public static java.io.File file2 = new java.io.File("digitsInReverse"
                                                      + "Order.txt");
    public static java.io.File file3 = new java.io.File("digitsInRandom"
                                                      + "Order.txt");
    public static int[] fileDigits = new int[50000];
    public static void main(String[] args) throws Exception {
        Scanner readIn = new Scanner(System.in);
        if (!file1.exists()) file1.createNewFile();
        if (!file2.exists()) file2.createNewFile();
        if (!file3.exists()) file3.createNewFile();
        
        Scanner file1In = new Scanner(file1);
        Scanner file2In = new Scanner(file2);
        Scanner file3In = new Scanner(file3);
        String fileName;
        int comparisons;
        long beginningTime, endTime, totalTime;
        
        createFile1(file1);
        createFile2(file2);
        createFile3(file3);
        System.out.print("Three files have been created and filled with "
                         + "50,000 digits.\nThe file names are "
                         + "digitsInOrder,digitsInReverseOrder and "
                         + "digitsInRandomOrder.\nEnter the name of the "
                         + "file to sort numerically: ");
        fileName = readIn.next();
        while (!fileName.equals("digitsInOrder") && 
               !fileName.equals("digitsInReverseOrder") &&
               !fileName.equals("digitsInRandomOrder")) {
            System.out.print("That is not a valid file.\nEnter the name of "
                             + "the file to sort numerically: ");
            fileName = readIn.next();
        }
        if (fileName.equals("digitsInOrder")) {
            beginningTime = System.currentTimeMillis();
            for (int i = 0; i < 50000; i++) 
                fileDigits[i] = file1In.nextInt();
            comparisons = insertionSort(fileDigits);
            endTime = System.currentTimeMillis();
            System.out.print("\nNumber of comparisons: "+comparisons+
                             "\nFirst thirty digits: ");
            for (int i = 0; i < 30; i++)
                System.out.print(fileDigits[i]+" ");
            totalTime = endTime-beginningTime;
            System.out.println("\nSorting runtime (milliseconds): "
                               +totalTime);
        } 
        else if (fileName.equals("digitsInReverseOrder")) {
            beginningTime = System.currentTimeMillis();
            for (int i = 0; i < 50000; i++)
                fileDigits[i] = file2In.nextInt();
            comparisons = insertionSort(fileDigits);
            endTime = System.currentTimeMillis();
            System.out.print("\nNumber of comparisons: "+comparisons+
                             "\nFirst thirty digits: ");
            for (int i = 0; i < 30; i++)
                System.out.print(fileDigits[i]+" ");
            totalTime = endTime-beginningTime;
            System.out.println("\nSorting runtime (milliseconds): "
                               +totalTime);
        } 
        else if (fileName.equals("digitsInRandomOrder")) {
            beginningTime = System.currentTimeMillis();
            for (int i = 0; i < 50000; i++)
                fileDigits[i] = file3In.nextInt();
            comparisons = insertionSort(fileDigits);
            endTime = System.currentTimeMillis();
            System.out.print("\nNumber of comparisons: "+comparisons+
                             "\nFirst thirty digits: ");
            for (int i = 0; i < 30; i++)
                System.out.print(fileDigits[i]+" ");
            totalTime = endTime-beginningTime;
            System.out.println("\nSorting runime (milliseconds): "
                               +totalTime);
        } 
    } 
    public static void createFile1(java.io.File file1) throws Exception {
        PrintStream P1 = new PrintStream("digitsInOrder.txt");
        for (int i = 1; i <= 50000; i++) {
            P1.print(i + "\n");
        }
    }
    public static void createFile2(java.io.File file2) throws Exception {
        PrintStream P2 = new PrintStream("digitsInReverseOrder.txt");
        for (int i = 50000; i >= 1; i--) {
            P2.print(i + "\n");
        }
    }
    public static void createFile3(java.io.File file3) throws Exception {
        PrintStream P3 = new PrintStream("digitsInRandomOrder.txt");
        Random random = new Random();
        int numDigits, randNum;
        numDigits = 50000;
        
        while (numDigits >= 0) {
            randNum = random.nextInt(50000)+1;
            P3.print(randNum + "\n");
            numDigits--;
        }    
    }
    public static int insertionSort(int[] fileDigits) {
        int comparisons;
        comparisons = 0;
        for (int i = 1; i < fileDigits.length; i++) {
            int temp = fileDigits[i], j;
            for (j = i-1; j >= 0 && temp < fileDigits[j]; j--) {
                fileDigits[j+1] = fileDigits[j];
                comparisons++;
            }
            fileDigits[j+1] = temp;
        }
        return comparisons;
    }
}
