package space.artifacts;

import navigation.NavigationEngine;
import util.PositionUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SpaceShip implements IsSpaceShip, Runnable, Serializable {
    public static final Double ARCSPEED_FACTOR = 5.0d;

    private String  name;
    private String  transponderId;
    private int     id;
    private int     crewCapacity;
    private long    range;
    private int     numGuns;
    private boolean engagedInBattle;
    private int     score;
    private long    timeCreated;
    private int     positionIndex;
    private double  arcSpeed;
    private boolean firstPass;

    private Position                 position;
    private PolarCoordinate          polarCoordinate;
    private List<PolarCoordinate>    orbit;
    private List<Position>           cartesianOrbit;
    private ScheduledExecutorService scheduler;
    private NavigationEngine         navigationEngine;


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
        this.firstPass = true;
        computeNavPath();

        this.navigationEngine = new NavigationEngine(this);
        this.scheduler        = Executors.newSingleThreadScheduledExecutor();
    }

    public void initiateNavigation() {
        scheduler.scheduleAtFixedRate(navigationEngine, 0l, 20l, TimeUnit.MILLISECONDS);
    }

    public NavigationEngine getNavigationEngine() {
        return navigationEngine;
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

    /**
     * This needs to run every 20 ms. or (1000 / ARCSPEED_FACTOR)
     */
    @Override
    public void run() {


    }

    public List<PolarCoordinate> getOrbit() {
        return orbit;
    }

    public List<Position> getCartesianOrbit() {
        return cartesianOrbit;
    }

    public synchronized int getPositionIndex() {
        return positionIndex;
    }

    public synchronized void setPositionIndex(int positionIndex) {
        this.positionIndex = positionIndex;
    }

    private void computeNavPath() {
        orbit          = new ArrayList<>();
        cartesianOrbit = new ArrayList<>();

        double angle          = 0.0d;
        double angleIncrement = arcSpeed / ARCSPEED_FACTOR;

        while (angle <= 360.0d) {
            PolarCoordinate polarCoordinate = new PolarCoordinate();
            polarCoordinate.setThetaDegrees(angle);
            polarCoordinate.setTheta(Math.toRadians(angle));
            polarCoordinate.setRadius(this.polarCoordinate.getRadius());
            orbit.add(polarCoordinate);

            angle = angle + angleIncrement;
        }

        Collections.sort(orbit);

        for (PolarCoordinate p : orbit) {
            cartesianOrbit.add(PositionUtil.convertToCartesian(p));
        }

        if (firstPass) {
            for (int i = 0; i < orbit.size(); i++) {
                PolarCoordinate p = orbit.get(i);
                if (p.getTheta() >= this.polarCoordinate.getTheta()) {
                    positionIndex = i;
                    firstPass     = false;
                    break;
                }
            }
        }
    }
}
