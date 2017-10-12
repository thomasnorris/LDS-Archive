// Car wash simulation program for Ethan's car wash.
// Created by Thomas Norris for EECS 2500-001 with Dr. Ledgard.

package carwashsimultaion;
import java.util.*;
import javafx.scene.control.*;
public class CarWashSimulation {
    public static CustomerQueue<Customer> customerQueue = 
                                          new CustomerQueue<>();
    public static ArrayList<Integer> waitTimes = new ArrayList<>();
    public static void runSimulation(TextField hours, TextField minutes,
                                     TextField customers, Alert simComplete)
                                     throws Exception {
        int totalHours, totalMinutes, initialCustomers, currentTime,
            deniedCustomers, totalServicedCustomers, arrivalTime,
            serviceLength, idleTime, waitTime, finishTime, totalWaitTimes;
        
        deniedCustomers = 0;
        totalServicedCustomers = 0;
        arrivalTime = 0;
        idleTime = 0;
        finishTime = 0;
        totalWaitTimes = 0;
        serviceLength = 0;
        
        totalHours = Integer.parseInt(hours.getText());
        totalMinutes = (totalHours * 60);
        totalMinutes += Integer.parseInt(minutes.getText());
        initialCustomers = Integer.parseInt(customers.getText());
        
        if (initialCustomers > 4) {
            deniedCustomers = initialCustomers - 4;
            initialCustomers = initialCustomers - deniedCustomers;
        }
        
        for (int i = 0; i < initialCustomers; i++) {
            customerQueue.enqueue(new Customer(arrivalTime));
        }
        
        for (currentTime = 0; currentTime <= totalMinutes; currentTime++) {
            if (currentTime == arrivalTime) {
                if (customerQueue.size() < 4) 
                    customerQueue.enqueue(new Customer(arrivalTime));   
                else 
                    deniedCustomers++;    
                arrivalTime += 1+(int)(Math.random()*((6-1)+1));
            }
            if (currentTime == finishTime) {
                totalServicedCustomers++;
                Customer customer = customerQueue.dequeue();
                waitTime = (currentTime - customer.arrivalTime);
                waitTimes.add(waitTime);
                serviceLength = 2+(int)(Math.random()*(5-2)+1);
                finishTime = currentTime + serviceLength;
            }
            else
                idleTime++;
        }
        
        for (int i = 0; i < waitTimes.size(); i++) {
             totalWaitTimes += waitTimes.get(i);
        }
        
        simComplete.setContentText("Total Customers: "+
                                        totalServicedCustomers+"\n"+
                                        "Idle Time: "+
                                        idleTime+" Minutes\n"+
                                        "Average Wait Time: "+
                                        (totalWaitTimes / waitTimes.size())+
                                        " Minutes\n"+"Longest Wait Time: "+
                                        Collections.max(waitTimes)+
                                        " Minutes\n"+"Denied Customers: "+
                                        deniedCustomers);
        simComplete.showAndWait();
        System.exit(0);
    }   
}
