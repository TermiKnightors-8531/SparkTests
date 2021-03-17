/*
* THIS CLASS HAS BEEN DEPRECATED
*/

package frc.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.*;
import edu.wpi.first.wpilibj.RobotDrive.*;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/* 
* This class served as a good test, but for the sake of making the code less verbose and more modular,
* separate classes with individual methods and constructors have been used. 
*/


@Deprecated
public class robo extends TimedRobot{
    XboxController xb = new XboxController(0);
    DifferentialDrive front;            //drive to control the two front motors
    DifferentialDrive rear;             //drive to control the two rear motors
    //Spark Controller Declarations
    CANSparkMax lf, lr, rf, rr;
    //CAN ID's of different 
    int lfID, lrID, rfID, rrID;
    //Power Distribution
    PowerDistributionPanel pdp = new PowerDistributionPanel(0);         //creates a power distribution panel with CAN ID 0
    /*
    * Constructor for setting CAN ID's of the motor controller
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
        front = new DifferentialDrive(this.lf, this.rf);
        rear = new DifferentialDrive(this.lr, this.rr);
    }
    
    @Override
    public void teleopPeriodic(){
        syncDrive();
                            
    }

    //functions to drive both front and rear motors at the same time
    public void syncDrive(){
        front.tankDrive(xb.getY(Hand.kLeft), xb.getY(Hand.kRight));
        rear.tankDrive(xb.getY(Hand.kLeft), xb.getY(Hand.kRight));
        System.out.println(pdp.getCurrent(0));          //reads current from PDP channel 0
    }
}
