/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobcandidatedatabse_ia;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author raghav
 */
@Entity
@Table(name = "SHORTLIST")
@NamedQueries({
    @NamedQuery(name = "Shortlist.findAll", query = "SELECT s FROM Shortlist s")
    , @NamedQuery(name = "Shortlist.findByShortlistId", query = "SELECT s FROM Shortlist s WHERE s.shortlistId = :shortlistId")
    , @NamedQuery(name = "Shortlist.findByStatus", query = "SELECT s FROM Shortlist s WHERE s.status = :status")})
public class Shortlist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SHORTLIST_ID")
    private Integer shortlistId;
    @Column(name = "STATUS")
    private Boolean status;
    @JoinColumn(name = "CANDIDATE_ID", referencedColumnName = "CANDIDATE_ID")
    @ManyToOne
    private Candidateinformation candidateId;
    @JoinColumn(name = "JOB_ID", referencedColumnName = "JOB_ID")
    @ManyToOne
    private Jobdescription jobId;

    public Shortlist() {
    }

    public Shortlist(Integer shortlistId) {
        this.shortlistId = shortlistId;
    }

    public Integer getShortlistId() {
        return shortlistId;
    }

    public void setShortlistId(Integer shortlistId) {
        this.shortlistId = shortlistId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Candidateinformation getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Candidateinformation candidateId) {
        this.candidateId = candidateId;
    }

    public Jobdescription getJobId() {
        return jobId;
    }

    public void setJobId(Jobdescription jobId) {
        this.jobId = jobId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shortlistId != null ? shortlistId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Shortlist)) {
            return false;
        }
        Shortlist other = (Shortlist) object;
        if ((this.shortlistId == null && other.shortlistId != null) || (this.shortlistId != null && !this.shortlistId.equals(other.shortlistId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jobcandidatedatabse_ia.Shortlist[ shortlistId=" + shortlistId + " ]";
    }
    
}
