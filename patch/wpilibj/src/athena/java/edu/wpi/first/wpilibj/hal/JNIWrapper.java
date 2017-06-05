/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.hal;

import java.io.File;

/**
 * Base class for all JNI wrappers.
 */
public class JNIWrapper {

    static boolean libraryLoaded = false;
    static File jniLibrary = null;

    private static final int HAL_kInvalidHandle = 0;
    private static final int HAL_HandleEnum$$Port = 0;

    static {}

    public static int getPortWithModule(byte module, byte channel) {
        // Dont allow a number that wouldn't fit in a uint8_t
        if (channel < 0 || channel >= 255)
            return HAL_kInvalidHandle;
        if (module < 0 || module >= 255)
            return HAL_kInvalidHandle;
        return HandlesInternal.createPortHandle(channel, module);

    }

    public static int getPort(byte channel) {
        // Dont allow a number that wouldn't fit in a uint8_t
        if (channel < 0 || channel >= 255)
            return HAL_kInvalidHandle;
        return HandlesInternal.createPortHandle(channel, (byte) 1);
    }
}
