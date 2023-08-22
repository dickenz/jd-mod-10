import entity.Client;
import entity.Planet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import service.CrudService;
import service.impl.ClientCrudServiceImpl;
import service.impl.PlanetCrudServiceImpl;
import util.DatabaseMigration;

public class ApplicationStartup {

    public static void main(String[] args) {
        loadHibernateProperties();
        migrateDatabase();
        runClientPlanetCrudService();
    }

    private static void loadHibernateProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/hibernate.properties"));
            properties.forEach((key, value) -> System.setProperty((String) key, (String) value));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void migrateDatabase() {
        DatabaseMigration.main(null);
    }

    private static void runClientPlanetCrudService() {
        CrudService<Client, Long> clientService = new ClientCrudServiceImpl();
        CrudService<Planet, String> planetService = new PlanetCrudServiceImpl();

        Client newClient = new Client();
        newClient.setName("John Doe");
        clientService.create(newClient);

        Planet newPlanet = new Planet();

        newPlanet.setId("EARTH");
        newPlanet.setName("Earth");
        planetService.create(newPlanet);

        clientService.delete(newClient.getId());
        planetService.delete(newPlanet.getId());

    }
}