package com.epam.buscompany.dao.impl;

import com.epam.buscompany.dao.StageDao;
import com.epam.buscompany.model.entity.Stage;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Transactional
public class StageDaoImpl implements StageDao {

    @Autowired
    private SessionFactory sessionFactory;


    public void create(Stage item) {
        Session session = sessionFactory.getCurrentSession();
        session.save(item);
    }

    public void update(Stage item) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(item);
    }

    public void remove(int stageNumber) {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("delete Stage where number = :stageNumber");
        q.setParameter("stageNumber", stageNumber);
        q.executeUpdate();
    }

    public List<Stage> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Stage> cq = cb.createQuery(Stage.class);
        Root<Stage> rootEntry = cq.from(Stage.class);
        CriteriaQuery<Stage> all = cq.select(rootEntry);

        TypedQuery<Stage> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public Stage findByNumber(int stageNumber) {
        Session session = sessionFactory.getCurrentSession();
        Criteria stageCriteria = session.createCriteria(Stage.class);
        stageCriteria.add(Restrictions.eq("number", stageNumber));
        return (Stage) stageCriteria.uniqueResult();
    }
}
