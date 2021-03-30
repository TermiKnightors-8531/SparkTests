package frc.robot;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import java.util.ArrayList;
import java.util.Arrays;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;


public class DriveTrain {
    //instances of side for both left and right side (container for left and right wheel motor controllers)
    private side left, right; 
    public Gear gear;
    //constructor with two sides 
    public DriveTrain(side left, side right){
        this.left = left;
        this.right = right;
        Gear gear = new Gear();
    }

    //constructor to create two sides from 4 IDs
    public DriveTrain(int lf, int lr, int rf, int rr){
        this.left = new side(lf, lr);
        this.right = new side(rf, rr);
    }
    
    //function to drive robot based on the left and right joystick
    public void drive(XboxController xb){
        this.left.drive(xb.getY(Hand.kLeft));
        this.right.drive(xb.getY(Hand.kRight));
    }

    //function to drive robot based on the left and right joystick
    public void drive(XboxController xb, double left, double right){
        this.left.drive(xb.getY(Hand.kLeft),left);
        this.right.drive(xb.getY(Hand.kRight),right);
    }

    //drives based on the selected gear
    public void gearDrive(XboxController xb){ 
        this.left.drive(xb.getY(Hand.kLeft), gear.getScaler());
        this.right.drive(xb.getY(Hand.kRight), gear.getScaler());
    }

    /*
    * class to represent a single side (two motors per wheel)
    * defined as static to allow access without super class reference
    */
    static class side {
        private int fID,rID;                                //ints to hold the CAN ID's
        private CANSparkMax front,rear;                     //motor controller instances
        private int fchan, rchan;                           //possible ints for holding PDP channels for motor controllers
        private boolean inverted = false; 

        public Gear gear;
        /*
        * Base Constructor for a side
        * @param fID: the CAN ID of the front motor
        * @param rID: the CAN ID of the rear motor
        */
        public side(int fID, int rID){
            this.fID = fID;
            this.rID = rID;
            front = new CANSparkMax(this.fID, MotorType.kBrushed);
            
            rear =  new CANSparkMax(this.rID, MotorType.kBrushed);
        }
        
        /*
        * Constructor for a side with CAN ID's and a boolean to invert control of the side
        * @param fID: the CAN ID of the front motor
        * @param rID: the CAN ID of the rear motor
        * @param inverted: inverts the control of the motor if true
        */
        public side(int fID, int rID, boolean inverted){
            this.fID = fID;
            this.rID = rID;
            this.inverted = inverted;
            front = new CANSparkMax(this.fID, MotorType.kBrushed);
            front.setInverted(this.inverted);
            rear =  new CANSparkMax(this.rID, MotorType.kBrushed);
            rear.setInverted(this.inverted);
        }

        //basic function to drive the two motors at the same speed based on a given speed 
        public void drive(double speed) {
            front.set(speed);
            rear.set(speed);
        }

        /*
        * function to drive the two motors at the same speed with a prescaled value 
        * @param speed: speed from -1 to 1 of the two motors
        * @param scaler: number from 0 to 1.00 to scale the input of the joysticks
        */
        public void drive(double speed, double scaler) {
            front.set((speed*scaler));
            rear.set((speed*scaler));
        }

        //TODO complete deadzone implementation
        /*
        * function to drive the two motors at the same speed with joystick adjustment
        * @param speed: speed from -1 to 1 of the two motors
        * @param scaler: number from 0 to 1.00 to scale the input of the joysticks
        * @param deadzone: range of dead zone (an area of no control) for the joystick (prevents 'ghost driving')
        */        
        public void drive(double speed, double scaler, double deadzone){
            front.set((speed*scaler));
            rear.set((speed*scaler));
        }

    }

    static class Gear {
        /*
        * Gear 0: .40
        * Gear 1: .50
        * Gear 2: .55
        * Gear 3: .70
        */
        private double[] speeds;
        private int totalGears = 4;
        private int currentGear = 1;
        public Gear () {
            double[] speed = {.40, .50, .55, .70};
            this.speeds = speed;
            this.currentGear = 2;
        }

        public Gear (double[] speeds, int totalGears, int currentGear){
            this.speeds = speeds;
            this.totalGears = totalGears;
            this.currentGear = currentGear;
        }
        public double getScaler() {
            return speeds[currentGear];
        }

        public void incrementGear(){
            if(currentGear<(totalGears-1))currentGear++;
            System.out.println(currentGear);
        }

        public void decrementGear(){
            if(currentGear>0)currentGear--;
            System.out.println(currentGear);
        }
    }

}
