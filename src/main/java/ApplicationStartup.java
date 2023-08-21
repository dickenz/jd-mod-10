import entity.Client;
import entity.Planet;

import org.flywaydb.core.Flyway;

import service.ClientCrudService;
import service.PlanetCrudService;
import service.impl.ClientCrudServiceImpl;
import service.impl.PlanetCrudServiceImpl;

public class ApplicationStartup {

    public static void main(String[] args) {
        migrateDatabase();

        runClientPlanetCrudService();
    }

    private static void migrateDatabase() {
        Flyway flyway = Flyway.configure().dataSource("jdbc:h2:F:/java/test", "sa", "").load();
        flyway.migrate();
    }

    private static void runClientPlanetCrudService() {
        ClientCrudService clientService = new ClientCrudServiceImpl();
        PlanetCrudService planetService = new PlanetCrudServiceImpl();

        Client newClient = new Client();
        newClient.setName("John Doe");
        clientService.createClient(newClient);
        Planet newPlanet = new Planet();

        newPlanet.setId("EARTH");
        newPlanet.setName("Earth");
        planetService.createPlanet(newPlanet);

        clientService.deleteClient(newClient.getId());
        planetService.deletePlanet(newPlanet.getId());

    }
}