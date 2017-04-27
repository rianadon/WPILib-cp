import java.io.IOException;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.hal.frccom.ComServer;

public class Main {
    public static class RobotTemplate extends IterativeRobot {

        public void robotInit() {
            Joystick j = new Joystick(1);
            for (int i = 0; i < 1000; i++) {
                System.out.println(j.getX() + " " + j.getY() + " " + j.getZ());
                Thread.sleep(100);
            }
        }

        public void autonomousInit() {

        }

        public void autonomousPeriodic() {

        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        (new Thread(new ComServer())).start();

        (new RobotTemplate()).startCompetition();
    }
}
