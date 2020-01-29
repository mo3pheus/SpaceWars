package space.artifacts;

public class PolarCoordinate implements Comparable<PolarCoordinate> {
    double radius;
    double theta;
    double thetaDegrees;

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public double getThetaDegrees() {
        return thetaDegrees;
    }

    public void setThetaDegrees(double thetaDegrees) {
        this.thetaDegrees = thetaDegrees;
    }

    @Override
    public int compareTo(PolarCoordinate polarCoordinate) {
        if (theta == polarCoordinate.getTheta()) {
            return 0;
        } else {
            return (theta < polarCoordinate.getTheta()) ? -1 : 1;
        }
    }
}
