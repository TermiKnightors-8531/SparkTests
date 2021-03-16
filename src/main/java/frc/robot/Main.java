// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.XboxController;

public final class Main {
  private Main() {}

  public static void main(String... args) {
    //costructor for robot class
    DriveTrain dt = new DriveTrain(1,2,3,4);
    Arm a = new Arm(123, new DigitalInput(0), new DigitalInput(1));
    XboxController xb = new XboxController(0);
    RobotBase.startRobot(() -> new RobotAssembly(dt, a, xb));
  }
}
