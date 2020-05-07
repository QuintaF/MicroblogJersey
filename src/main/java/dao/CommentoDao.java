/*
 * 
 */
package dao;

import entity.Commento;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class CommentoDao {

    private final EntityManager em;
    String PERSISTENCE_UNIT_NAME = "DEFAULT_PU";

    public CommentoDao() {
        em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
                .createEntityManager();
    }

    public CommentoDao(EntityManager em) {
        this.em = em;
    }

    public List<Commento> findAll() {
        /* 
        https://docs.oracle.com/javaee/6/tutorial/doc/bnbrg.html       
         */
        TypedQuery<Commento> typedQuery = (TypedQuery<Commento>) em.createNativeQuery("SELECT * FROM Commento", Commento.class);
        List<Commento> commentList = typedQuery.setMaxResults(10).getResultList();
        return commentList;
    }

    public List<Commento> findAllQNative() {
        /*
        https://www.oracle.com/technetwork/articles/vasiliev-jpql-087123.html
         */
        List<Commento> commentList = (List<Commento>) em.createNativeQuery("SELECT * FROM Utente", Commento.class)
                .setMaxResults(10)
                .getResultList();
        return commentList;
    }

    public boolean insertCommento(Commento c) {
        em.getTransaction().begin();
        try {
            em.persist(c);
            // -- workaround cache entity manager
            em.flush();
            em.clear();
            // --
            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }
    
}
