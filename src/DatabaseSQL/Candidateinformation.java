/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobcandidatedatabse_ia;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author raghav
 */
@Entity
@Table(name = "CANDIDATEINFORMATION")
@NamedQueries({
    @NamedQuery(name = "Candidateinformation.findAll", query = "SELECT c FROM Candidateinformation c")
    , @NamedQuery(name = "Candidateinformation.findByCandidateId", query = "SELECT c FROM Candidateinformation c WHERE c.candidateId = :candidateId")
    , @NamedQuery(name = "Candidateinformation.findByCandidateName", query = "SELECT c FROM Candidateinformation c WHERE c.candidateName = :candidateName")
    , @NamedQuery(name = "Candidateinformation.findByCandidateSurname", query = "SELECT c FROM Candidateinformation c WHERE c.candidateSurname = :candidateSurname")
    , @NamedQuery(name = "Candidateinformation.findByNationality", query = "SELECT c FROM Candidateinformation c WHERE c.nationality = :nationality")
    , @NamedQuery(name = "Candidateinformation.findByEnglish", query = "SELECT c FROM Candidateinformation c WHERE c.english = :english")
    , @NamedQuery(name = "Candidateinformation.findByBurmese", query = "SELECT c FROM Candidateinformation c WHERE c.burmese = :burmese")
    , @NamedQuery(name = "Candidateinformation.findByBangla", query = "SELECT c FROM Candidateinformation c WHERE c.bangla = :bangla")
    , @NamedQuery(name = "Candidateinformation.findByCommunication", query = "SELECT c FROM Candidateinformation c WHERE c.communication = :communication")
    , @NamedQuery(name = "Candidateinformation.findByExperience", query = "SELECT c FROM Candidateinformation c WHERE c.experience = :experience")
    , @NamedQuery(name = "Candidateinformation.findByBasicComputing", query = "SELECT c FROM Candidateinformation c WHERE c.basicComputing = :basicComputing")})
public class Candidateinformation implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CANDIDATE_ID")
    private Integer candidateId;
    @Column(name = "CANDIDATE_NAME")
    private String candidateName;
    @Column(name = "CANDIDATE_SURNAME")
    private String candidateSurname;
    @Column(name = "NATIONALITY")
    private String nationality;
    @Column(name = "ENGLISH")
    private Boolean english;
    @Column(name = "BURMESE")
    private Boolean burmese;
    @Column(name = "BANGLA")
    private Boolean bangla;
    @Column(name = "COMMUNICATION")
    private Boolean communication;
    @Column(name = "EXPERIENCE")
    private Boolean experience;
    @Column(name = "BASIC_COMPUTING")
    private Boolean basicComputing;
    @OneToMany(mappedBy = "candidateId")
    private Collection<Shortlist> shortlistCollection;

    public Candidateinformation() {
    }

    public Candidateinformation(Integer candidateId) {
        this.candidateId = candidateId;
    }

    public Integer getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Integer candidateId) {
        Integer oldCandidateId = this.candidateId;
        this.candidateId = candidateId;
        changeSupport.firePropertyChange("candidateId", oldCandidateId, candidateId);
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        String oldCandidateName = this.candidateName;
        this.candidateName = candidateName;
        changeSupport.firePropertyChange("candidateName", oldCandidateName, candidateName);
    }

    public String getCandidateSurname() {
        return candidateSurname;
    }

    public void setCandidateSurname(String candidateSurname) {
        String oldCandidateSurname = this.candidateSurname;
        this.candidateSurname = candidateSurname;
        changeSupport.firePropertyChange("candidateSurname", oldCandidateSurname, candidateSurname);
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        String oldNationality = this.nationality;
        this.nationality = nationality;
        changeSupport.firePropertyChange("nationality", oldNationality, nationality);
    }

    public Boolean getEnglish() {
        return english;
    }

    public void setEnglish(Boolean english) {
        Boolean oldEnglish = this.english;
        this.english = english;
        changeSupport.firePropertyChange("english", oldEnglish, english);
    }

    public Boolean getBurmese() {
        return burmese;
    }

    public void setBurmese(Boolean burmese) {
        Boolean oldBurmese = this.burmese;
        this.burmese = burmese;
        changeSupport.firePropertyChange("burmese", oldBurmese, burmese);
    }

    public Boolean getBangla() {
        return bangla;
    }

    public void setBangla(Boolean bangla) {
        Boolean oldBangla = this.bangla;
        this.bangla = bangla;
        changeSupport.firePropertyChange("bangla", oldBangla, bangla);
    }

    public Boolean getCommunication() {
        return communication;
    }

    public void setCommunication(Boolean communication) {
        Boolean oldCommunication = this.communication;
        this.communication = communication;
        changeSupport.firePropertyChange("communication", oldCommunication, communication);
    }

    public Boolean getExperience() {
        return experience;
    }

    public void setExperience(Boolean experience) {
        Boolean oldExperience = this.experience;
        this.experience = experience;
        changeSupport.firePropertyChange("experience", oldExperience, experience);
    }

    public Boolean getBasicComputing() {
        return basicComputing;
    }

    public void setBasicComputing(Boolean basicComputing) {
        Boolean oldBasicComputing = this.basicComputing;
        this.basicComputing = basicComputing;
        changeSupport.firePropertyChange("basicComputing", oldBasicComputing, basicComputing);
    }

    public Collection<Shortlist> getShortlistCollection() {
        return shortlistCollection;
    }

    public void setShortlistCollection(Collection<Shortlist> shortlistCollection) {
        this.shortlistCollection = shortlistCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (candidateId != null ? candidateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Candidateinformation)) {
            return false;
        }
        Candidateinformation other = (Candidateinformation) object;
        if ((this.candidateId == null && other.candidateId != null) || (this.candidateId != null && !this.candidateId.equals(other.candidateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jobcandidatedatabse_ia.Candidateinformation[ candidateId=" + candidateId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
