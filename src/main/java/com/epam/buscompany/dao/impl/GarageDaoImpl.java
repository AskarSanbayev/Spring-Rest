package com.epam.buscompany.dao.impl;

import com.epam.buscompany.dao.GarageDao;
import com.epam.buscompany.model.entity.Garage;
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
public class GarageDaoImpl implements GarageDao {

    @Autowired
    private SessionFactory sessionFactory;


    public void create(Garage item) {
        Session session = sessionFactory.getCurrentSession();
        session.save(item);
    }

    public void update(Garage item) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(item);
    }

    public void remove(int garageNumber) {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("delete Garage where number = :garageNumber");
        q.setParameter("garageNumber",garageNumber);
        q.executeUpdate();
    }

    public List<Garage> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Garage> cq = cb.createQuery(Garage.class);
        Root<Garage> rootEntry = cq.from(Garage.class);
        CriteriaQuery<Garage> all = cq.select(rootEntry);

        TypedQuery<Garage> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public Garage findByNumber(int garageNumber) {
        Session session = sessionFactory.getCurrentSession();
        Criteria garageCriteria = session.createCriteria(Garage.class);
        garageCriteria.add(Restrictions.eq("number", garageNumber));
        return (Garage) garageCriteria.uniqueResult();
    }

}
