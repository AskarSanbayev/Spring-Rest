package com.epam.buscompany.dao.impl;

import com.epam.buscompany.dao.RouteDao;
import com.epam.buscompany.model.entity.Route;
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
public class RouteDaoImpl implements RouteDao {

    @Autowired
    private SessionFactory sessionFactory;


    public void create(Route item) {
        Session session = sessionFactory.getCurrentSession();
        session.save(item);
    }

    public void update(Route item) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(item);
    }

    public void remove(int routeNumber) {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("delete Route where number = :routeNumber");
        q.setParameter("routeNumber", routeNumber);
        q.executeUpdate();
    }

    public List<Route> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Route> cq = cb.createQuery(Route.class);
        Root<Route> rootEntry = cq.from(Route.class);
        CriteriaQuery<Route> all = cq.select(rootEntry);

        TypedQuery<Route> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public Route findByNumber(int routeNumber) {
        Session session = sessionFactory.getCurrentSession();
        Criteria routeCriteria = session.createCriteria(Route.class);
        routeCriteria.add(Restrictions.eq("number", routeNumber));
        return (Route) routeCriteria.uniqueResult();
    }
}
