package edu.wpi.first.wpilibj.hal;

public class DefaultHardwareInterface implements HardwareInterface {

    public int getNumPWMChannels() { return 2; }

    public int nthPWM(int n) { return n; }

    public void initPWM(int port) {}
    public void freePWM(int port) {}
    public void setPWMSpeed(int port, double speed) {}
}
