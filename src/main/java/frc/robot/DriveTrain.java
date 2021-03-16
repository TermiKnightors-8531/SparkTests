package frc.robot;

import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

public class DriveTrain {
    //class to hold the two motor controllers for a single side
    side left, right;

    //TODO Evaluate necesity (I may not need this class/it may be redundant)
    //I am just trying to increase the verbosity of the code
    class motor extends CANSparkMax {
        private int deviceID, pChan;
        public motor(int deviceID, MotorType type, int pChan) {
            super(deviceID, type);
            this.deviceID = deviceID;
            this.pChan = pChan;
        }
        
    }
    
    class side {
        private int fID,rID;                                //ints to hold the CAN ID's
        private CANSparkMax front,rear;                     //motor controller instances
        private int fchan, rchan;

        //constructor with CAN IDs
        public side(int fID, int rID){
            this.fID = fID;
            this.rID = rID;
            front = new CANSparkMax(this.fID, MotorType.kBrushed);
            rear =  new CANSparkMax(this.rID, MotorType.kBrushed);
        }
        
        //constructor with CAN IDs and power channels from the PDP (for monitoring current output)
        public side(int fID, int rID, int fchan, int rchan){
            this.fID = fID;
            this.rID = rID;
            this.fchan = fchan;
            this.rchan = rchan;
            front = new CANSparkMax(this.fID, MotorType.kBrushed);
            rear =  new CANSparkMax(this.rID, MotorType.kBrushed);
        }

        //function to drive the two motors at the same speed
        public void drive(double speed) {
            front.set(speed);
            rear.set(speed);
        }


    }
}
