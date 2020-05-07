/*
 * 
 */
package dao;

import entity.Utente;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class UtenteDao {

    private final EntityManager em;
    String PERSISTENCE_UNIT_NAME = "DEFAULT_PU";

    public UtenteDao() {
        em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
                .createEntityManager();
    }

    public UtenteDao(EntityManager em) {
        this.em = em;
    }

    public List<Utente> findAll() {
        /* 
        https://docs.oracle.com/javaee/6/tutorial/doc/bnbrg.html       
         */
        TypedQuery<Utente> typedQuery = (TypedQuery<Utente>) em.createNativeQuery("SELECT u FROM Utente u", Utente.class);
        List<Utente> personaList = typedQuery.setMaxResults(10).getResultList();
        return personaList;
    }

    public List<Utente> findAllQNative() {
        /*
        https://www.oracle.com/technetwork/articles/vasiliev-jpql-087123.html
         */
        List<Utente> personaList = (List<Utente>) em.createNativeQuery("SELECT * FROM Utente", Utente.class)
                .setMaxResults(10)
                .getResultList();
        return personaList;
    }

    public List<Utente> findByName(String username) {
        /* 
        https://docs.oracle.com/javaee/6/tutorial/doc/bnbrg.html       
         */
        TypedQuery<Utente> typedQuery = (TypedQuery<Utente>) em.createQuery("SELECT p FROM Utente p WHERE p.username LIKE :username", Utente.class)
                .setParameter("username", username);
        List<Utente> personaList = typedQuery.setMaxResults(10).getResultList();
        return personaList;
    }

    public boolean insertUtente(Utente u) {
        em.getTransaction().begin();
        try {
            em.persist(u);
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
