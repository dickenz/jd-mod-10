package service.impl;

import entity.Client;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import service.ClientCrudService;
import util.HibernateUtil;

import java.util.List;

public class ClientCrudServiceImpl implements ClientCrudService {

    private final SessionFactory sessionFactory;

    public ClientCrudServiceImpl() {
        this.sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    }

    @Override
    public Client createClient(Client client) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(client);
            session.getTransaction().commit();
            return client;
        }
    }

    @Override
    public Client updateClient(Client client) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(client);
            session.getTransaction().commit();
            return client;
        }
    }

    @Override
    public void deleteClient(Long clientId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Client client = session.get(Client.class, clientId);
            if (client != null) {
                session.remove(client);

            }
            session.getTransaction().commit();
        }
    }

    @Override
    public Client getClientById(Long clientId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Client.class, clientId);
        }
    }

    @Override
    public List<Client> getAllClients() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Client> query = builder.createQuery(Client.class);
            Root<Client> root = query.from(Client.class);
            query.select(root);
            return session.createQuery(query).getResultList();
        }
    }
}


