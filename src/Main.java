import java.io.IOException;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.HLUsageReporting;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.internal.HardwareHLUsageReporting;
import edu.wpi.first.wpilibj.internal.HardwareTimer;

public class Main {
    public static class RobotTemplate extends IterativeRobot {

        private Joystick j;

        public void robotInit() {
            // initializeHardwareConfiguration();
            Timer.SetImplementation(new HardwareTimer());
            HLUsageReporting.SetImplementation(new HardwareHLUsageReporting());
            RobotState.SetImplementation(DriverStation.getInstance());

            j = new Joystick(0);
        }

        public void autonomousInit() {

        }

        public void autonomousPeriodic() {

        }

        public void teleopPeriodic() {
            System.out.println(j.getX() + " " + j.getY() + " " + j.getZ());
        }

        public void robotPeriodic() {
            System.out.println(isEnabled() + " " + isAutonomous() + " " + isTest());
        }
    }

    public static void main(String[] args) throws IOException {
        (new RobotTemplate()).startCompetition();
    }
}
