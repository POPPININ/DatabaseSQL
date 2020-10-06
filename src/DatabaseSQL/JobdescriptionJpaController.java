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
public class JobdescriptionJpaController implements Serializable {

    public JobdescriptionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Jobdescription jobdescription) {
        if (jobdescription.getShortlistCollection() == null) {
            jobdescription.setShortlistCollection(new ArrayList<Shortlist>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Shortlist> attachedShortlistCollection = new ArrayList<Shortlist>();
            for (Shortlist shortlistCollectionShortlistToAttach : jobdescription.getShortlistCollection()) {
                shortlistCollectionShortlistToAttach = em.getReference(shortlistCollectionShortlistToAttach.getClass(), shortlistCollectionShortlistToAttach.getShortlistId());
                attachedShortlistCollection.add(shortlistCollectionShortlistToAttach);
            }
            jobdescription.setShortlistCollection(attachedShortlistCollection);
            em.persist(jobdescription);
            for (Shortlist shortlistCollectionShortlist : jobdescription.getShortlistCollection()) {
                Jobdescription oldJobIdOfShortlistCollectionShortlist = shortlistCollectionShortlist.getJobId();
                shortlistCollectionShortlist.setJobId(jobdescription);
                shortlistCollectionShortlist = em.merge(shortlistCollectionShortlist);
                if (oldJobIdOfShortlistCollectionShortlist != null) {
                    oldJobIdOfShortlistCollectionShortlist.getShortlistCollection().remove(shortlistCollectionShortlist);
                    oldJobIdOfShortlistCollectionShortlist = em.merge(oldJobIdOfShortlistCollectionShortlist);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Jobdescription jobdescription) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Jobdescription persistentJobdescription = em.find(Jobdescription.class, jobdescription.getJobId());
            Collection<Shortlist> shortlistCollectionOld = persistentJobdescription.getShortlistCollection();
            Collection<Shortlist> shortlistCollectionNew = jobdescription.getShortlistCollection();
            Collection<Shortlist> attachedShortlistCollectionNew = new ArrayList<Shortlist>();
            for (Shortlist shortlistCollectionNewShortlistToAttach : shortlistCollectionNew) {
                shortlistCollectionNewShortlistToAttach = em.getReference(shortlistCollectionNewShortlistToAttach.getClass(), shortlistCollectionNewShortlistToAttach.getShortlistId());
                attachedShortlistCollectionNew.add(shortlistCollectionNewShortlistToAttach);
            }
            shortlistCollectionNew = attachedShortlistCollectionNew;
            jobdescription.setShortlistCollection(shortlistCollectionNew);
            jobdescription = em.merge(jobdescription);
            for (Shortlist shortlistCollectionOldShortlist : shortlistCollectionOld) {
                if (!shortlistCollectionNew.contains(shortlistCollectionOldShortlist)) {
                    shortlistCollectionOldShortlist.setJobId(null);
                    shortlistCollectionOldShortlist = em.merge(shortlistCollectionOldShortlist);
                }
            }
            for (Shortlist shortlistCollectionNewShortlist : shortlistCollectionNew) {
                if (!shortlistCollectionOld.contains(shortlistCollectionNewShortlist)) {
                    Jobdescription oldJobIdOfShortlistCollectionNewShortlist = shortlistCollectionNewShortlist.getJobId();
                    shortlistCollectionNewShortlist.setJobId(jobdescription);
                    shortlistCollectionNewShortlist = em.merge(shortlistCollectionNewShortlist);
                    if (oldJobIdOfShortlistCollectionNewShortlist != null && !oldJobIdOfShortlistCollectionNewShortlist.equals(jobdescription)) {
                        oldJobIdOfShortlistCollectionNewShortlist.getShortlistCollection().remove(shortlistCollectionNewShortlist);
                        oldJobIdOfShortlistCollectionNewShortlist = em.merge(oldJobIdOfShortlistCollectionNewShortlist);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = jobdescription.getJobId();
                if (findJobdescription(id) == null) {
                    throw new NonexistentEntityException("The jobdescription with id " + id + " no longer exists.");
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
            Jobdescription jobdescription;
            try {
                jobdescription = em.getReference(Jobdescription.class, id);
                jobdescription.getJobId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The jobdescription with id " + id + " no longer exists.", enfe);
            }
            Collection<Shortlist> shortlistCollection = jobdescription.getShortlistCollection();
            for (Shortlist shortlistCollectionShortlist : shortlistCollection) {
                shortlistCollectionShortlist.setJobId(null);
                shortlistCollectionShortlist = em.merge(shortlistCollectionShortlist);
            }
            em.remove(jobdescription);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Jobdescription> findJobdescriptionEntities() {
        return findJobdescriptionEntities(true, -1, -1);
    }

    public List<Jobdescription> findJobdescriptionEntities(int maxResults, int firstResult) {
        return findJobdescriptionEntities(false, maxResults, firstResult);
    }

    private List<Jobdescription> findJobdescriptionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Jobdescription.class));
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

    public Jobdescription findJobdescription(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Jobdescription.class, id);
        } finally {
            em.close();
        }
    }

    public int getJobdescriptionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Jobdescription> rt = cq.from(Jobdescription.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
