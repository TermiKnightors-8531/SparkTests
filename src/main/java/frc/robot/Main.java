package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Arm.ArmController;
import frc.robot.Arm.Limit;
import frc.robot.DriveTrain.side;

public final class Main {
  private Main() {}

  /*
  * Creates a publically accesable Power Distribution Panel on CAN ID 0 for monitoring current draw on motors
  */
  public PowerDistributionPanel pdp = new PowerDistributionPanel(0);

  public static XboxController xb;

  public static Joystick j;
  
  public static void main(String... args) {

    /*
    * Creates a new drive train object with a left and right side
    * Sets left side motors to CAN IDs 1 and 2; left side is not inverted
    * Sets right side motors to CAN IDs 3 and 4; right side is inverted
    */
    DriveTrain dt = new DriveTrain((new side(1,2,false)), new side(3,4,true));

    /*
    * Creates a new Arm object for controlling arm and intake movement
    * Sets CAN ID of Arm motor controller to 11 and the Intake motor controller to 10
    * Selects separate joystick control for the arm allowing a for a two person drive team
    * (Change to ArmContronller.xbox for a singular controller for the drive train and the arm) 
    * Sets Limiting method to manual control
    * (Change to Limit.current for current monitoring and limiting)
    */
    Arm a = new Arm(11, 10, ArmController.joystick, Limit.manual);
    
    /*
    * Creates a new XboxController on port 0 (as seen in the driver station)
    */
    xb = new XboxController(0);

    /*
    * Creates a new Joystick on port 1 (as seen in the driver station)
    */
    j = new Joystick(1);

    /*
    * Starts and Initializes the Robot 
    * Creates a new instance of the RobotAssembly with the following variables
    *   dt: the drivetrain for controlling wheel movement on both sides of the robot
    *   a: the arm for controlling arm movement and the intake of the balls
    *   xb: the xbox used to drive the drivetrain and operate the functions of the arm
    */
    RobotBase.startRobot(() -> new RobotAssembly(dt, a, xb));
  }
}
