//package service;
//
//import entity.Planet;
//import util.HibernateUtil;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//public class PlanetCrudService {
//
//    public Planet createPlanet(String id, String name) {
//        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
//            Transaction tx = session.beginTransaction();
//
//            Planet planet = new Planet();
//            planet.setId(id);
//            planet.setName(name);
//
//            //session.save(planet);
//            session.persist(planet);
//            tx.commit();
//
//            return planet;
//        }
//    }
//
//    public Planet getPlanetById(String id) {
//        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
//            return session.get(Planet.class, id);
//        }
//    }
//
//    public void updatePlanet(Planet planet) {
//        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
//            Transaction tx = session.beginTransaction();
//            //session.update(planet);
//            session.merge(planet);
//            tx.commit();
//        }
//    }
//
//    public void deletePlanet(Planet planet) {
//        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
//            Transaction tx = session.beginTransaction();
//            //session.delete(planet);
//            session.remove(planet);
//            tx.commit();
//        }
//    }
//}

package service;

import entity.Planet;

import java.util.List;

public interface PlanetCrudService {
    Planet createPlanet(Planet planet);

    Planet updatePlanet(Planet planet);

    void deletePlanet(String planetId);

    Planet getPlanetById(String planetId);

    List<Planet> getAllPlanets();
}