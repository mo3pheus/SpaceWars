package util;

import space.artifacts.IsSpaceShip;

public class BattleUtil {

    public enum SpaceMetric {
        CREW_CAPACITY,
        NUM_GUNS,
        RANGE;
    }

    public static IsSpaceShip getBattleResult(IsSpaceShip spaceShipA, IsSpaceShip spaceShipB, SpaceMetric choice) throws IllegalArgumentException {
        IsSpaceShip winner;
        switch (choice) {
            case CREW_CAPACITY: {
                winner = (spaceShipA.getCrewCapacity() > spaceShipB.getCrewCapacity()) ? spaceShipA : spaceShipB;
            }
            break;
            case RANGE: {
                winner = (spaceShipA.getRange() > spaceShipB.getRange()) ? spaceShipA : spaceShipB;
            }
            break;
            case NUM_GUNS: {
                winner = (spaceShipA.getNumGuns() > spaceShipB.getNumGuns()) ? spaceShipA : spaceShipB;
            }
            break;
            default: {
                throw new IllegalArgumentException("Choice supplied " + choice + " is invalid");
            }
        }
        return winner;
    }



}
