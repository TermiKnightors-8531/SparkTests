package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;

import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode.*;

public class Arm {
    int armID,intakeID;
    //TODO determine correct limit switch
    DigitalInput upSwitch, downSwitch;
    VictorSPX armMotor,intakeMotor;
    //PowerDistribution Panel Channels for monitoring current
    private int armChannel, intakeChannel;
    private double intakeSpeed = .5;

    //constructor for creating an arm with ID's for VictorSPX controller
    public Arm (int armID, int intakeID, DigitalInput upSwitch, DigitalInput downSwitch){
        this.armID = armID;
        this.intakeID = intakeID;
        this.upSwitch = upSwitch;
        this.downSwitch = downSwitch;
        this.armMotor = new VictorSPX(armID);
        this.intakeMotor = new VictorSPX(intakeID);
        armMotor.configFactoryDefault();
        armMotor.setNeutralMode(NeutralMode.Brake);
        intakeMotor.configFactoryDefault();
        intakeMotor.setNeutralMode(NeutralMode.Brake);

    }

    /*constructor for creating an arm with ID's for VictorSPX controller and PowerDistributionPanel channels
    * @param armID: the CAN ID of the arm
    * @param intakeID: the CAN ID of the intake motor
    * @param armChannel: the channel of the motor controller for the arm 
    * @param intakeChannel: the channel of the motor 
    * @param brake: if true: brake on idle / if false: coast on idle
    * @param intakeSpeed: speed of arm 
    */ 
    public Arm (int armID, int intakeID, int armChannel, int intakeChannel, boolean brake, double intakeSpeed){
        this.armID = armID;
        this.intakeID = intakeID;
        this.armMotor = new VictorSPX(armID);
        this.intakeMotor = new VictorSPX(intakeID);
        this.armChannel = armChannel;
        this.intakeChannel = intakeChannel;
        armMotor.configFactoryDefault();
        intakeMotor.configFactoryDefault();
        if (brake) {
            armMotor.setNeutralMode(NeutralMode.Brake);
            intakeMotor.setNeutralMode(NeutralMode.Brake);
        }
        else {
            armMotor.setNeutralMode(NeutralMode.Coast);
            intakeMotor.setNeutralMode(NeutralMode.Coast);
        }
        this.intakeSpeed = intakeSpeed;
    }


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

    public void test_arm(){
        armMotor.set(ControlMode.PercentOutput, .5);
    }


}
