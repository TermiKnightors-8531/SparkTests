package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public abstract class ArmV2 {

    //motor controller for arm and intake motors
    private VictorSPX armMotor, intakeMotor;
    //CAN ID's of the arm and intake motor controllers 
    private int armID, intakeID;
    
    //TODO find appropriate speeds
    //variables to define speed of intake motor and speed of arm movement
    private final double intakeSpeed = .37;
    private final double armSpeed = .5;

    public ArmV2 (int armID, int intakeID) {
        this.armID = armID;
        this.intakeID = intakeID;
    }
    //methods to be implemented by sub classes

    //method to raise arm
    public abstract void raiseArm();

    //method to lower arm
    public abstract void lowerArm();

    //method to disable arm motor
    public void idleArm() {
        this.armMotor.set(ControlMode.Disabled, 0);
    }

    //starts intake 
    public void startIntake() {
        this.intakeMotor.set(ControlMode.PercentOutput, this.intakeSpeed);
    }
    
    //stops intake 
    public void stopIntake() {
        this.intakeMotor.set(ControlMode.Disabled, 0);
    }

    //reverses intake 
    public void reverseIntake() {
        this.intakeMotor.set(ControlMode.PercentOutput, -this.intakeSpeed);
    }

    public class ManualArm extends ArmV2{
        public ManualArm(int armID, int a){
            super(armID,a);
        }
        @Override 
        public void raiseArm() {

        }

        @Override 
        public void lowerArm() {
            
        }


    }






}