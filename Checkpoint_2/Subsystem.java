

public class Subsystem {
    Motor[] motorList;

    public Subsystem(String name, int[] idList) {
        motorList = new Motor[idList.length];
        for (int i = 0; i < motorList.length; i++) {
            motorList[i] = new Motor(name , idList[i]);
        }
    }

    public void setAllSpeeds(double speed) {
        for (int i = 0; i < motorList.length; i++) {
            motorList[i].setSpeed(speed);
        }
    }

    public double getAverageTemperature() {
        double avgTemp = 0.0;
        for (int i = 0; i < motorList.length; i++) {
            avgTemp = avgTemp + motorList[i].getTemperature();
        }
        avgTemp = avgTemp / motorList.length;
        return avgTemp;
    }

    public Motor getHottestMotor() {
        int highestTempMotorIndex = 0;
        for (int i = 0; i < motorList.length; i++) {
            double currentMotor = motorList[i].getTemperature();
            double highTempMotor = motorList[highestTempMotorIndex].getTemperature();
            if (currentMotor > highTempMotor) {
                highestTempMotorIndex = i;
            }
        }
        return motorList[highestTempMotorIndex];
    }
}