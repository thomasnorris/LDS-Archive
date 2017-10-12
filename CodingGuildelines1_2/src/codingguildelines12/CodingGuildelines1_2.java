package codingguildelines12;

// Coding guidelines question 1 and 2
// Created by Thomas Norris for EECS 2500-001 with Dr. Ledgard.

// Question 1
// A re-write of the code follows:
char c;
c = userChoice();

switch(c) {
    case 'a', case 'A': optionA(); break;
    case 'i', case 'I': optionI(); break;
    case 'w', case 'W': optionW(); break;
    default: cout << "Not a valid choice\n";
}

/* 5 things wrong with the original code (not above):
   1. GetChoice(); is a poor name. userChoice(); is better (fixed).
   2. Since there is only one 'c' character, the name 'c1' is redundant,
      'c' is better (fixed).
   3. The formatting of the switch statement is messy. A table layout 
      is better (fixed).
   4. The lines 'c = userChoice();' and 'switch(c)' should have a space 
      between them because they are separate functions and a space
      there makes sense (fixed)
   5. The tabbing between the 'case' statement and the 'option' statements
      are incorrect (fixed).
*/












// Question 2
// A re-write of the code follows:
class Time {
    private:
        int hour, minute, second;
    public:
        int h, m, s;
        void displayTime() {
            cout << setw(2) << setfill('0') << hour   << ":"
                 << setw(2) << setfill('0') << minute << ":"
                 << setw(2) << setfill('0') << second << endl;
        }
        void set(h=0, m=0, s=0);
}

/* 5 things wrong with the original code (not above):
   1. The method Timedisplay() should be renamed to displayTime() because
      it is easier to read (fixed).
   2. The three 'int' hour, minute, second variables should be moved onto
      one line to take up less space (fixed).
   3. The long line that starts with 'cout' should be formatted to look
      more like a table so it is easier to read (fixed).
   4. The tree 'int' h, m, s variables shouldnt be initialized in their
      declaration (fixed).
   5. Comments were in a confusing spot and should have been removed 
      altogether(fixed).
*/