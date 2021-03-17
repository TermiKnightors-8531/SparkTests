package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;

import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode.*;

public class Arm {
    int armID,intakeID;
    DigitalInput upSwitch, downSwitch;
    VictorSPX armMotor,intakeMotor;

    private double intakeSpeed = .5;
    //constructor for creating an arm with ID's for VictorSPX controller
    public Arm (int armID, int intakeID, DigitalInput upSwitch, DigitalInput downSwitch){
        this.armID = armID;
        this.intakeID = intakeID;
        this.upSwitch = upSwitch;
        this.downSwitch = downSwitch;
        this.armMotor = new VictorSPX(armID);
        this.intakeMotor = new VictorSPX(intakeID);
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
}
