package space.artifacts;

public class SpaceShip {
    private String   name;
    private String   transponderId;
    private int      id;
    private int      crewCapacity;
    private long     range;
    private int      numGuns;
    private boolean  engagedInBattle;
    private int      score;
    private long     timeAliveMs;
    private Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
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

    public long getTimeAliveMs() {
        return timeAliveMs;
    }

    public void setTimeAliveMs(long timeAliveMs) {
        this.timeAliveMs = timeAliveMs;
    }
}
