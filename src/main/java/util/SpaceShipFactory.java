package util;

import org.codehaus.jackson.map.ObjectMapper;
import space.artifacts.Position;
import space.artifacts.SpaceShip;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class SpaceShipFactory {

    public static SpaceShip generateSpaceShip(String spaceshipConfig) {
        String[] configValues = spaceshipConfig.split(",");

        SpaceShip spaceShip = new SpaceShip();
        spaceShip.setName(configValues[0]);
        spaceShip.setTransponderId(UUID.randomUUID().toString());
        spaceShip.setCrewCapacity(Integer.parseInt(configValues[1]));
        spaceShip.setRange(Integer.parseInt(configValues[2]));
        spaceShip.setNumGuns(Integer.parseInt(configValues[3]));
        spaceShip.setArcSpeed(ThreadLocalRandom.current().nextDouble(1.0d, 10.0d));
        spaceShip.setEngagedInBattle(false);
        spaceShip.setScore(0);
        spaceShip.setTimeCreated(System.currentTimeMillis());

        return spaceShip;
    }

    public static SpaceShip generateSpaceShip(String spaceshipConfig, int radius) {
        SpaceShip spaceShip = generateSpaceShip(spaceshipConfig);

        double   theta    = ThreadLocalRandom.current().nextDouble(0.0d, 2.0d * Math.PI);
        Position position = new Position((int) (radius * Math.cos(theta)), (int) (radius * Math.sin(theta)));
        spaceShip.setPosition(position);

        return spaceShip;
    }

    public static String convertToString(SpaceShip spaceShip) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(spaceShip);
    }

    public static SpaceShip convertToPojo(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, SpaceShip.class);
    }
}
