
package bagclass;
public class Bag {
    public int newEntry;
    public int[] bagItems;
    public int numItems;
    public Bag() {
        // An empty bag would be created
        numItems = 0;
    }
    public void add(int newEntry) {
        // This is where the method for adding an item to the bag would be
        // found. Some of it is below (maybe it is right, maybe not)
        newEntry = bagItems[numItems];
        numItems++;
    }
    public int size() {
        // This would check the size of the array and return it
        return numItems;
    }
    public int countOccurences(int target) {
        for (int i = 0; i < numItems; i++) {
            // This would search for the name of the item and would count up.
            // Returns the number of times the counter has counted up.
        }
        return //something;
    }
    public boolean remove(int target) {
        // This would see if an item was removed from the bag. If the item
        // was removed, this would be true. Otherwise false.
        return //something;
    }
    
}
