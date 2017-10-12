// Hash Table class.
// Part of the SpellChecker project.
// The two HashTable() methods, the Insert(String S), Contains(String S), 
// and Count() methods were given in the project file and were to be used as
// they were given. This is why "S" is used for a string value in the 
// Insert(String S) method. I would have changed it to something besides "S"
// but I was told not to. Same goes for the isPrime(int n) method where I
// would have changed "n" to something else.

package spellchecker;
import java.util.*;
public class HashTable {
    public static String[] hashTable;
    public static char[] charArray;
    
    public static void HashTable() {
        hashTable = new String[1000];
    }
    public static void HashTable(int n) {
        int hashTableSize;
        hashTableSize = 3*n;
        
        while (!isPrime(hashTableSize)) {
            hashTableSize++;
            isPrime(hashTableSize);
        }
        hashTable = new String[hashTableSize];
        Arrays.fill(hashTable, " ");
    }
    public static void Insert(String S) {
        charArray = S.toCharArray();
        int hashValue, index, j; 
        double number;
        hashValue = 0;
        j = 0;
        
        for (int i = 0; i < charArray.length; i++) {
            number = (int)charArray[i] - (int)'a' + 1;
            hashValue += (int)(number * Math.pow(31, (3-j)));
            if (j < 3) 
                j++;
            else 
                j=0;
        }
        index = hashValue % Count();
        while (!hashTable[index].equals(" ")) {
            if (index == Count())
                index = 0;
            else 
                index++;
        }
        hashTable[index] = S;
    }
    public static boolean isPrime(int n) {
        if (n%2 == 0) 
            return false;
        else
            for (int i = 3; i*i <= n; i+=2) {
                if (n%i == 0)
                    return false;
            }
        return true;
    }
    public static boolean Contains(String S) {
        S = S.replaceAll("[^a-zA-Z]","");
        S = S.toLowerCase();
        for (int i = 0; i < Count(); i++) {
            if (hashTable[i].equals(S)) 
                return true;
        }
        return false;
    }
    
    public static int Count() {
        return hashTable.length;
    }
}
