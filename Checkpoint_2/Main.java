

public class Main {

    public static void main(String[] args) {
        System.out.println("---------------------------------------------");
        Subsystem s1 = new Subsystem("Drivetrain", new int[] {31, 42});
        System.out.println("Total number of motors: " + Motor.getMotorCount());
        Subsystem s2 = new Subsystem("Shooter", new int[] {57, 80, 96});
        System.out.println("Total number of motors: " + Motor.getMotorCount());
        
        // Initial
        System.out.println("---------------------------------------------");
        System.out.println("Avarage temperature of the drivetrain motors: " + s1.getAverageTemperature());
        Motor s1Motor = s1.getHottestMotor();
        System.out.println("The hottest drivetrain motor: " + s1Motor);

        s1.setAllSpeeds(0.7);
        
        // After speed set
        System.out.println("Avarage temperature of the drivetrain motors: " + s1.getAverageTemperature());
        s1Motor = s1.getHottestMotor();
        System.out.println("The hottest drivetrain motor: " + s1Motor);

        s1.setAllSpeeds(-0.8);
        
        // After speed set
        System.out.println("Avarage temperature of the drivetrain motors: " + s1.getAverageTemperature());
        s1Motor = s1.getHottestMotor();
        System.out.println("The hottest drivetrain motor: " + s1Motor);

        // Initial
        System.out.println("---------------------------------------------");
        System.out.println("Avarage temperature of the drivetrain motors: " + s2.getAverageTemperature());
        Motor s2Motor = s1.getHottestMotor();
        System.out.println("The hottest drivetrain motor: " + s2Motor);

        s1.setAllSpeeds(0.7);
        
        // After speed set
        System.out.println("Avarage temperature of the drivetrain motors: " + s2.getAverageTemperature());
        s1Motor = s2.getHottestMotor();
        System.out.println("The hottest drivetrain motor: " + s2Motor);
        System.out.println("---------------------------------------------");
        
    }
}