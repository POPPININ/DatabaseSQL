/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobcandidatedatabse_ia;

/**
 *
 * @author raghav
 */
public class User2 {
    
    private int shortID;
    private int jobID;
    private int candID;
    
    public User2(int shortID, int jobID, int candID){
        this.shortID = shortID;
        this.jobID = jobID;
        this.candID = candID;
    }
    
    public int getShortID(){ return shortID; }
    public int getJobID(){ return jobID; }
    public int getCandID(){ return candID; }
    
    
}
