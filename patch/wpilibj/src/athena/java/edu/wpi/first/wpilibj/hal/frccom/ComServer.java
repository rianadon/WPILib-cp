package edu.wpi.first.wpilibj.hal.frccom;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
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
    private static final int NETCONSOLE_PORT = 6666;

    private DatagramSocket socket;
    private Map<InetAddress, Long> clientMap;

    // Volatile because this will be set from other threads
    private volatile boolean stop;

    private volatile DsToRobot lastPacket;

    private final AtomicBoolean lock = new AtomicBoolean();

    public ComServer() throws SocketException {
        socket = new DatagramSocket(RECV_PORT);
        clientMap = new HashMap<InetAddress, Long>();
    }

    public void run() {
        startRedirectingStdout();
        while (!stop) {
            DatagramPacket packet = new DatagramPacket(new byte[RECV_PACKETSIZE], RECV_PACKETSIZE);
            try {
                socket.receive(packet);
                clientMap.put(packet.getAddress(), System.currentTimeMillis());
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

    private void broadcast(byte[] buffer, int port) {
        long t = System.currentTimeMillis();
        for (InetAddress a : clientMap.keySet()) {
            long timeDiff = t - clientMap.get(a);
            // Only send to clients that have sent something in the last 100ms
            if (timeDiff < 100) {
                DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, a, port);
                try {
                    socket.send(sendPacket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                clientMap.remove(a);
            }
        }
    }

    private PrintStream getStdStream(PrintStream oldStream) {
        return new PrintStream(new OutputStream() {
            private ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            @Override
            public void write(int b) {
                byte byt = (byte) b;
                if (byt == '\n') {
                    broadcast(buffer.toByteArray(), NETCONSOLE_PORT);
                    buffer.reset();
                } else if (byt != '\r') {
                    buffer.write(b);
                }
                oldStream.write(b);
            }

            @Override
            public void flush() throws IOException {
                super.flush();
                oldStream.flush();
            }
        });
    }

    public void startRedirectingStdout() {
        System.setOut(getStdStream(System.out));
        System.setErr(getStdStream(System.err));
    }
}
