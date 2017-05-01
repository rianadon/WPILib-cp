package edu.wpi.first.wpilibj.hal.frccom;

public class RobotToDs {

    public static enum CodeState {

        RUNNING, INITIALIZING
    }

    ;

    public static enum ControlMode {

        TELEOP, TEST, AUTON
    }

    ;

    private int seqNum;

    private boolean eStopped;

    private boolean brownedOut;

    private CodeState codeState;

    private boolean enabled;

    private ControlMode controlMode;

    private boolean hasRobotCode;

    private double batteryVoltage;

    private boolean requestDate;

    public RobotToDs(int seqNum, boolean eStopped, boolean brownedOut, CodeState codeState, boolean enabled, ControlMode controlMode, boolean hasRobotCode, double batteryVoltage, boolean requestDate) {
        this.seqNum = seqNum;
        this.eStopped = eStopped;
        this.brownedOut = brownedOut;
        this.codeState = codeState;
        this.enabled = enabled;
        this.controlMode = controlMode;
        this.hasRobotCode = hasRobotCode;
        this.batteryVoltage = batteryVoltage;
        this.requestDate = requestDate;
    }

    public byte[] toByteArray() {
        byte[] ret = new byte[8];
        // Sequence number
        ret[0] = (byte) (seqNum >> 8);
        ret[1] = (byte) (seqNum & 0xFF);
        // Protocol version
        ret[2] = 0x01;
        // Control code
        if (eStopped)
            ret[3] |= 0x80;
        if (brownedOut)
            ret[3] |= 0x10;
        if (codeState == CodeState.INITIALIZING)
            ret[3] |= 0x08;
        if (enabled)
            ret[3] |= 0x04;
        if (controlMode == ControlMode.AUTON)
            ret[3] |= 0x02;
        if (controlMode == ControlMode.TEST)
            ret[3] |= 0x01;
        // Extra control data
        if (hasRobotCode)
            ret[4] |= 0x20;
        // Battery data
        ret[5] = (byte) batteryVoltage;
        ret[6] = (byte) Math.round((batteryVoltage - ret[5]) * 0xFF);
        ret[7] = (byte) (requestDate ? 1 : 0);
        return ret;
    }
}
