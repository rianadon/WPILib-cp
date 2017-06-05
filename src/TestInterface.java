import edu.wpi.first.wpilibj.hal.HardwareInterface;

public class TestInterface implements HardwareInterface {

    public int getNumPWMChannels() { return 2; }

    public int nthPWM(int n) { return n; }

    public void initPWM(int port) {
    }

    public void freePWM(int port) {
    }

    public void setPWMSpeed(int port, double speed) {
        System.out.println(port+"="+speed);
    }
}
