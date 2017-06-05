/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.hal;

public class PortsJNI extends JNIWrapper {

    public static int getNumAccumulators() {
        return -1;
    }

    public static int getNumAnalogTriggers() {
        return -1;
    }

    public static int getNumAnalogInputs() {
        return -1;
    }

    public static int getNumAnalogOutputs() {
        return -1;
    }

    public static int getNumCounters() {
        return -1;
    }

    public static int getNumDigitalHeaders() {
        return -1;
    }

    public static int getNumPWMHeaders() {
        return -1;
    }

    public static int getNumDigitalChannels() {
        return -1;
    }

    public static int getNumPWMChannels() {
        return HardwareManager.getHardwareInterface().getNumPWMChannels();
    }

    public static int getNumDigitalPWMOutputs() {
        return -1;
    }

    public static int getNumEncoders() {
        return -1;
    }

    public static int getNumInterrupts() {
        return -1;
    }

    public static int getNumRelayChannels() {
        return -1;
    }

    public static int getNumRelayHeaders() {
        return -1;
    }

    public static int getNumPCMModules() {
        return -1;
    }

    public static int getNumSolenoidChannels() {
        return -1;
    }

    public static int getNumPDPModules() {
        return -1;
    }

    public static int getNumPDPChannels() {
        return -1;
    }
}
