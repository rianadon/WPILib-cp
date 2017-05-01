/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.hal;

import java.net.SocketException;
import java.nio.ByteBuffer;
import edu.wpi.first.wpilibj.hal.frccom.ComServer;
import edu.wpi.first.wpilibj.hal.frccom.DsToRobot;

/**
 * JNI Wrapper for HAL<br>.
 */
@SuppressWarnings({ "AbbreviationAsWordInName", "MethodName" })
public class HAL extends JNIWrapper {

    private static ComServer comServer;

    static {
        try {
            comServer = new ComServer();
            (new Thread(comServer)).start();
        } catch (SocketException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void waitForDSData() {
        try {
            synchronized (comServer.getLock()) {
                while (!comServer.getLock().get())
                    comServer.getLock().wait();
                comServer.getLock().set(false);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /** Generated method stub */
    public static int initialize(int mode) {
        return 0;
    }

    /** Generated method stub */
    public static void observeUserProgramStarting() {
    }

    /** Generated method stub */
    public static void observeUserProgramDisabled() {
    }

    /** Generated method stub */
    public static void observeUserProgramAutonomous() {
    }

    /** Generated method stub */
    public static void observeUserProgramTeleop() {
    }

    /** Generated method stub */
    public static void observeUserProgramTest() {
    }

    public static void report(int resource, int instanceNumber) {
        report(resource, instanceNumber, 0, "");
    }

    public static void report(int resource, int instanceNumber, int context) {
        report(resource, instanceNumber, context, "");
    }

    /** Generated method stub */
    public static int report(int resource, int instanceNumber, int context, String feature) {
        return 0;
    }

    // /** Generated method stub */
    // private static int nativeGetControlWord() {
    //     return 0;
    // }

    @SuppressWarnings("JavadocMethod")
    public static void getControlWord(ControlWord controlWord) {
        // int word = nativeGetControlWord();
        // controlWord.update((word & 1) != 0, ((word >> 1) & 1) != 0, ((word >> 2) & 1) != 0, ((word >> 3) & 1) != 0, ((word >> 4) & 1) != 0, ((word >> 5) & 1) != 0);
        DsToRobot p = comServer.getLastPacket();
        controlWord.update(p.getEnabled(),
                           p.getControlMode() == DsToRobot.ControlMode.AUTON,
                           p.getControlMode() == DsToRobot.ControlMode.TEST,
                           p.getEStopped(),
                           p.getFmsAttached(),
                           true);
    }

    private static int nativeGetAllianceStation() {
        return comServer.getAllianceStation();
    }

    @SuppressWarnings("JavadocMethod")
    public static AllianceStationID getAllianceStation() {
        switch(nativeGetAllianceStation()) {
            case 0:
                return AllianceStationID.Red1;
            case 1:
                return AllianceStationID.Red2;
            case 2:
                return AllianceStationID.Red3;
            case 3:
                return AllianceStationID.Blue1;
            case 4:
                return AllianceStationID.Blue2;
            case 5:
                return AllianceStationID.Blue3;
            default:
                return null;
        }
    }

    public static int kMaxJoystickAxes = 12;

    public static int kMaxJoystickPOVs = 12;

    public static short getJoystickAxes(byte joystickNum, float[] axesArray) {
        DsToRobot.Joystick[] joysticks = comServer.getJoysticks();
        if (joysticks == null)
            return 0;
        if (joystickNum >= joysticks.length)
            return 0;
        float[] f = joysticks[joystickNum].getAxesValues();
        for (int i = 0; i < f.length; i++) {
            axesArray[i] = f[i];
        }
        return (short) f.length;
    }

    /** Generated method stub */
    public static short getJoystickPOVs(byte joystickNum, short[] povsArray) {
        return 0;
    }

    /** Generated method stub */
    public static int getJoystickButtons(byte joystickNum, ByteBuffer count) {
        return 0;
    }

    /** Generated method stub */
    public static int setJoystickOutputs(byte joystickNum, int outputs, short leftRumble, short rightRumble) {
        return 0;
    }

    /** Generated method stub */
    public static int getJoystickIsXbox(byte joystickNum) {
        return 0;
    }

    /** Generated method stub */
    public static int getJoystickType(byte joystickNum) {
        return 0;
    }

    /** Generated method stub */
    public static String getJoystickName(byte joystickNum) {
        return "";
    }

    /** Generated method stub */
    public static int getJoystickAxisType(byte joystickNum, byte axis) {
        return 0;
    }

    /** Generated method stub */
    public static double getMatchTime() {
        return 0.0;
    }

    /** Generated method stub */
    public static boolean getSystemActive() {
        return false;
    }

    /** Generated method stub */
    public static boolean getBrownedOut() {
        return false;
    }

    /** Generated method stub */
    public static int setErrorData(String error) {
        return 0;
    }

    /** Generated method stub */
    public static int sendError(boolean isError, int errorCode, boolean isLVCode, String details, String location, String callStack, boolean printMsg) {
        return 0;
    }
}
