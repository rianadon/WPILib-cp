import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

public class Main {
    public static class RobotTemplate extends IterativeRobot {

        private Joystick j;

        public void robotInit() {
            initializeHardwareConfiguration();

            j = new Joystick(0);
        }

        public void autonomousInit() {

        }

        public void autonomousPeriodic() {

        }

        public void teleopPeriodic() {
            System.out.println(j.getX() + " " + j.getY() + " " + j.getZ());
            System.out.println(IntStream.rangeClosed(1, j.getButtonCount()).mapToObj(j::getRawButton).map(String::valueOf).collect(Collectors.joining(" ")));
            System.out.println(IntStream.range(0, j.getPOVCount()).mapToObj(j::getPOV).map(String::valueOf).collect(Collectors.joining(" ")));
        }

        public void robotPeriodic() {
            System.out.println(isEnabled() + " " + isAutonomous() + " " + isTest());
        }
    }

    public static void main(String[] args) throws IOException {
        (new RobotTemplate()).startCompetition();
    }
}
