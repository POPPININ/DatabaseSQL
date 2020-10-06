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
@Table(name = "JOBDESCRIPTION")
@NamedQueries({
    @NamedQuery(name = "Jobdescription.findAll", query = "SELECT j FROM Jobdescription j")
    , @NamedQuery(name = "Jobdescription.findByJobId", query = "SELECT j FROM Jobdescription j WHERE j.jobId = :jobId")
    , @NamedQuery(name = "Jobdescription.findByJobName", query = "SELECT j FROM Jobdescription j WHERE j.jobName = :jobName")
    , @NamedQuery(name = "Jobdescription.findByEnglish", query = "SELECT j FROM Jobdescription j WHERE j.english = :english")
    , @NamedQuery(name = "Jobdescription.findByBurmese", query = "SELECT j FROM Jobdescription j WHERE j.burmese = :burmese")
    , @NamedQuery(name = "Jobdescription.findByBangla", query = "SELECT j FROM Jobdescription j WHERE j.bangla = :bangla")
    , @NamedQuery(name = "Jobdescription.findByCommunication", query = "SELECT j FROM Jobdescription j WHERE j.communication = :communication")
    , @NamedQuery(name = "Jobdescription.findByExperience", query = "SELECT j FROM Jobdescription j WHERE j.experience = :experience")
    , @NamedQuery(name = "Jobdescription.findByBasicComputing", query = "SELECT j FROM Jobdescription j WHERE j.basicComputing = :basicComputing")})
public class Jobdescription implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "JOB_ID")
    private Integer jobId;
    @Column(name = "JOB_NAME")
    private String jobName;
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
    @OneToMany(mappedBy = "jobId")
    private Collection<Shortlist> shortlistCollection;

    public Jobdescription() {
    }

    public Jobdescription(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        Integer oldJobId = this.jobId;
        this.jobId = jobId;
        changeSupport.firePropertyChange("jobId", oldJobId, jobId);
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        String oldJobName = this.jobName;
        this.jobName = jobName;
        changeSupport.firePropertyChange("jobName", oldJobName, jobName);
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
        hash += (jobId != null ? jobId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jobdescription)) {
            return false;
        }
        Jobdescription other = (Jobdescription) object;
        if ((this.jobId == null && other.jobId != null) || (this.jobId != null && !this.jobId.equals(other.jobId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jobcandidatedatabse_ia.Jobdescription[ jobId=" + jobId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
