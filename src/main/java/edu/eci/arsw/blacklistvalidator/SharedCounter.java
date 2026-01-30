package edu.eci.arsw.blacklistvalidator;

/**
 * 
 * @author María Belén Quintero
 * @author Nikolas Martinez Rivera
 */
public class SharedCounter {
    private int totalOccurrences;
    private final int alarm;
    
    public SharedCounter(int alarm) {
        this.totalOccurrences = 0;
        this.alarm = alarm;
    }
    
    public synchronized void increment() {
        totalOccurrences++;
    }
    
    public synchronized boolean shouldStop() {
        return totalOccurrences >= alarm;
    }

    public synchronized int getTotalOccurrences() {
        return totalOccurrences;
    }
}
