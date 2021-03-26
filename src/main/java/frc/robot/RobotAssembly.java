package frc.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Arm.ArmController;

public class RobotAssembly extends TimedRobot{
    private  Arm a;
    private XboxController xb;
    private DriveTrain dt;
    private Timer timer = new Timer();
    private ArmController armController;
    private static Joystick j = Main.j;

    public RobotAssembly (DriveTrain dt, Arm a, XboxController xb) {
        this.dt = dt;
        this.xb = xb;
        this.a = a;
    }

    @Override
    public void robotInit() {
        this.armController = a.getArmController();
    }
    //TEMP
    @Override
    public void autonomousInit() {

    }
    
    //TEMP motor test
    @Override
    public void autonomousPeriodic() {
        
    }

    @Override
    public void teleopInit() {
        if(a == null) System.out.println("Arm is null");
        if(a.armMotor == null) System.out.println("Arm Motor is null");
        if(a.intakeMotor == null) System.out.println("Intake Motor is null");
    }

    @Override
    public void teleopPeriodic() {
        //drives with joysticks at 75%
        this.dt.drive(xb,.75,.75);
        /*
        * A Button: Starts Intake Motor (Arm.startIntake)
        * B Button: Stops Intake Motor  (Arm.stopIntake)
        * Y Button: Reverses the Intake Motor (Arm.reverseIntake)
        * DPad Right-Up(315)/Up(0)/Left-Up(45): Raises the arm (Arm.raiseArm)
        * DPad Right-Down(225)/Down(180)/Left-Down(135)
        */
        switch(armController){
            //handles code for the single joystick control operation
            case xbox:
                System.out.println("Xbox");
                //starts intake if 'A' button is pressed
                System.out.println(this.xb.getAButton());
                if(this.xb.getAButton()){
                    a.startIntake();
                }

                //stops intake if 'B' button is pressed
                if(this.xb.getBButton()){
                    a.stopIntake();
                }

                //reverses intake if 'Y' button is pressed
                if(this.xb.getYButton()){
                    a.reverseIntake();
                }

                //if DPAD value is not pressed set the motor to idle
                if(this.xb.getPOV()==-1){
                    a.idleArm();
                }

                //raises arm is the DPAD value is 315, 360, 0, or 45
                if(this.xb.getPOV()==315 || this.xb.getPOV()==360 || this.xb.getPOV()==0|| this.xb.getPOV()==45){
                    a.raiseArm();
                    System.out.println("Rasing arm");
                }

                //lowers arm is the DPAD value is 225, 180, or 135
                if(this.xb.getPOV()==225 || this.xb.getPOV()==180 || this.xb.getPOV()==135){
                    a.lowerArm();
                    System.out.println("Lowering arm");
                }

                //ends the case of xbox
                break;

            //handles code for multi-person control of the 
            case joystick:

                //if the joystick is pushed forwards, the arm is raised
                if(j.getY()>.2){
                    a.lowerArm();
                }

                //if the joystick is pulled backwards, the arm is lowered              
                if(j.getY()<-.2) {
                    a.raiseArm();
                }


                break;
        }
    }

 
}
