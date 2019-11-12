package com.epam.buscompany.dao.impl;

import com.epam.buscompany.dao.BusDao;
import com.epam.buscompany.model.entity.Bus;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Transactional
public class BusDaoImpl implements BusDao {

    @Autowired
    private SessionFactory sessionFactory;


    public void create(Bus item) {
        Session session = sessionFactory.getCurrentSession();
        session.save(item);
    }

    public void update(Bus item) {
        Session session = sessionFactory.getCurrentSession();
        session.update(item);
    }

    public boolean remove(Bus item) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(item);
        return session.contains(item);
    }

    public List<Bus> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        Criteria busCriteria = session.createCriteria(Bus.class);
        return busCriteria.list();
    }

    public boolean findByNumber(int registerNumber) {
        Session session = sessionFactory.getCurrentSession();
        Criteria busCriteria = session.createCriteria(Bus.class);
        busCriteria.add(Restrictions.eq("registerNumber", registerNumber));
        Bus bus = (Bus) busCriteria.uniqueResult();
        return bus != null;
    }


}
