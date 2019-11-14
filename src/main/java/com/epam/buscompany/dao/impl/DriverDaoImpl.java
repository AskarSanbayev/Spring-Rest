package com.epam.buscompany.dao.impl;

import com.epam.buscompany.dao.DriverDao;
import com.epam.buscompany.model.entity.Driver;
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
public class DriverDaoImpl implements DriverDao {

    @Autowired
    private SessionFactory sessionFactory;


    public void create(Driver item) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(item);
    }

    public void update(Driver item) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(item);
    }

    public void remove(int driverNumber) {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("delete Driver where number = :driverNumber");
        q.setParameter("driverNumber",driverNumber);
        q.executeUpdate();
    }

    public List<Driver> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Driver> cq = cb.createQuery(Driver.class);
        Root<Driver> rootEntry = cq.from(Driver.class);
        CriteriaQuery<Driver> all = cq.select(rootEntry);

        TypedQuery<Driver> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public Driver findByNumber(int driverNumber) {
        Session session = sessionFactory.getCurrentSession();
        Criteria driverCriteria = session.createCriteria(Driver.class);
        driverCriteria.add(Restrictions.eq("number", driverNumber));
        return (Driver) driverCriteria.uniqueResult();
    }
}
