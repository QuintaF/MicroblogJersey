/*
 * 
 */
package dao;

import entity.Post;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class PostDao {

    private final EntityManager em;
    String PERSISTENCE_UNIT_NAME = "DEFAULT_PU";

    public PostDao() {
        em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
                .createEntityManager();
    }

    public PostDao(EntityManager em) {
        this.em = em;
    }

    public List<Post> findAll() {
        /* 
        https://docs.oracle.com/javaee/6/tutorial/doc/bnbrg.html       
         */
        TypedQuery<Post> typedQuery = (TypedQuery<Post>) em.createNativeQuery("SELECT * FROM Post", Post.class);
        List<Post> personaList = typedQuery.setMaxResults(10).getResultList();
        return personaList;
    }

    public List<Post> findAllQNative() {
        /*
        https://www.oracle.com/technetwork/articles/vasiliev-jpql-087123.html
         */
        List<Post> personaList = (List<Post>) em.createNativeQuery("SELECT * FROM Post", Post.class)
                .setMaxResults(10)
                .getResultList();
        return personaList;
    }

    public List<Post> findPostById(Long id) {
        /*
        https://www.oracle.com/technetwork/articles/vasiliev-jpql-087123.html
         */
        List<Post> post = (List<Post>) em.createNativeQuery("SELECT * FROM Post p WHERE p.id = ?1", Post.class)
                .setParameter(1,id)
                .getResultList();
        return post;
    }
    
    public boolean insertPost(Post p) {
        em.getTransaction().begin();
        try {
            em.persist(p);
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
