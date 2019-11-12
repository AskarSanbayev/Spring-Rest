package com.epam.buscompany.dao.impl;

import com.epam.buscompany.dao.TownDao;
import com.epam.buscompany.model.entity.Town;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
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
        session.update(item);
    }

    public boolean remove(Town item) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(item);
        return session.contains(item);
    }

    public List<Town> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        Criteria townCriteria = session.createCriteria(Town.class);
        return townCriteria.list();
    }

    public boolean findByName(String townName) {
        Session session = sessionFactory.getCurrentSession();
        Criteria townCriteria = session.createCriteria(Town.class);
        townCriteria.add(Restrictions.eq("name", townName));
        Town town = (Town) townCriteria.uniqueResult();
        return town != null;
    }
}
