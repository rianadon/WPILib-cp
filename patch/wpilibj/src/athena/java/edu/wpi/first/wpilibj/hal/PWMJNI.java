/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.hal;

import edu.wpi.first.wpilibj.PWMConfigDataResult;
import edu.wpi.first.wpilibj.SensorBase;

@SuppressWarnings("AbbreviationAsWordInName")
public class PWMJNI extends DIOJNI {

    public static int initializePWMPort(int halPortHandle) {
        int channel = HandlesInternal.getPortHandleChannel(halPortHandle);
        if (channel == HandlesInternal.INVALID_HANDLE_INDEX || channel > SensorBase.kPwmChannels) {
            return 0;
        }

        return HardwareManager.getHardwareInterface().nthPWM(channel);
    }

    public static boolean checkPWMChannel(int channel) {
        return channel < SensorBase.kPwmChannels && channel >= 0;
    }

    /** Generated method stub */
    public static void freePWMPort(int pwmPortHandle) {
        System.out.println("PWMJNI.freePWMPort(" + "pwmPortHandle=" + pwmPortHandle + ");");
    }

    /** Generated method stub */
    public static void setPWMConfigRaw(int pwmPortHandle, int maxPwm, int deadbandMaxPwm, int centerPwm,
            int deadbandMinPwm, int minPwm) {
        System.out.println("PWMJNI.setPWMConfigRaw(" + "pwmPortHandle=" + pwmPortHandle + ", " + "maxPwm=" + maxPwm
                + ", " + "deadbandMaxPwm=" + deadbandMaxPwm + ", " + "centerPwm=" + centerPwm + ", " + "deadbandMinPwm="
                + deadbandMinPwm + ", " + "minPwm=" + minPwm + ");");
    }

    /** Generated method stub */
    public static void setPWMConfig(int pwmPortHandle, double maxPwm, double deadbandMaxPwm, double centerPwm,
            double deadbandMinPwm, double minPwm) {
        System.out.println("PWMJNI.setPWMConfig(" + "pwmPortHandle=" + pwmPortHandle + ", " + "maxPwm=" + maxPwm + ", "
                + "deadbandMaxPwm=" + deadbandMaxPwm + ", " + "centerPwm=" + centerPwm + ", " + "deadbandMinPwm="
                + deadbandMinPwm + ", " + "minPwm=" + minPwm + ");");
    }

    /** Generated method stub */
    public static PWMConfigDataResult getPWMConfigRaw(int pwmPortHandle) {
        System.out.println("PWMJNI.getPWMConfigRaw(" + "pwmPortHandle=" + pwmPortHandle + ");");
        return null;
    }

    /** Generated method stub */
    public static void setPWMEliminateDeadband(int pwmPortHandle, boolean eliminateDeadband) {
        System.out.println("PWMJNI.setPWMEliminateDeadband(" + "pwmPortHandle=" + pwmPortHandle + ", "
                + "eliminateDeadband=" + eliminateDeadband + ");");
    }

    /** Generated method stub */
    public static boolean getPWMEliminateDeadband(int pwmPortHandle) {
        System.out.println("PWMJNI.getPWMEliminateDeadband(" + "pwmPortHandle=" + pwmPortHandle + ");");
        return false;
    }

    /** Generated method stub */
    public static void setPWMRaw(int pwmPortHandle, short value) {
        System.out.println("PWMJNI.setPWMRaw(" + "pwmPortHandle=" + pwmPortHandle + ", " + "value=" + value + ");");
    }

    public static void setPWMSpeed(int pwmPortHandle, double speed) {
        HardwareManager.getHardwareInterface().setPWMSpeed(pwmPortHandle, speed);
    }

    /** Generated method stub */
    public static void setPWMPosition(int pwmPortHandle, double position) {
        System.out.println(
                "PWMJNI.setPWMPosition(" + "pwmPortHandle=" + pwmPortHandle + ", " + "position=" + position + ");");
    }

    /** Generated method stub */
    public static short getPWMRaw(int pwmPortHandle) {
        System.out.println("PWMJNI.getPWMRaw(" + "pwmPortHandle=" + pwmPortHandle + ");");
        return 0;
    }

    /** Generated method stub */
    public static double getPWMSpeed(int pwmPortHandle) {
        System.out.println("PWMJNI.getPWMSpeed(" + "pwmPortHandle=" + pwmPortHandle + ");");
        return 0.0;
    }

    /** Generated method stub */
    public static double getPWMPosition(int pwmPortHandle) {
        System.out.println("PWMJNI.getPWMPosition(" + "pwmPortHandle=" + pwmPortHandle + ");");
        return 0.0;
    }

    /** Generated method stub */
    public static void setPWMDisabled(int pwmPortHandle) {
        System.out.println("PWMJNI.setPWMDisabled(" + "pwmPortHandle=" + pwmPortHandle + ");");
    }

    /** Generated method stub */
    public static void latchPWMZero(int pwmPortHandle) {
        System.out.println("PWMJNI.latchPWMZero(" + "pwmPortHandle=" + pwmPortHandle + ");");
    }

    /** Generated method stub */
    public static void setPWMPeriodScale(int pwmPortHandle, int squelchMask) {
        System.out.println("PWMJNI.setPWMPeriodScale(" + "pwmPortHandle=" + pwmPortHandle + ", " + "squelchMask="
                + squelchMask + ");");
    }
}
