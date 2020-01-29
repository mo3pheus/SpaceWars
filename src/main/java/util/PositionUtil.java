package util;

import space.artifacts.PolarCoordinate;
import space.artifacts.Position;

public class PositionUtil {
    public static Position convertToCartesian(PolarCoordinate polarCoordinate) {
        return new Position((int) (polarCoordinate.getRadius() * Math.cos(polarCoordinate.getTheta())),
                (int) (polarCoordinate.getRadius() * Math.sin(polarCoordinate.getTheta())));
    }

    public static PolarCoordinate convertToPolar(Position position) {
        PolarCoordinate polarCoordinate = new PolarCoordinate();
        polarCoordinate.setRadius(Math.sqrt(position.getX() * position.getX() + position.getY() * position.getY()));
        polarCoordinate.setTheta(Math.acos(position.getX() / polarCoordinate.getRadius()));
        polarCoordinate.setThetaDegrees(Math.toDegrees(polarCoordinate.getTheta()));
        return polarCoordinate;
    }
}
