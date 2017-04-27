package edu.wpi.first.wpilibj.hal.frccom;


import java.util.ArrayList;
import java.util.Arrays;

public class DsToRobot
{
    public static enum ControlMode {TELEOP, TEST, AUTON};
    public static enum RequestType {UNCONNECTED, RESTART_CODE, REBOOT, NORMAL};
    public static enum Station {RED1, RED2, RED3, BLUE1, BLUE2, BLUE3};


    private int seqNum;
    private boolean eStopped;
    private boolean fmsAttached;
    private boolean enabled;
    private ControlMode controlMode;
    private RequestType requestType;
    private Station station;
    private Joystick[] joysticks;

    public DsToRobot(int seqNum, boolean eStopped, boolean fmsAttached, boolean enabled, ControlMode controlMode, RequestType requestType, Station station, Joystick[] joysticks)
    {
        this.seqNum = seqNum;
        this.eStopped = eStopped;
        this.fmsAttached = fmsAttached;
        this.enabled = enabled;
        this.controlMode = controlMode;
        this.requestType = requestType;
        this.station = station;
        this.joysticks = joysticks;
    }

    public static DsToRobot fromByteArray(byte[] buffer) {
        if (buffer[2] != 1) {
            throw new RuntimeException("Protocol version " + buffer[2] + " is not supported.");
        }
        int seqNum = (buffer[0] << 8) + (buffer[1] & 0xFF);
        boolean eStopped = (buffer[3] & 0x80) > 0;
        boolean fmsAttached = (buffer[3] & 0x08) > 0;
        boolean enabled = (buffer[3] & 0x04) > 0;

        ControlMode controlMode = null;
        switch (buffer[3] & 0x03) {
            case 0: controlMode = ControlMode.TELEOP; break;
            case 1: controlMode = ControlMode.TEST; break;
            case 2: controlMode = ControlMode.AUTON; break;
            default: throw new RuntimeException("Invalid control mode");
        }

        RequestType requestType = null;
        switch (buffer[4] & 0xFF) {
            case 0x00: requestType = RequestType.UNCONNECTED; break;
            case 0x04: requestType = RequestType.RESTART_CODE; break;
            case 0x08: requestType = RequestType.REBOOT; break;
            case 0x80: requestType = RequestType.NORMAL; break;
            default: throw new RuntimeException("Invalid request type");
        }

        Station station = null;
        switch (buffer[5]) {
            case 0: station = Station.RED1; break;
            case 1: station = Station.RED2; break;
            case 2: station = Station.RED3; break;
            case 3: station = Station.BLUE1; break;
            case 4: station = Station.BLUE2; break;
            case 5: station = Station.BLUE3; break;
            default: throw new RuntimeException("Invalid station");
        }

        int i = 6;
        ArrayList<Joystick> joysticks = new ArrayList<Joystick>();
        while (i < buffer.length) {
            if (buffer[i+1] != 12) {
                throw new RuntimeException("Invalid Joystick code " + buffer[i+1]);
            }
            int numAxes = buffer[i+2] & 0xFF;
            byte[] axesValues = new byte[numAxes];
            i += 3;
            for (int a = 0; a < numAxes; a++) {
                axesValues[a] = buffer[i];
                i++;
            }

            int numButtons = buffer[i];
            int buttonsCode = (buffer[i+2] << 8) + (buffer[i+1] & 0xFF);
            i += 3;
            boolean[] buttonValues = new boolean[numButtons];
            for (int b = 0; b < numButtons; b++) {
                buttonValues[b] = (buttonsCode & (1 << b)) > 0;
            }

            int numHats = buffer[i];
            i++;

            int[] hatValues = new int[numHats];
            for (int h = 0; h < numHats; h++) {
                hatValues[h] = ((buffer[i] << 8) + (buffer[i+1] & 0xFF));
                i += 2;
            }

            joysticks.add(new Joystick(axesValues, buttonValues, hatValues));
        }
        Joystick[] joystickArray = new Joystick[joysticks.size()];
        return new DsToRobot(seqNum, eStopped, fmsAttached, enabled, controlMode, requestType, station, (Joystick[])joysticks.toArray(joystickArray));
    }

    public int getSeqNum() { return seqNum; }
    public boolean getEStopped() { return eStopped; }
    public boolean getFmsAttached() { return fmsAttached; }
    public boolean getEnabled() { return enabled; }
    public ControlMode getControlMode() { return controlMode; }
    public RequestType getRequestType() { return requestType; }
    public Station getStation() { return station; }
    public Joystick[] getJoysticks() { return joysticks; }

    public String toString() {
        return "DsToRobot[seqNum=" + seqNum + ", eStopped=" + eStopped + ", fmsAttached=" + fmsAttached + ", enabled=" + enabled + ", controlMode=" + controlMode + ", requestType=" + requestType + ", station=" + station + ", joysticks=" + Arrays.toString(joysticks) + "]";
    }

    public static class Joystick {
        private byte[] axesValues;
        private boolean[] buttonValues;
        private int[] hatValues;

        public Joystick(byte[] axesValues, boolean[] buttonValues, int[] hatValues) {
            this.axesValues = axesValues;
            this.buttonValues = buttonValues;
            this.hatValues = hatValues;
        }

        public byte[] getAxesValues() {
            return this.axesValues;
        }

        public boolean[] getButtonValues() {
            return this.buttonValues;
        }

        public int[] getHatValues() {
            return this.hatValues;
        }

        public String toString() {
            return "Joystick[axesValues=" + Arrays.toString(this.axesValues) + ", buttonValues=" + Arrays.toString(this.buttonValues) + ", hatValues=" + Arrays.toString(this.hatValues) + "]";
        }
    }
}
