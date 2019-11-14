package com.epam.buscompany.dao.impl;

import com.epam.buscompany.dao.TownDao;
import com.epam.buscompany.model.entity.Town;
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
public class TownDaoImpl implements TownDao {

    @Autowired
    private SessionFactory sessionFactory;


    public void create(Town item) {
        Session session = sessionFactory.getCurrentSession();
        session.save(item);
    }

    public void update(Town item) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(item);
    }

    public void remove(String townName) {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("delete Town where name = :townName");
        q.setParameter("townName", townName);
        q.executeUpdate();
    }

    public List<Town> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Town> cq = cb.createQuery(Town.class);
        Root<Town> rootEntry = cq.from(Town.class);
        CriteriaQuery<Town> all = cq.select(rootEntry);

        TypedQuery<Town> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public Town findByName(String townName) {
        Session session = sessionFactory.getCurrentSession();
        Criteria townCriteria = session.createCriteria(Town.class);
        townCriteria.add(Restrictions.eq("name", townName));
        return (Town) townCriteria.uniqueResult();
    }
}
