import java.util.Objects;

public class Motor {
    private String name;
    private final int canId;
    private double currentSpeed = 0.0;
    private double currentTemperature = 20.0;
    private static int motorCount = 0;

    public Motor(String name, int canId) {
        this.name = name;
        this.canId = canId;

        motorCount += 1;
    }

    @Override
    public String toString() {
        String nameAndId = getName() + " " + getCanId();
        return nameAndId;
    }

    @Override
    public boolean equals(Object m) {
        return ((Motor)m).getCanId() == getCanId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(canId);
    }

    public void setSpeed(double speed) {
        if (speed < -1.0 || speed > 1.0) {
            throw new IllegalArgumentException("Enter a valid speed between -1.0 and 1.0");
        }
        // double speedDiff = Math.abs(speed - currentSpeed);
        double speedDiff = Math.abs(Math.abs(speed) - Math.abs(currentSpeed));
        currentTemperature = currentTemperature + ((speedDiff / 0.1) * 2.0);
        currentSpeed = speed;
    }

    public String getName() {
        return name;
    }

    public int getCanId() {
        return canId;
    }

    public double getSpeed() {
        return currentSpeed;
    }

    public double getTemperature() {
        return currentTemperature;
    }

    public static int getMotorCount() {
        return motorCount;
    }
}