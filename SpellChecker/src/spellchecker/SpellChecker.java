// Spell checker using a hash table.
// Created by Thomas Norris for EECS 2500-001 with Dr. Ledgard.

package spellchecker;
import java.io.*;
import java.util.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;

public class SpellChecker {
    public static Alert doneAlert = new Alert(AlertType.INFORMATION);
    public static Alert misspelledWordsAlert = new Alert(AlertType.INFORMATION);
    public static ArrayList<String> errorList = new ArrayList<>();
    public static void loadDictionary(String dictFile) throws FileNotFoundException {
        String word;
        int numDictWords;
        numDictWords = 0;
      
        Scanner dictScanner = new Scanner(new File(dictFile));
        Scanner dictScanner2 = new Scanner(new File(dictFile));
        while (dictScanner.hasNext()) {
            numDictWords++;
            dictScanner.next();
        }
        HashTable.HashTable(numDictWords);
        while (dictScanner2.hasNext()) {
            word = dictScanner2.next();
            word = word.replaceAll("[^a-zA-Z]","");
            word = word.toLowerCase();
            HashTable.Insert(word);
        }
    }
    public static void scanFile(String userFile) throws FileNotFoundException {
        String word;
        int errorCount;
        errorCount = 0;
        
        Scanner userFileScanner = new Scanner(new File(userFile));
        while (userFileScanner.hasNext()) {
            word = userFileScanner.next();
            word = word.replaceAll("[^a-zA-Z]","");
            word = word.toLowerCase();
            if (!HashTable.Contains(word) && !word.equals("") 
                                          && !word.contains(" ")) {
                errorCount++;
                errorList.add(word);
            }    
        }
        endMessage(userFile, errorCount);
    }
    public static void endMessage(String userFile, int errorCount) {
        doneAlert.setTitle("Scanning complete");
        if (errorCount == 0)
            doneAlert.setHeaderText("There were no errors found in the "
                                  + "file \""+userFile+"\". You are an "
                                  + "excellent speller! :)\nPress \"OK\" "
                                  + "to dismiss.");
        else if (errorCount == 1)
            doneAlert.setHeaderText("There is 1 error in the file \""+userFile
                                  + "\". You are a decent speller!\n"
                                  + "Press \"OK\" to dismiss.");
        else
            doneAlert.setHeaderText("There are "+errorCount+" errors in "
                                  + "the file \""+userFile+"\". That "
                                  + "stinks :(\nPress \"OK\" to dismiss.");
        doneAlert.showAndWait();
        if (errorCount != 0) {
            misspelledWordsAlert.setTitle("Misspelled Words");
            misspelledWordsAlert.setHeaderText("The words that were "
                                             + "misspelled are as follows:"
                                             + "\n"+errorList+"\n"
                                             + "Press \"OK\" to dismiss.");
            misspelledWordsAlert.showAndWait();
        }
    }
}
