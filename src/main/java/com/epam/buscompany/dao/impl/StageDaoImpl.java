package com.epam.buscompany.dao.impl;

import com.epam.buscompany.dao.StageDao;
import com.epam.buscompany.model.entity.Stage;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
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
        session.update(item);
    }

    public boolean remove(Stage item) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(item);
        return session.contains(item);
    }

    public List<Stage> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        Criteria stageCriteria = session.createCriteria(Stage.class);
        return stageCriteria.list();
    }

    public boolean findByNumber(int stageNumber) {
        Session session = sessionFactory.getCurrentSession();
        Criteria stageCriteria = session.createCriteria(Stage.class);
        stageCriteria.add(Restrictions.eq("number", stageNumber));
        Stage stage = (Stage) stageCriteria.uniqueResult();
        return stage != null;
    }
}
