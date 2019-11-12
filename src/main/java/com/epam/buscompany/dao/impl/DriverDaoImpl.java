package com.epam.buscompany.dao.impl;

import com.epam.buscompany.dao.DriverDao;
import com.epam.buscompany.model.entity.Driver;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
        session.save(item);
    }

    public void update(Driver item) {
        Session session = sessionFactory.getCurrentSession();
        session.update(item);
    }

    public boolean remove(Driver item) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(item);
        return session.contains(item);
    }

    public List<Driver> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Criteria driverCriteria = session.createCriteria(Driver.class);
        return driverCriteria.list();
    }

    public boolean findByNumber(int driverNumber) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Driver> cr = cb.createQuery(Driver.class);
        Root<Driver> root = cr.from(Driver.class);
        cr.select(root).where(cb.equal(root.get("number"), driverNumber));
        Query<Driver> query = session.createQuery(cr);
        Driver driver = query.getSingleResult();
        return driver != null;
    }
}
