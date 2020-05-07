/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.*;

/**
 *
 * @author Marco
 */
public class DAO {

    String PERSISTENCE_UNIT_NAME = "DEFAULT_PU";
    static EntityManager em;
    private final UtenteDao utenteDao;
    private final PostDao postDao;
    private final CommentoDao commentoDao;

    public DAO() {
        em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
        utenteDao = new UtenteDao(em);
        postDao = new PostDao(em);
        commentoDao = new CommentoDao(em);
    }

    public UtenteDao getUtenteDao() {
        return utenteDao;
    }

    public PostDao getPostDao() {
        return postDao;
    }
    public CommentoDao getCommentoDao() {
        return commentoDao;
    }
}
