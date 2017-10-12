// Stable matching soution to the marriage problem.
// Create by Thomas Norris for EECS 2500-001 with Dr. Ledgard

package stablematching;
import java.io.*;
import java.util.*;
public class StableMatching {
    private static LinkedList<String> man1PrefList = new LinkedList<>();
    private static LinkedList<String> man2PrefList = new LinkedList<>();
    private static LinkedList<String> man3PrefList = new LinkedList<>();
    private static LinkedList<String> man4PrefList = new LinkedList<>();
    private static LinkedList<String> man5PrefList = new LinkedList<>();
    private static LinkedList<String> woman1PrefList = new LinkedList<>();
    private static LinkedList<String> woman2PrefList = new LinkedList<>();
    private static LinkedList<String> woman3PrefList = new LinkedList<>();
    private static LinkedList<String> woman4PrefList = new LinkedList<>();
    private static LinkedList<String> woman5PrefList = new LinkedList<>();
    private static LinkedList<String> menList = new LinkedList<>();
    private static LinkedList<String> womenList = new LinkedList<>();
    private static LinkedList<String> engagedMenList = new LinkedList<>();
    private static LinkedList<String> engagedWomenList = new LinkedList<>();
    private static java.io.File dataFile = new File("dataFile.txt");
    
    public static void main(String[] args) throws Exception {
        Scanner fileIn = new Scanner(dataFile);
        Scanner userIn = new Scanner(System.in);
        int numMenAndWomen;
        String fileName, proposalChoice, allowAlgorithm, m, w, f;
        boolean menPropose, womenPropose;
        menPropose = false;
        womenPropose = false;
    
        System.out.println("What file would you like to load? ");
        fileName = userIn.next();
        while (!fileName.equals("dataFile")) {
            System.out.println("That is an invalid file.\nTry again: ");
            fileName = userIn.next();
        }
        numMenAndWomen = fileIn.nextInt();
        for (int i = 0; i < 5; i++) menList.add(fileIn.next());
        for (int i = 0; i < 5; i++) womenList.add(fileIn.next());
        fillMenPreferences();
        fillWomenPreferences();
        
        System.out.println("There are "+numMenAndWomen+" men and "
                           +numMenAndWomen+" women.");
        listMenPreferences();
        listWomenPreferences();
        
        System.out.println("Would you like the men to propose or the "
                         + "women to propose? ");
        proposalChoice = userIn.next();
        while (!proposalChoice.equals("men") && 
               !proposalChoice.equals("women")) {
            System.out.println("That is not a gender. Please select "
                             + "men or women");
            proposalChoice = userIn.next();
        }
        if (proposalChoice.equals("men")) 
            menPropose = true;
        else if (proposalChoice.equals("women"))
            womenPropose = true;
        
            
        System.out.println("Can I use the Gale-Shapley algorithm to "
                         + "find the stable marriages? ");
        allowAlgorithm = userIn.next();
        while (!allowAlgorithm.equals("yes") && 
                 !allowAlgorithm.equals("no")) {
            System.out.println("Okay smarty pants I need a yes or no answer.");
            allowAlgorithm = userIn.next();
        }    
        if (allowAlgorithm.equals("yes") && menPropose == true) {
            System.out.println("Okay, finding stable marriages when the "
                             + "men propose to the women.");
            menProposeMatching();
            System.out.println(engagedMenList.size());
            System.out.println(engagedWomenList.size());
            for (int i = 0; i < 5; i++) {
                System.out.println(engagedMenList.get(i));
                System.out.println(engagedWomenList.get(i));
            }
        } else if (allowAlgorithm.equals("yes") && womenPropose == true) {
            System.out.println("Okay, finding stable marriages when the "
                             + "women propose to the men.");
            womenProposeMatching();
            System.out.println(engagedWomenList.size());
            System.out.println(engagedMenList.size());
            for (int i = 0; i < 5; i++) {
                System.out.println(engagedWomenList.get(i));
                System.out.println(engagedMenList.get(i));
            }
        } else if (allowAlgorithm.equals("no")) {
            System.out.println("Oh. Okay then. Goodbye.");
            System.exit(0);
        }    
    }
    public static void menProposeMatching() {
        LinkedList<String> womanPrefList;
        int count, numProposals;
        String man, woman;
        count = 0;
        numProposals = 0;
        man = menList.get(count);
        while (!engagedMenList.contains(man) && numProposals < 4) {
            man = menList.get(count);
            
            if (man.equals("Brian"))
                woman = man1PrefList.get(numProposals);
            else if (man.equals("George"))
                woman = man2PrefList.get(numProposals);
            else if (man.equals("John"))
                woman = man3PrefList.get(numProposals);
            else if (man.equals("Robert"))
                woman = man4PrefList.get(numProposals);
            else woman = man5PrefList.get(numProposals);
            
            if (woman.equals("Anne"))
                womanPrefList = woman1PrefList;
            else if (woman.equals("Joyce"))
                womanPrefList = woman2PrefList;
            else if (woman.equals("Nancy"))
                womanPrefList = woman3PrefList;
            else if (woman.equals("Patricia"))
                womanPrefList = woman4PrefList;
            else womanPrefList = woman5PrefList;
            
            if (!engagedWomenList.contains(woman)) {
                engagedMenList.add(man);
                engagedWomenList.add(woman);
                count++;
                numProposals++;
                man = menList.get(count);
            } else if (womanPrefList.indexOf(man) < 
                       womanPrefList.indexOf(engagedMenList.get
                            (engagedWomenList.indexOf(woman)))) {
                engagedMenList.add(man);
                engagedWomenList.add(woman);
                engagedMenList.remove(engagedMenList.get
                    (engagedWomenList.indexOf(woman)));
                count--;
                numProposals++;
                man = menList.get(numProposals);
            } else numProposals++;
        }
    }    
    public static void womenProposeMatching() {
        LinkedList<String> manPrefList;
        int count, numProposals;
        String man, woman;
        woman = null;
        count = 0;
        numProposals = 0;
        while (!engagedWomenList.contains(woman) && numProposals < 4) {
            woman = womenList.get(count);
            if (woman.equals("Anne"))
                man = woman1PrefList.get(numProposals);
            else if (woman.equals("Joyce"))
                man = woman2PrefList.get(numProposals);
            else if (woman.equals("Nancy"))
                man = woman3PrefList.get(numProposals);
            else if (woman.equals("Patricia"))
                man = woman4PrefList.get(numProposals);
            else man = woman5PrefList.get(numProposals);
            
            if (man.equals("Brian"))
                manPrefList = man1PrefList;
            else if (man.equals("George"))
                manPrefList = man2PrefList;
            else if (man.equals("John"))
                manPrefList = man3PrefList;
            else if (woman.equals("Robert"))
                manPrefList = man4PrefList;
            else manPrefList = man5PrefList;
            
            if (!engagedMenList.contains(man)) {
                engagedWomenList.add(woman);
                engagedMenList.add(man);
                count++;
                numProposals++;
                woman = womenList.get(count);
            } else if (manPrefList.indexOf(woman) < 
                       manPrefList.indexOf(engagedWomenList.get
                            (engagedMenList.indexOf(man)))) {
                engagedMenList.add(man);
                engagedWomenList.add(woman);
                engagedWomenList.remove(engagedWomenList.get
                    (engagedMenList.indexOf(man)));
                count--;
                numProposals++;
                woman = womenList.get(numProposals);
            } else numProposals++;
        }
    }
    public static void listMenPreferences() {
        System.out.println("The men's preferences are as follows:");
        System.out.print(menList.get(0)+": ");
        for (int i = 0; i < 5; i++)
            System.out.print(man1PrefList.get(i)+", ");
        System.out.print("\n");
        System.out.print(menList.get(1)+": ");
        for (int i = 0; i < 5; i++)
            System.out.print(man2PrefList.get(i)+", ");
        System.out.print("\n");
        System.out.print(menList.get(2)+": ");
        for (int i = 0; i < 5; i++)
            System.out.print(man3PrefList.get(i)+", ");
        System.out.print("\n");
        System.out.print(menList.get(3)+": ");
        for (int i = 0; i < 5; i++)
            System.out.print(man4PrefList.get(i)+", ");
        System.out.print("\n");    
        System.out.print(menList.get(4)+": ");
        for (int i = 0; i < 5; i++)
            System.out.print(man5PrefList.get(i)+", ");
        System.out.print("\n\n");
    }
    public static void listWomenPreferences() {
        System.out.println("The women's preferences are as follows:");
        System.out.print(womenList.get(0)+": ");
        for (int i = 0; i < 5; i++)
            System.out.print(woman1PrefList.get(i)+", ");
        System.out.print("\n");
        System.out.print(womenList.get(1)+": ");
        for (int i = 0; i < 5; i++)
            System.out.print(woman2PrefList.get(i)+", ");
        System.out.print("\n");
        System.out.print(womenList.get(2)+": ");
        for (int i = 0; i < 5; i++)
            System.out.print(woman3PrefList.get(i)+", ");
        System.out.print("\n");
        System.out.print(womenList.get(3)+": ");
        for (int i = 0; i < 5; i++)
            System.out.print(woman4PrefList.get(i)+", ");
        System.out.print("\n");
        System.out.print(womenList.get(4)+": ");
        for (int i = 0; i < 5; i++)
            System.out.print(woman5PrefList.get(i)+", ");
        System.out.print("\n\n");
    }
    public static void fillMenPreferences() throws Exception {
        Scanner fileIn = new Scanner(dataFile);
        for (int i = 0; i < 15; i++) fileIn.next();
        for (int i = 0; i < 5; i++) man3PrefList.add(fileIn.next());
        fileIn.next();
        for (int i = 0; i < 5; i++) man4PrefList.add(fileIn.next());
        fileIn.next();
        for (int i = 0; i < 5; i++) man1PrefList.add(fileIn.next());
        fileIn.next();
        for (int i = 0; i < 5; i++) man5PrefList.add(fileIn.next());
        fileIn.next();
        for (int i = 0; i < 5; i++) man2PrefList.add(fileIn.next());
    }
    public static void fillWomenPreferences() throws Exception {
        Scanner fileIn = new Scanner(dataFile);
        for (int i = 0; i < 48; i++) fileIn.next();
        for (int i = 0; i < 5; i++) woman3PrefList.add(fileIn.next());
        fileIn.next();
        for (int i = 0; i < 5; i++) woman2PrefList.add(fileIn.next());
        fileIn.next();
        for (int i = 0; i < 5; i++) woman4PrefList.add(fileIn.next());
        fileIn.next();
        for (int i = 0; i < 5; i++) woman1PrefList.add(fileIn.next());
        fileIn.next();
        for (int i = 0; i < 5; i++) woman5PrefList.add(fileIn.next());  
    }
}
