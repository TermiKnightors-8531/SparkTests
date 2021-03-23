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
    //TODO find best speed for arm and intake
    private final double intakeSpeed = .5;

    //ints to hold current which should not be exceeded (used for sensing the arm motor position)
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
        armMotor.configFactoryDefault();
        armMotor.setNeutralMode(NeutralMode.Brake);
        intakeMotor.configFactoryDefault();
        intakeMotor.setNeutralMode(NeutralMode.Brake);
    }


    /*Constructor for Arm with PowerDistributionPanel channels for monitoring current
    * @param armID: the CAN ID of the arm
    * @param intakeID: the CAN ID of the intake motor
    * @param armController: enum value to select the method of controlling the arm (selects between 1 or multiple joysticks)
    * @param armChannel: the channel of the motor controller for the arm 
    * @param intakeChannel: the channel of the motor 
    * @param intakeSpeed: speed of arm 
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


    //TODO rework methods to work with enums (make more dynamic and adaptable for team's needs)

    //method to raise arm while limit switch has not been pressed
    public void raiseArm () {
        if(upSwitch.get())return;       //returns if arm is up
        while(!upSwitch.get()){
            armMotor.set(ControlMode.PercentOutput, .5);
        }
            armMotor.set(ControlMode.Disabled, 0);
    }
    
    //method to raise arm while limit switch has not been pressed
    public void lowerArm () {
        if(downSwitch.get())return;     //returns if arm is down
        while(!downSwitch.get()){
            armMotor.set(ControlMode.PercentOutput, -.5);
        }
            armMotor.set(ControlMode.Disabled, 0);
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

    public static ArmController getArmController() {
        return armController;
    }

    //returns the selected limit method
    public static Limit getLimt() {
        return l;
    }
    //enum for selecting what type of control is to be used for the arm
    //allows the arm and intake to be controlled through one XBoxController (that is also used for driving the robot)
    //or a second joystick (entirely separate from the driving portion), allowing two members of the team to drive the robot
    public enum ArmController {
        xbox(Main.xb), 
        joystick(Main.j);
        public final GenericHID h;
        private ArmController (GenericHID h){
            this.h = h;
        }
    };

    public enum Limit {
        dio,
        current,
        hybridUp,
        hybridDown;

        private Limit (String s){

        }
        private Limit (){
            
        }
    }

}
