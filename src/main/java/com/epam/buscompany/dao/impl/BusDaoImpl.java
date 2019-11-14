package com.epam.buscompany.dao.impl;

import com.epam.buscompany.dao.BusDao;
import com.epam.buscompany.model.entity.Bus;
import org.hibernate.Criteria;
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
public class BusDaoImpl implements BusDao {

    @Autowired
    private SessionFactory sessionFactory;


    public void create(Bus item) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(item);
    }

    public void update(Bus item) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(item);
    }

    public List<Bus> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Bus> cq = cb.createQuery(Bus.class);
        Root<Bus> rootEntry = cq.from(Bus.class);
        CriteriaQuery<Bus> all = cq.select(rootEntry);

        TypedQuery<Bus> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public Bus findByNumber(int registerNumber) {
        Session session = sessionFactory.getCurrentSession();
        Criteria busCriteria = session.createCriteria(Bus.class);
        busCriteria.add(Restrictions.eq("registerNumber", registerNumber));
        return (Bus) busCriteria.uniqueResult();
    }

    @Override
    public void remove(int registerNumber) {
        Session session = sessionFactory.getCurrentSession();
        Bus bus = session.get(Bus.class, registerNumber);
        session.delete(bus);
    }


}
