package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class Arm {
    //CAN IDs of the arm and intake motors 
    int armID,intakeID;

    //Digital Input ports to be used when limit switches are implemented
    DigitalInput upSwitch, downSwitch;

    //Motor controllers for the arm and intake motors (CAN ID's set in the )
    VictorSPX armMotor,intakeMotor;

    //PowerDistribution Panel Channels for monitoring current
    private int armChannel, intakeChannel;

    //Speed at which the arm moves and the motors spin to intake the balls
    private final double intakeSpeed = .93;
    private double armSpeed = .43;

    //ints to hold current which should not be exceeded (used for sensing the arm motor position)
    //TODO implement at later date
    private final int maxCurrentUp = 0;
    private final int maxCurrentDown = 0;

    //instance of enum to hold type of arm controller used
    public static ArmController armController;

    //instance of limiting type
    public static Limit l;

    /*
    * Base Constructor for Arm
    * @param armID: the CAN ID of the arm motor controller
    * @param intakeID: the CAN ID of the intake motor controller
    */
    public Arm(int armID, int intakeID, ArmController armController, Limit l) {
        this.armID = armID;
        this.intakeID = intakeID;
        this.armController = armController;
        this.armMotor = new VictorSPX(armID);
        this.intakeMotor = new VictorSPX(intakeID);
        armMotor.configFactoryDefault();
        armMotor.setNeutralMode(NeutralMode.Brake);
        intakeMotor.configFactoryDefault();
        intakeMotor.setNeutralMode(NeutralMode.Brake);
    }

    /*
    * Constructor for Arm with Limit Switches
    * @param armID: the CAN ID of the arm motor controller
    * @param intakeID: the CAN ID of the intake motor controller
    * @param DigitalInput upSwitch: DIO port of the upper limit switch
    * @param DigitalInput downSwitch: DIO port of the lower limit switch
    */
    public Arm (int armID, int intakeID, ArmController armController, Limit l, DigitalInput upSwitch, DigitalInput downSwitch){
        this(armID, intakeID, armController, l);
        this.upSwitch = upSwitch;
        this.downSwitch = downSwitch;
        this.armController = armController;
        armMotor.configFactoryDefault();
        armMotor.setNeutralMode(NeutralMode.Brake);
        intakeMotor.configFactoryDefault();
        intakeMotor.setNeutralMode(NeutralMode.Brake);
    }


    /*
    * Constructor for Arm with PowerDistributionPanel channels for monitoring current
    * @param armID: the CAN ID of the arm
    * @param intakeID: the CAN ID of the intake motor
    * @param armController: enum value to select the method of controlling the arm (selects between 1 or multiple joysticks)
    * @param armChannel: the channel of the motor controller for the arm 
    * @param intakeChannel: the channel of the motor 
    */ 
    public Arm (int armID, int intakeID, ArmController armController, Limit l, int armChannel, int intakeChannel){
        this(armID, intakeID, armController, l);
        this.armChannel = armChannel;
        this.intakeChannel = intakeChannel;
        armMotor.configFactoryDefault();
        intakeMotor.configFactoryDefault();
        armMotor.setNeutralMode(NeutralMode.Brake);
        intakeMotor.setNeutralMode(NeutralMode.Brake);

    }

    //method to raise arm
    public void raiseArm () {
        //TODO implement enum control
        armMotor.set(ControlMode.PercentOutput, armSpeed);
    }
    
    //method to lower arm
    public void lowerArm () {
        //TODO implement enum control
        armMotor.set(ControlMode.PercentOutput, -armSpeed);
    }

    //disables arm motor
    public void idleArm () {
        armMotor.set(ControlMode.Disabled, 0);
    }

    public void setIntakeSpeed(double d){
        this.armSpeed = d;
    }

    //starts intake motor and runs until the stopIntake method is invoked
    public void startIntake () {
        intakeMotor.set(ControlMode.PercentOutput, intakeSpeed);
    }

    //stops intake motor 
    public void stopIntake () {
        intakeMotor.set(ControlMode.Disabled, 0);
    }

    //starts intake motor in reverse at intake speed and runs until the stopIntake method is invoked
    public void reverseIntake() {
        intakeMotor.set(ControlMode.PercentOutput, -intakeSpeed);
    }

    public void startIntake(Joystick j){
        intakeMotor.set(ControlMode.PercentOutput, j.getThrottle());
    }

    public void reverseIntake(Joystick j){
        intakeMotor.set(ControlMode.PercentOutput, -j.getThrottle());
    }


    //returns the selected Arm Controller
    public ArmController getArmController(){
        return armController;
    }


    //returns the selected limit method
    public static Limit getLimt() {
        return l;
    }
    
    /*
    * enum for selecting what type of control is to be used for the arm
    * allows the arm and intake to be controlled through one XBoxController (that is also used for driving the robot)
    * or a second joystick (entirely separate from the driving portion), allowing two members of the team to drive the robot
    */
    public enum ArmController {
        xbox(Main.xb), 
        joystick(Main.j);
        public final GenericHID h;
        private ArmController (GenericHID h){
            this.h = h;
        }
    };

    /*
    * enum used to select the control method for stopping the arm movement
    * CURRENTLY-- manual controll only limited
    */
    public enum Limit {
        
        //uses Digital Input limiting on both upper and lower end
        dio,

        //uses current draw to find limit the arm movement
        current,

        //uses a limit switch on the upper end of the arm and current draw on the lower
        hybridUp,

        //uses a limit switch on the lower end of the arm and current draw on the upper
        hybridDown,

        //uses manual control through a type of joystick or xbox controller
        manual;

        private Limit (){
            
        }
    }

}
