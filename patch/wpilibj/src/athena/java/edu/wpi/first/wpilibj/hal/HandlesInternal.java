package edu.wpi.first.wpilibj.hal;

/**
 * This is a Java implementation of the HandlesInternal C++ file and header.
 */
public class HandlesInternal {
    public static int INVALID_HANDLE_INDEX = -1;

    private static enum HandleEnum {
        Undefined, DIO, Port, Notifier, Interrupt, AnalogOutput, AnalogInput, AnalogTrigger, Relay,
        PWM, DigitalPWM, Counter, FPGAEncoder, Encoder, Compressor, Solenoid, AnalogGyro, Vendor,
    };

    public static int createPortHandle(byte channel, byte module) {
        // set last 8 bits, then shift to first 8 bits
        int handle = HandleEnum.Port.ordinal();
        handle = handle << 24;
        // shift module and add to 3rd set of 8 bits
        int temp = module;
        temp = (temp << 8) & 0xff00;
        handle += temp;
        // add channel to last 8 bits
        handle += channel;
        return handle;
    }

    public static int getHandleType(int handle) {
        // mask first 8 bits and cast to enum
        return ((handle >> 24) & 0xff);
    }

    public static boolean isHandleType(int handle, int handleType) {
        return handleType == getHandleType(handle);
    }

    /* specialized functions for
     * Port handle*
     * Port Handle
     * Data Layout*Bits 0-7:
     * Channel Number*Bits 8-15:
     * Module Number*Bits 16-23:Unused*Bits 24-30:
     * Handle Type*Bit 31:1 if
     * handle error,0 if
     * no error
     */

    public static int getPortHandleChannel(int handle) {
        if (!isHandleType(handle, HandleEnum.Port.ordinal()))
            return INVALID_HANDLE_INDEX;
        return (handle & 0xff);
    }
}
