/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2017. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.jar.Manifest;

import edu.wpi.first.wpilibj.hal.FRCNetComm.tInstances;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tResourceType;
import edu.wpi.first.wpilibj.hal.HAL;
import edu.wpi.first.wpilibj.internal.HardwareHLUsageReporting;
import edu.wpi.first.wpilibj.internal.HardwareTimer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.util.WPILibVersion;

/**
 * Implement a Robot Program framework. The RobotBase class is intended to be subclassed by a user
 * creating a robot program. Overridden autonomous() and operatorControl() methods are called at the
 * appropriate time as the match proceeds. In the current implementation, the Autonomous code will
 * run to completion before the OperatorControl code could start. In the future the Autonomous code
 * might be spawned as a task, then killed at the end of the Autonomous period.
 */
public abstract class RobotBase {
  /**
   * Common initialization for all robot programs.
   */
  public static void initializeHardwareConfiguration() {
    int rv = HAL.initialize(0);
    assert rv == 1;

    // Set some implementations so that the static methods work properly
    Timer.SetImplementation(new HardwareTimer());
    HLUsageReporting.SetImplementation(new HardwareHLUsageReporting());
    RobotState.SetImplementation(DriverStation.getInstance());

    // Load opencv
    // try {
    //   System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    // } catch (UnsatisfiedLinkError ex) {
    //   System.out.println("OpenCV Native Libraries could not be loaded.");
    //   System.out.println("Please try redeploying, or reimage your roboRIO and try again.");
    //   ex.printStackTrace();
    // }
  }
}
