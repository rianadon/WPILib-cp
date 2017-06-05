package edu.wpi.first.wpilibj.hal;

/**
 * This interface sits a layer below WPILib's HAL and is responsible for
 * abstracting a specific platform's I/O (such as a Raspberry Pi) so that it is
 * consistent regardless of the platform.
 *
 * When running WPILib-cp on a new platform, you'll need to define a class that
 * implements this interface and register it with the
 * <code>HardwareManager</code> class.
 */
public interface HardwareInterface {

    // public int getNumAccumulators();
    // public int getNumAnalogTriggers();
    // public int getNumAnalogInputs();
    // public int getNumAnalogOutputs();
    // public int getNumCounters();
    // public int getNumDigitalHeaders();
    // public int getNumDigitalChannels();

    /**
     * Returns the maximum number of PWM outputs the hardware supports.
     */
    public int getNumPWMChannels();

    // public int getNumDigitalPWMOutputs();
    // public int getNumEncoders();
    // public int getNumInterrupts();
    // public int getNumRelayChannels();
    // public int getNumRelayHeaders();
    // public int getNumPCMModules();
    // public int getNumSolenoidChannels();
    // public int getNumPDPModules();
    // public int getNumPDPChannels();

    /**
     * Returns the physical port of the nth PWM channel.
     */
    public int nthPWM(int n);

    /**
     * Initialize a PWM output
     * @param port  The port of the PWM output
     */
    public void initPWM(int port);

    /**
     * Frees a PWM output
     * @param port  The port of the PWM output
     */
    public void freePWM(int port);

    /**
     * Sets the speed of a PWM output
     * @param port  The port of the PWM output
     * @param speed The speed the port should be set to, from -1.0 to 1.0
     */
    public void setPWMSpeed(int port, double speed);
}
