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
class Users {
    
    private int JobID;
    private String JobName;
    private boolean english;
    private boolean bangla;
    private boolean burmese;
    private boolean communicative;
    private boolean Basic_computing;
    private boolean experienced;

    
    
       public Users(int ID, String name,  boolean english, boolean burmese, boolean bangla, boolean communicative, boolean basic_computing, boolean experienced ){
       this.JobID = ID;
       this.JobName = name;
       this.english = english;
       this.bangla = bangla;
       this.burmese = burmese;
       this.communicative = communicative;
       this.Basic_computing = basic_computing;
       this.experienced = experienced;
    }

    
    
    public int getJobID(){ return JobID; }
    public String getJobName(){ return JobName; }
    public boolean getJobEnglish(){ return english;}
    public boolean getJobBangla(){ return bangla;}
    public boolean getJobBurmese(){ return burmese;}
    public boolean getJobCommunicative(){ return communicative;}
    public boolean getJobExperienced(){ return experienced;}
    public boolean getJobBasicComp(){ return Basic_computing;}
}
