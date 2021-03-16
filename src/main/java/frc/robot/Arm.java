package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;

public class Arm {
    int ID;
    DigitalInput upSwitch, downSwitch;

    public Arm (int ID, DigitalInput upSwitch, DigitalInput downSwitch){
        this.ID = ID;
        this.upSwitch = upSwitch;
        this.downSwitch = downSwitch;
    }

    //method to raise arm while limit switch has not been pressed
    public void raiseArm () {
        while(!upSwitch.get()){
            //TODO add motor control
        }
    }
}
