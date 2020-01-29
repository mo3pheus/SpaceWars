package space.artifacts;

import java.util.ArrayList;
import java.util.List;

public class SpaceShip implements IsSpaceShip, Runnable {
    private String          name;
    private String          transponderId;
    private int             id;
    private int             crewCapacity;
    private long            range;
    private int             numGuns;
    private boolean         engagedInBattle;
    private int             score;
    private long            timeCreated;
    private Position        position;
    private PolarCoordinate polarCoordinate;
    private double          arcSpeed;

    public PolarCoordinate getPolarCoordinate() {
        return polarCoordinate;
    }

    public void setPolarCoordinate(PolarCoordinate polarCoordinate) {
        this.polarCoordinate = polarCoordinate;
    }

    public double getArcSpeed() {
        return arcSpeed;
    }

    public void setArcSpeed(double arcSpeed) {
        this.arcSpeed = arcSpeed;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position        = position;
        this.polarCoordinate = new PolarCoordinate();
        this.polarCoordinate.setRadius(Math.sqrt(Math.pow(position.getX(), 2.0d) + Math.pow(position.getY(), 2.0d)));
        this.polarCoordinate.setTheta(Math.acos(position.getX() / this.polarCoordinate.getRadius()));
        this.polarCoordinate.setThetaDegrees(Math.toDegrees(this.polarCoordinate.getTheta()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTransponderId() {
        return transponderId;
    }

    public void setTransponderId(String transponderId) {
        this.transponderId = transponderId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCrewCapacity() {
        return crewCapacity;
    }

    public void setCrewCapacity(int crewCapacity) {
        this.crewCapacity = crewCapacity;
    }

    public long getRange() {
        return range;
    }

    public void setRange(long range) {
        this.range = range;
    }

    public int getNumGuns() {
        return numGuns;
    }

    public void setNumGuns(int numGuns) {
        this.numGuns = numGuns;
    }

    public boolean isEngagedInBattle() {
        return engagedInBattle;
    }

    public void setEngagedInBattle(boolean engagedInBattle) {
        this.engagedInBattle = engagedInBattle;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(long timeCreated) {
        this.timeCreated = timeCreated;
    }

    @Override
    public void run() {


    }

    private List<PolarCoordinate> getNavPath() {
        List<PolarCoordinate> polarCoordinates = new ArrayList<>();
        double angle = 0;

        while (Math.round(angle) <= 360.0d) {

        }

        return polarCoordinates;
    }
}
