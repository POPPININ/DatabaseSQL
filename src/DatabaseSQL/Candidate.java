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
public class Candidate {
    
    private int CandidateID;
    private String CandidateName;
    private String CandidateSurname;
    private String CandidateNationality;
    private boolean english;
    private boolean bangla;
    private boolean burmese;
    private boolean communicative;
    private boolean Basic_computing;
    private boolean experienced;
    
    public Candidate(int ID, String name, String surname, String nationality, boolean english, boolean bangla, boolean burmese, boolean communicative, boolean basic_computing, boolean experienced ){
       this.CandidateID = ID;
       this.CandidateName = name;
       this.CandidateSurname = surname;
       this.CandidateNationality = nationality;
       this.english = english;
       this.bangla = bangla;
       this.burmese = burmese;
       this.communicative = communicative;
       this.Basic_computing = basic_computing;
       this.experienced = experienced;
    }

    
    
    public int getID(){ return CandidateID; }
    public String getCandName(){ return CandidateName; }
    public String getCandSurname(){ return CandidateSurname; }
    public String getNationality(){ return CandidateNationality; }
    public boolean getCandEnglish(){ return english;}
    public boolean getCandBangla(){ return bangla;}
    public boolean getCandBurmese(){ return burmese;}
    public boolean getCandCommunicative(){ return communicative;}
    public boolean getCandExperienced(){ return experienced;}
    public boolean getCandBasicComp(){ return Basic_computing;}
    
     
     
    
    
}
