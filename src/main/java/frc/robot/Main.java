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
import frc.robot.DriveTrain.side;

public final class Main {
  private Main() {}

  //creates a publically accesable Power Distribution Panel for monitoring current draw on motors
  //also can read temperature and battery voltage
  public PowerDistributionPanel pdp = new PowerDistributionPanel(0);

  public static XboxController xb;

  public static Joystick j;
  public static void main(String... args) {

    //specifices a drive train with motor controller IDs 1 and 2 for the left side motors and 3 and 4 for the right side
    DriveTrain dt = new DriveTrain((new side(1,2,false)), new side(3,4,true));

    //Creates an arm with a CAN ID of the 10 for the arm motor and a CAN ID of 11 for the intake motor
    //manual control of the arm is used, so no autonomous limiting methods are currently implemented
    Arm a = new Arm(11, 10, ArmController.xbox, Limit.manual);
    
    //Creates an XbobxController on port 0 as seen in the driver station
    xb = new XboxController(0);

    //Creates a Joystick on port 1 as seen in the driver station
    j = new Joystick(1);

    //starts the robot with dt as the drive train, arm a, and XboxController xb
    RobotBase.startRobot(() -> new RobotAssembly(dt, a, xb));
  }
}
