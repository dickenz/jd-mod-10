//package service;
//
//import entity.Client;
//import util.HibernateUtil;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//public class ClientCrudService {
//
//    public Client createClient(String name) {
//        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
//            Transaction tx = session.beginTransaction();
//
//            Client client = new Client();
//            client.setName(name);
//
//            //session.save(client);
//            session.persist(client);
//            tx.commit();
//
//            return client;
//        }
//    }
//
//    public Client getClientById(Long id) {
//        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
//            return session.get(Client.class, id);
//        }
//    }
//
//    public void updateClient(Client client) {
//        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
//            Transaction tx = session.beginTransaction();
//            //session.update(client);
//            session.merge(client);
//            tx.commit();
//        }
//    }
//
//    public void deleteClient(Client client) {
//        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
//            Transaction tx = session.beginTransaction();
//            //session.delete(client);
//            session.remove(client);
//            tx.commit();
//        }
//    }
//}
package service;

import entity.Client;

import java.util.List;

public interface ClientCrudService {
    Client createClient(Client client);

    Client updateClient(Client client);

    void deleteClient(Long clientId);

    Client getClientById(Long clientId);

    List<Client> getAllClients();
}