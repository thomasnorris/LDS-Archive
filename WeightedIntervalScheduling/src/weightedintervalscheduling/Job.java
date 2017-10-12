// Job class for the Weighted Interval Scheduling problem

package weightedintervalscheduling;
public class Job {
    int startTime, endTime, weight;
    public Job(int startTime, int endTime, int weight) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.weight = weight;
    }
}
