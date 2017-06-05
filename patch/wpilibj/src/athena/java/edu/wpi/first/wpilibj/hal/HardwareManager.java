package edu.wpi.first.wpilibj.hal;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * Class responsible for setting the {@link HardwareInterface} that
 * WPILib-cp should use. This allows it to conform to any platform that you want
 * to use it on.
 *
 * As the code can run on only one hardware platform, all methods are static.
 */
public class HardwareManager {

    private static HardwareInterface hardInterface = null;
    private static HardwareInterface defaultInterface = new DefaultHardwareInterface();
    private static boolean haveWarned = false;

    /**
     * Sets the {@link HardwareInterface} that WPILib-cp will use for hardware-specific
     * tasks.
     *
     * @param interf The interface to use
     */
    public static void setHardwareInterface(HardwareInterface interf) {
        hardInterface = interf;
    }

    /**
     * Returns the {@link HardwareInterface} that WPILib-cp is set to use.
     * If none is specified throws an error.
     *
     * @return The {@link HardwareInterface}
     * @throws
     */
    public static HardwareInterface getHardwareInterface() {
        if (hardInterface == null) {
            if (!haveWarned) {
                DriverStation.reportWarning("You have not specified a hardware interface for " +
                                            "WPILib-cp to use! Please specify one using the " +
                                            "HardwareManager.setHardwareInterface method.", false);
                haveWarned = true;
            }
            return defaultInterface;
        }
        return hardInterface;
    }

}
