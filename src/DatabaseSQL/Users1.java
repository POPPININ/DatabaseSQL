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
class Users1 {
    int shortlistID;
    int jobID;
    int candID;
    
    
    
    public Users1(int shortlistID, int jobID, int candID){
        this.jobID = jobID;
        this.shortlistID = shortlistID;
        this.candID = candID;
        
    }
    
    public int getShortlistID(){ return shortlistID; }
    public int getJobsID(){ return jobID; }
    public int getCandsID(){return candID;}
    
    
}
