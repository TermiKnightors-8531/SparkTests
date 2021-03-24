// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Arm.ArmController;
import frc.robot.Arm.Limit;

public final class Main {
  private Main() {}

  //creates a publically accesable Power Distribution Panel for monitoring current draw on motors
  //also can read temperature and battery voltage
  public PowerDistributionPanel pdp = new PowerDistributionPanel(0);

  public static XboxController xb;

  public static Joystick j;
  public static void main(String... args) {
    //specifices a drive train with motor controller IDs 1 and 2 for the left side motors and 3 and 4 for the right side
    DriveTrain dt = new DriveTrain(1,2,3,4);

    //Creates an arm with a CAN ID of the 10 for the arm motor and a CAN ID of 11 for the intake motor
    //upwards limit switch created on Digital Input/Output port 0 and downwards limit switch created on Digital Input/Output port 1 
    Arm a = new Arm(10, 11, ArmController.xbox, Limit.manual, new DigitalInput(0), new DigitalInput(1));
   
    //Creates an XbobxController on port 0 as seen in the driver station
    xb = new XboxController(0);

    //Creates a Joystick on port 1 as seen in the driver station
    j = new Joystick(1);

    //starts the robot with dt as the drive train, arm a, and XboxController xb
    RobotBase.startRobot(() -> new RobotAssembly(dt, a, xb));
  }
}
