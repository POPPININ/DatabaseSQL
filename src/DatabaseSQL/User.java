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
class User {

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
    private boolean docSuf;

    
    
       public User(int ID, String name, String surname, String nationality, boolean english, boolean burmese, boolean bangla, boolean communicative, boolean basic_comp, boolean experienced,  boolean docSuf){
       this.CandidateID = ID;
       this.CandidateName = name;
       this.CandidateSurname = surname;
       this.CandidateNationality = nationality;
       this.english = english;
       this.burmese = burmese;
       this.bangla = bangla;
       this.communicative = communicative;
       this.experienced = experienced;
       this.Basic_computing = basic_comp;      
       this.docSuf = docSuf;
    }

    
    
    public int getID(){ return CandidateID; }
    public String getCandName(){ return CandidateName; }
    public String getCandSurname(){ return CandidateSurname; }
    public String getNationality(){ return CandidateNationality; }
    public boolean getCandEnglish(){ return english;}
    public boolean getCandBurmese(){ return burmese;}
    public boolean getCandBangla(){ return bangla;}
    
    public boolean getCandCommunicative(){ return communicative;}
    public boolean getCandExperienced(){ return experienced;}
    public boolean getCandBasicComp(){ return Basic_computing;}
    public boolean getDocSuf(){return docSuf; }
    
}
