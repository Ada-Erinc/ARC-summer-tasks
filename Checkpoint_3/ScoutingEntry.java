


interface Exportable {
    String toCsvRow();
    default String toCsvRowWithHeader(String header) {
        return header + "\n" + toCsvRow();
    }
}

public enum MatchPhase {
    AUTONOMOUS, TELEOP, ENDGAME
}

abstract class ScoutingEntry {
    protected int teamNumber;
    protected String scoutName;
    protected String timeStamp;

    public ScoutingEntry(int teamNumber, String scoutName, String timeStamp) {
        this.teamNumber = teamNumber;
        this.scoutName = scoutName;
        this.timeStamp = timeStamp;
    }

    public String getSummaryHeader() {
        String Summary = "Team " + teamNumber + " - scouted by " + scoutName + " @ " + timeStamp;
        return Summary;
    }

    abstract String getDetails();
}

class PitScoutingEntry extends ScoutingEntry implements Exportable {
    String drivetrainType;
    double weightLbs;
    boolean hasAutonomous;

    public PitScoutingEntry(int teamNumber, String scoutName, String timeStamp, String drivetrainType, double weightLbs, boolean hasAutonomous) {
        super(teamNumber, scoutName, timeStamp);
        this.drivetrainType = drivetrainType;
        this.weightLbs = weightLbs;
        this.hasAutonomous = hasAutonomous;
    }

    @Override
    String getDetails() {
        String details = "Drive train type: " + drivetrainType + ", weight: " + weightLbs + ", Autonomous: " + hasAutonomous;
        return details;
    }

    @Override
    public String toCsvRow() {
        return teamNumber + "," + scoutName + "," + timeStamp + "," + drivetrainType + "," + weightLbs + "," + hasAutonomous;
    }
}

class MatchScoutingEntry extends ScoutingEntry implements Exportable {
    int matchNumber;
    MatchPhase strongestPhase;
    int pointsContributed;

    public MatchScoutingEntry(int teamNumber, String scoutName, String timeStamp, int matchNumber, MatchPhase strongestPhase, int pointsContributed) throws Exeption {
        super(teamNumber, scoutName, timeStamp);
        this.matchNumber = matchNumber;
        this.strongestPhase = strongestPhase;
        if (pointsContributed < 0) {
            throw new InvalidScoutingDataException("Points contributed should be positive.");
        }
        this.pointsContributed = pointsContributed;
    }

    @Override
    String getDetails() {
        String details = "Drive train type: " + drivetrainType + ", weight: " + weightLbs + ", Autonomous: " + hasAutonomous;
        return details;
    }

    @Override
    public String toCsvRow() {
        return teamNumber + "," + scoutName + "," + timeStamp + "," + matchNumber + "," + strongestPhase + "," + pointsContributed;
    }
}