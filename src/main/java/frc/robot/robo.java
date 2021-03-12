package frc.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.*;
import edu.wpi.first.wpilibj.RobotDrive.*;

import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class robo extends TimedRobot{
    Joystick j = new Joystick(0);       //creates Joystick on port 0
    DifferentialDrive front;            //drive to control the two front motors
    DifferentialDrive rear;             //drive to control the two rear motors
    //Spark Controller Declarations
    CANSparkMax lf, lr, rf, rr;
    //CAN ID's of different 
    int lfID, lrID, rfID, rrID;
    /*
    * Constructor for setting CAN ID's of the 
    * @param lfID: CAN ID of left front motor
    * @param lrID: CAN ID of left rear  motor
    * @param rfID: CAN ID of right front motor
    * @param rrID: CAN ID of right rear motor
    */
    public robo (int lfID, int lrID, int rfID, int rrID){
        this.lfID = lfID;
        this.lrID = lrID; 
        this.rfID = rfID;
        this.rrID = rrID;
        this.lf = new CANSparkMax(lfID, MotorType.kBrushed);
        this.rf = new CANSparkMax(rfID, MotorType.kBrushed);
        this.lr = new CANSparkMax(lrID, MotorType.kBrushed);
        this.rr = new CANSparkMax(rrID, MotorType.kBrushed);
        front = new DifferentialDrive(lf, rf);
        rear = new DifferentialDrive(lr, rr);
    }
    
    @Override
    public void teleopPeriodic(){
        adrive();                    
    }

    //functions to drive both front and rear motors at the same time
    public void adrive(){
        front.arcadeDrive(j.getY(), j.getX());
        rear.arcadeDrive(j.getY(), j.getX());
    }
}
