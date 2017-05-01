package edu.wpi.first.wpilibj.hal.frccom;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.atomic.AtomicBoolean;
import edu.wpi.first.wpilibj.hal.frccom.DsToRobot;
import edu.wpi.first.wpilibj.hal.frccom.RobotToDs;

/**
 * ComServer
 */
public class ComServer implements Runnable {

    private static final int RECV_PORT = 1110;

    private static final int RECV_PACKETSIZE = 100;

    private static final int SEND_PORT = 1150;

    private DatagramSocket socket;

    // Volatile because this will be set from other threads
    private volatile boolean stop;

    private volatile DsToRobot lastPacket;

    private final AtomicBoolean lock = new AtomicBoolean();

    public ComServer() throws SocketException {
        socket = new DatagramSocket(RECV_PORT);
    }

    public void run() {
        while (!stop) {
            DatagramPacket packet = new DatagramPacket(new byte[RECV_PACKETSIZE], RECV_PACKETSIZE);
            try {
                socket.receive(packet);
                byte[] data = new byte[packet.getLength()];
                System.arraycopy(packet.getData(), packet.getOffset(), data, 0, packet.getLength());
                DsToRobot sent = DsToRobot.fromByteArray(data);
                lastPacket = sent;
                synchronized (lock) {
                    lock.set(true);
                    lock.notifyAll();
                }
                double battery = 10 + Math.random() * 2;
                RobotToDs packetC = new RobotToDs(1, sent.getEStopped(), false, RobotToDs.CodeState.RUNNING, sent.getEnabled(), RobotToDs.ControlMode.TELEOP, true, battery, false);
                byte[] packetB = packetC.toByteArray();
                DatagramPacket sendPacket = new DatagramPacket(packetB, packetB.length, packet.getAddress(), SEND_PORT);
                socket.send(sendPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        stop = true;
    }

    public int getAllianceStation() {
        switch(lastPacket.getStation()) {
            case RED1:
                return 0;
            case RED2:
                return 1;
            case RED3:
                return 2;
            case BLUE1:
                return 3;
            case BLUE2:
                return 4;
            case BLUE3:
                return 5;
            default:
                return -1;
        }
    }

    public DsToRobot.Joystick[] getJoysticks() {
        return lastPacket == null ? null : lastPacket.getJoysticks();
    }

    public DsToRobot getLastPacket() {
        return lastPacket;
    }

    public AtomicBoolean getLock() {
        return lock;
    }
}
