/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobcandidatedatabse_ia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jobcandidatedatabse_ia.exceptions.NonexistentEntityException;

/**
 *
 * @author raghav
 */
public class ShortlistJpaController implements Serializable {

    public ShortlistJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Shortlist shortlist) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Candidateinformation candidateId = shortlist.getCandidateId();
            if (candidateId != null) {
                candidateId = em.getReference(candidateId.getClass(), candidateId.getCandidateId());
                shortlist.setCandidateId(candidateId);
            }
            Jobdescription jobId = shortlist.getJobId();
            if (jobId != null) {
                jobId = em.getReference(jobId.getClass(), jobId.getJobId());
                shortlist.setJobId(jobId);
            }
            em.persist(shortlist);
            if (candidateId != null) {
                candidateId.getShortlistCollection().add(shortlist);
                candidateId = em.merge(candidateId);
            }
            if (jobId != null) {
                jobId.getShortlistCollection().add(shortlist);
                jobId = em.merge(jobId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Shortlist shortlist) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Shortlist persistentShortlist = em.find(Shortlist.class, shortlist.getShortlistId());
            Candidateinformation candidateIdOld = persistentShortlist.getCandidateId();
            Candidateinformation candidateIdNew = shortlist.getCandidateId();
            Jobdescription jobIdOld = persistentShortlist.getJobId();
            Jobdescription jobIdNew = shortlist.getJobId();
            if (candidateIdNew != null) {
                candidateIdNew = em.getReference(candidateIdNew.getClass(), candidateIdNew.getCandidateId());
                shortlist.setCandidateId(candidateIdNew);
            }
            if (jobIdNew != null) {
                jobIdNew = em.getReference(jobIdNew.getClass(), jobIdNew.getJobId());
                shortlist.setJobId(jobIdNew);
            }
            shortlist = em.merge(shortlist);
            if (candidateIdOld != null && !candidateIdOld.equals(candidateIdNew)) {
                candidateIdOld.getShortlistCollection().remove(shortlist);
                candidateIdOld = em.merge(candidateIdOld);
            }
            if (candidateIdNew != null && !candidateIdNew.equals(candidateIdOld)) {
                candidateIdNew.getShortlistCollection().add(shortlist);
                candidateIdNew = em.merge(candidateIdNew);
            }
            if (jobIdOld != null && !jobIdOld.equals(jobIdNew)) {
                jobIdOld.getShortlistCollection().remove(shortlist);
                jobIdOld = em.merge(jobIdOld);
            }
            if (jobIdNew != null && !jobIdNew.equals(jobIdOld)) {
                jobIdNew.getShortlistCollection().add(shortlist);
                jobIdNew = em.merge(jobIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = shortlist.getShortlistId();
                if (findShortlist(id) == null) {
                    throw new NonexistentEntityException("The shortlist with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Shortlist shortlist;
            try {
                shortlist = em.getReference(Shortlist.class, id);
                shortlist.getShortlistId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The shortlist with id " + id + " no longer exists.", enfe);
            }
            Candidateinformation candidateId = shortlist.getCandidateId();
            if (candidateId != null) {
                candidateId.getShortlistCollection().remove(shortlist);
                candidateId = em.merge(candidateId);
            }
            Jobdescription jobId = shortlist.getJobId();
            if (jobId != null) {
                jobId.getShortlistCollection().remove(shortlist);
                jobId = em.merge(jobId);
            }
            em.remove(shortlist);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Shortlist> findShortlistEntities() {
        return findShortlistEntities(true, -1, -1);
    }

    public List<Shortlist> findShortlistEntities(int maxResults, int firstResult) {
        return findShortlistEntities(false, maxResults, firstResult);
    }

    private List<Shortlist> findShortlistEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Shortlist.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Shortlist findShortlist(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Shortlist.class, id);
        } finally {
            em.close();
        }
    }

    public int getShortlistCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Shortlist> rt = cq.from(Shortlist.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
