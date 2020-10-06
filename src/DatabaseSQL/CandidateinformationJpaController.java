/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobcandidatedatabse_ia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jobcandidatedatabse_ia.exceptions.NonexistentEntityException;

/**
 *
 * @author raghav
 */
public class CandidateinformationJpaController implements Serializable {

    public CandidateinformationJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Candidateinformation candidateinformation) {
        if (candidateinformation.getShortlistCollection() == null) {
            candidateinformation.setShortlistCollection(new ArrayList<Shortlist>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Shortlist> attachedShortlistCollection = new ArrayList<Shortlist>();
            for (Shortlist shortlistCollectionShortlistToAttach : candidateinformation.getShortlistCollection()) {
                shortlistCollectionShortlistToAttach = em.getReference(shortlistCollectionShortlistToAttach.getClass(), shortlistCollectionShortlistToAttach.getShortlistId());
                attachedShortlistCollection.add(shortlistCollectionShortlistToAttach);
            }
            candidateinformation.setShortlistCollection(attachedShortlistCollection);
            em.persist(candidateinformation);
            for (Shortlist shortlistCollectionShortlist : candidateinformation.getShortlistCollection()) {
                Candidateinformation oldCandidateIdOfShortlistCollectionShortlist = shortlistCollectionShortlist.getCandidateId();
                shortlistCollectionShortlist.setCandidateId(candidateinformation);
                shortlistCollectionShortlist = em.merge(shortlistCollectionShortlist);
                if (oldCandidateIdOfShortlistCollectionShortlist != null) {
                    oldCandidateIdOfShortlistCollectionShortlist.getShortlistCollection().remove(shortlistCollectionShortlist);
                    oldCandidateIdOfShortlistCollectionShortlist = em.merge(oldCandidateIdOfShortlistCollectionShortlist);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Candidateinformation candidateinformation) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Candidateinformation persistentCandidateinformation = em.find(Candidateinformation.class, candidateinformation.getCandidateId());
            Collection<Shortlist> shortlistCollectionOld = persistentCandidateinformation.getShortlistCollection();
            Collection<Shortlist> shortlistCollectionNew = candidateinformation.getShortlistCollection();
            Collection<Shortlist> attachedShortlistCollectionNew = new ArrayList<Shortlist>();
            for (Shortlist shortlistCollectionNewShortlistToAttach : shortlistCollectionNew) {
                shortlistCollectionNewShortlistToAttach = em.getReference(shortlistCollectionNewShortlistToAttach.getClass(), shortlistCollectionNewShortlistToAttach.getShortlistId());
                attachedShortlistCollectionNew.add(shortlistCollectionNewShortlistToAttach);
            }
            shortlistCollectionNew = attachedShortlistCollectionNew;
            candidateinformation.setShortlistCollection(shortlistCollectionNew);
            candidateinformation = em.merge(candidateinformation);
            for (Shortlist shortlistCollectionOldShortlist : shortlistCollectionOld) {
                if (!shortlistCollectionNew.contains(shortlistCollectionOldShortlist)) {
                    shortlistCollectionOldShortlist.setCandidateId(null);
                    shortlistCollectionOldShortlist = em.merge(shortlistCollectionOldShortlist);
                }
            }
            for (Shortlist shortlistCollectionNewShortlist : shortlistCollectionNew) {
                if (!shortlistCollectionOld.contains(shortlistCollectionNewShortlist)) {
                    Candidateinformation oldCandidateIdOfShortlistCollectionNewShortlist = shortlistCollectionNewShortlist.getCandidateId();
                    shortlistCollectionNewShortlist.setCandidateId(candidateinformation);
                    shortlistCollectionNewShortlist = em.merge(shortlistCollectionNewShortlist);
                    if (oldCandidateIdOfShortlistCollectionNewShortlist != null && !oldCandidateIdOfShortlistCollectionNewShortlist.equals(candidateinformation)) {
                        oldCandidateIdOfShortlistCollectionNewShortlist.getShortlistCollection().remove(shortlistCollectionNewShortlist);
                        oldCandidateIdOfShortlistCollectionNewShortlist = em.merge(oldCandidateIdOfShortlistCollectionNewShortlist);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = candidateinformation.getCandidateId();
                if (findCandidateinformation(id) == null) {
                    throw new NonexistentEntityException("The candidateinformation with id " + id + " no longer exists.");
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
            Candidateinformation candidateinformation;
            try {
                candidateinformation = em.getReference(Candidateinformation.class, id);
                candidateinformation.getCandidateId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The candidateinformation with id " + id + " no longer exists.", enfe);
            }
            Collection<Shortlist> shortlistCollection = candidateinformation.getShortlistCollection();
            for (Shortlist shortlistCollectionShortlist : shortlistCollection) {
                shortlistCollectionShortlist.setCandidateId(null);
                shortlistCollectionShortlist = em.merge(shortlistCollectionShortlist);
            }
            em.remove(candidateinformation);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Candidateinformation> findCandidateinformationEntities() {
        return findCandidateinformationEntities(true, -1, -1);
    }

    public List<Candidateinformation> findCandidateinformationEntities(int maxResults, int firstResult) {
        return findCandidateinformationEntities(false, maxResults, firstResult);
    }

    private List<Candidateinformation> findCandidateinformationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Candidateinformation.class));
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

    public Candidateinformation findCandidateinformation(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Candidateinformation.class, id);
        } finally {
            em.close();
        }
    }

    public int getCandidateinformationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Candidateinformation> rt = cq.from(Candidateinformation.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
