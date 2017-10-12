// Recursive solution to the weighted interval scheduling problem.
// Created by Thomas Norris for EECS 2500-001 with Dr. Ledgard.

package weightedintervalscheduling;
import java.io.*;
import java.util.*;
public class WeightedIntervalScheduling {
    public static Job[] Job;
    public static int[] compatableJobs;
    public static int[] optimumJobs;
    private static java.io.File dataFile = new File("dataFile.txt");
    public static void main(String[] args) throws Exception {
        Scanner fileIn = new Scanner(dataFile);
        String group;
        int index, numJobs, startTime, endTime, weight, job1, job2;
        startTime = 0;
        endTime = 0;
        weight = 0;
        index = 0;
        job1 = 0;
        job2 = 0;
        
        numJobs = fileIn.nextInt();
        Job = new Job[numJobs];
        compatableJobs = new int[numJobs];
        optimumJobs = new int[numJobs];
        Arrays.fill(compatableJobs, -1);
        Arrays.fill(optimumJobs, -1);
        
        while (fileIn.hasNext()) {
            group = fileIn.next();
            createJobObject(index, group, startTime, endTime, weight);
            index++;
        }
        
        findCompatableJobs(numJobs);
        findOptimalJobIndex(numJobs-1);
        
        for (int i = 0; i < optimumJobs.length; i++) {
            if (optimumJobs[i] != -1) {
                if (job1 > job2) {
                    job1 = optimumJobs[i];
                }
                job2 = optimumJobs[i];
            }    
        }
        
        System.out.println("Optimum Profit: "+findOptimalProfit(numJobs-1)+
                           "\nUsing Jobs: "+(job1+1)+", "+(job2+1));
    }
    
    public static void createJobObject(int index, String group, int startTime, 
                                       int endTime, int weight) {
        group = group.replaceAll(","," ");
        group = group.replaceAll("[()]"," ");
        Scanner groupIn = new Scanner(group);
        startTime = groupIn.nextInt();
        endTime = groupIn.nextInt();
        weight = groupIn.nextInt();
        Job[index] = new Job(startTime, endTime, weight);
    }    
    public static void findCompatableJobs(int numJobs) {
        for (int i = 0; i < Job.length; i++) {
            for (int j = 0; j < i; j++) {
                if (Job[j].endTime <= Job[i].startTime) {
                    compatableJobs[i] = j;
                }
            }
        }
    }
    public static int findOptimalProfit(int j) {
        if (j == -1) 
            return 0;
        else 
            return Math.max(Job[j].weight + 
                            findOptimalProfit(compatableJobs[j]), 
                            findOptimalProfit(j-1));      
    }
    public static void findOptimalJobIndex(int j) {
        if (j != -1) {
            if (Job[j].weight + findOptimalProfit(compatableJobs[j]) > 
                findOptimalProfit(j-1)) {
                optimumJobs[j] = j;
                findOptimalJobIndex(compatableJobs[j]);
            } else
                findOptimalJobIndex(j-1); 
        }  
    }
}
