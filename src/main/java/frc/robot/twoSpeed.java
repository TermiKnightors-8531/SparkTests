package frc.robot;

import frc.robot.DriveTrain.Gear;

public class twoSpeed extends Gear{
    private double low = .55;
    private double high = .75;
    private int current = 0;

    @Override 
    public void incrementGear() {
        this.current = 1;
    }

    @Override
    public void decrementGear() {
        this.current = 0;
    }

    @Override
    public double getScaler(){
        if(current==1) return high;
        else return low;
    }
}
