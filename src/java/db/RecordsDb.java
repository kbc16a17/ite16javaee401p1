package db;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.*;
import db.entities.Record;

@Stateless
public class RecordsDb {
    @PersistenceContext
    private EntityManager em;

    public void create(Record record) {
        em.persist(record);
    }

    public Record findById(Integer id) {
        TypedQuery<Record> query = em.createQuery("SELECT r FROM Record r WHERE r.id = :id", Record.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Record> findByAccountId(Integer accountId) {
        TypedQuery<Record> query = em.createQuery("SELECT r FROM Record r WHERE r.accountId = :id ORDER BY r.date", Record.class);
        query.setParameter("id", accountId);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
