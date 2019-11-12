package com.epam.buscompany.dao.impl;

import com.epam.buscompany.dao.GarageDao;
import com.epam.buscompany.model.entity.Garage;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class GarageDaoImpl implements GarageDao {

    @Autowired
    private SessionFactory sessionFactory;


    public void create(Garage item) {
        Session session = sessionFactory.getCurrentSession();
        session.save(item);
    }

    public void update(Garage item) {
        Session session = sessionFactory.getCurrentSession();
        session.update(item);
    }

    public boolean remove(Garage item) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(item);
        return session.contains(item);
    }

    public List<Garage> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Criteria garageCriteria = session.createCriteria(Garage.class);
        return garageCriteria.list();
    }

    public boolean findByNumber(int garageNumber) {
        Session session = sessionFactory.getCurrentSession();
        Criteria garageCriteria = session.createCriteria(Garage.class);
        garageCriteria.add(Restrictions.eq("number",garageNumber));
        Garage garage = (Garage) garageCriteria.uniqueResult();
        return garage != null;
    }

}
