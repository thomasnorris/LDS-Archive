// Queue class for the CarWashSimulaton program.

package carwashsimultaion;
import java.util.*;
public class CustomerQueue<T> {
    private LinkedList<T> customerQueue = new LinkedList<>();
    public boolean isEmpty() {
        return (customerQueue.isEmpty());
    }
    public void enqueue(T element) {
        customerQueue.addLast(element);
    }
    public T dequeue() {
        if (isEmpty()) {
            System.out.println("Dequeue attempted on empty queue.");
            System.exit(0);
        }
        return customerQueue.remove();
    }
    public int size() {
        return customerQueue.size();
    }
}
