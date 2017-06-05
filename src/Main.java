import java.util.stream.Collectors;
import java.util.stream.IntStream;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.hal.HardwareManager;

public class Main extends IterativeRobot {

    static {
        HardwareManager.setHardwareInterface(new TestInterface());
    }

    private Joystick j;
    private RobotDrive myRobot;

    public void robotInit() {
        myRobot = new RobotDrive(0, 1);
        j = new Joystick(0);
    }

    public void autonomousInit() {

    }

    public void autonomousPeriodic() {

    }

    public void teleopPeriodic() {
        myRobot.arcadeDrive(j);
        // System.out.println(j.getX() + " " + j.getY() + " " + j.getZ());
        // System.out.println(IntStream.rangeClosed(1, j.getButtonCount()).mapToObj(j::getRawButton).map(String::valueOf).collect(Collectors.joining(" ")));
        // System.out.println(IntStream.range(0, j.getPOVCount()).mapToObj(j::getPOV).map(String::valueOf).collect(Collectors.joining(" ")));
    }

    public void robotPeriodic() {
        // System.out.println(isEnabled() + " " + isAutonomous() + " " + isTest());
    }
}
