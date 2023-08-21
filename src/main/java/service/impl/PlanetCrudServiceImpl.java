package service.impl;

import entity.Planet;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import service.PlanetCrudService;
import util.HibernateUtil;

import java.util.List;

public class PlanetCrudServiceImpl implements PlanetCrudService {

    private final SessionFactory sessionFactory;

    public PlanetCrudServiceImpl() {
        this.sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    }

    @Override
    public Planet createPlanet(Planet planet) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(planet);
            session.getTransaction().commit();
            return planet;
        }
    }

    @Override
    public Planet updatePlanet(Planet planet) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(planet);
            session.getTransaction().commit();
            return planet;
        }
    }

    @Override
    public void deletePlanet(String planetId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Planet planet = session.get(Planet.class, planetId);
            if (planet != null) {
                session.remove(planet);
            }
            session.getTransaction().commit();
        }
    }

    @Override
    public Planet getPlanetById(String planetId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Planet.class, planetId);
        }
    }

    @Override
    public List<Planet> getAllPlanets() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Planet> query = builder.createQuery(Planet.class);
            Root<Planet> root = query.from(Planet.class);
            query.select(root);
            return session.createQuery(query).getResultList();
        }
    }
}