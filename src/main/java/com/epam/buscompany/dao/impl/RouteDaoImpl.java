package com.epam.buscompany.dao.impl;

import com.epam.buscompany.dao.RouteDao;
import com.epam.buscompany.model.entity.Route;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
        session.update(item);
    }

    public boolean remove(Route item) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(item);
        return session.contains(item);
    }

    public List<Route> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Criteria routeCriteria = session.createCriteria(Route.class);
        return routeCriteria.list();
    }

    public boolean findByNumber(int routeNumber) {
        Session session = sessionFactory.getCurrentSession();
        Criteria routeCriteria = session.createCriteria(Route.class);
        routeCriteria.add(Restrictions.eq("number", routeNumber));
        Route route = (Route)  routeCriteria.uniqueResult();
        return route != null;
    }
}
