/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.hal;

@SuppressWarnings("AbbreviationAsWordInName")
public class HALUtil extends JNIWrapper {

    public static final int NULL_PARAMETER = -1005;

    public static final int SAMPLE_RATE_TOO_HIGH = 1001;

    public static final int VOLTAGE_OUT_OF_RANGE = 1002;

    public static final int LOOP_TIMING_ERROR = 1004;

    public static final int INCOMPATIBLE_STATE = 1015;

    public static final int ANALOG_TRIGGER_PULSE_OUTPUT_ERROR = -1011;

    public static final int NO_AVAILABLE_RESOURCES = -104;

    public static final int PARAMETER_OUT_OF_RANGE = -1028;

    /** Generated method stub */
    public static short getFPGAVersion() {
        System.out.println("HALUtil.getFPGAVersion(" + ");");
        return 0;
    }

    /** Generated method stub */
    public static int getFPGARevision() {
        System.out.println("HALUtil.getFPGARevision(" + ");");
        return 0;
    }

    public static long getFPGATime() {
        return System.nanoTime() / 1000;
    }

    /** Generated method stub */
    public static int getHALRuntimeType() {
        System.out.println("HALUtil.getHALRuntimeType(" + ");");
        return 0;
    }

    /** Generated method stub */
    public static boolean getFPGAButton() {
        System.out.println("HALUtil.getFPGAButton(" + ");");
        return false;
    }

    /** Generated method stub */
    public static String getHALErrorMessage(int code) {
        System.out.println("HALUtil.getHALErrorMessage(" + "code=" + code + ");");
        return "";
    }

    /** Generated method stub */
    public static int getHALErrno() {
        System.out.println("HALUtil.getHALErrno(" + ");");
        return 0;
    }

    /** Generated method stub */
    public static String getHALstrerror(int errno) {
        System.out.println("HALUtil.getHALstrerror(" + "errno=" + errno + ");");
        return "";
    }

    public static String getHALstrerror() {
        return getHALstrerror(getHALErrno());
    }
}
