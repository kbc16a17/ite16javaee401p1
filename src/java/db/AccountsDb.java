package db;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.*;
import db.entities.Account;

@Stateless
public class AccountsDb {
    @PersistenceContext
    private EntityManager em;
    
    public void create(Account account) {
        em.persist(account);
    }
    
    public Account find(Integer userId) {
        return em.find(Account.class, userId);
    }

    public Account find(String username, String password) {
        TypedQuery<Account> query = em.createQuery("SELECT a FROM Account a WHERE a.username=:username AND a.password=:password", Account.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }    
}
