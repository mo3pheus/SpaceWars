package util;

import space.artifacts.SpaceShip;

public class SpaceShipFactory {

    public static SpaceShip generateSpaceShip(String spaceshipConfig) {
        SpaceShip spaceShip = new SpaceShip();

        String[] configValues = spaceshipConfig.split(",");
        spaceShip.setName(configValues[0]);
        spaceShip.setTransponderId(configValues[1]);
        spaceShip.setId(Integer.parseInt(configValues[2]));
        spaceShip.setCrewCapacity(Integer.parseInt(configValues[3]));
        spaceShip.setRange(Integer.parseInt(configValues[4]));
        spaceShip.setNumGuns(Integer.parseInt(configValues[5]));
        spaceShip.setEngagedInBattle(false);
        spaceShip.setScore(0);
        spaceShip.setTimeAliveMs(System.currentTimeMillis());
        return spaceShip;
    }
}
