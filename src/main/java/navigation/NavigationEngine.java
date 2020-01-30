package navigation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.artifacts.PolarCoordinate;
import space.artifacts.Position;
import space.artifacts.SpaceShip;
import util.PositionUtil;

import java.io.Serializable;

public class NavigationEngine implements Runnable, Serializable {
    private Logger    logger = LoggerFactory.getLogger(NavigationEngine.class);
    private SpaceShip spaceShip;

    public NavigationEngine(SpaceShip spaceShip) {
        this.spaceShip = spaceShip;
    }

    public void run() {
        Thread.currentThread().setName("navEngine :: " + spaceShip.getName());

        int             positionIndex     = spaceShip.getPositionIndex();
        PolarCoordinate polarCoordinate   = spaceShip.getOrbit().get(positionIndex);
        Position        cartesianPosition = spaceShip.getCartesianOrbit().get(positionIndex);

        logger.info(spaceShip.getName() + " moving to - " + cartesianPosition);
        logger.info(spaceShip.getName() + " currentPostion = " + spaceShip.getPositionIndex() + " radius = " + spaceShip.getPolarCoordinate().getRadius());

        spaceShip.setPolarCoordinate(polarCoordinate);
        spaceShip.setPosition(cartesianPosition);

        if (positionIndex == (spaceShip.getOrbit().size() - 1)) {
            logger.error("Resetting the spacecraft to original position");
            positionIndex = 0;
        } else {
            positionIndex++;
        }

        spaceShip.setPositionIndex(positionIndex);
    }
}
