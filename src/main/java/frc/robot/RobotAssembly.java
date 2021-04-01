package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Arm.ArmController;

public class RobotAssembly extends TimedRobot{
    //Variable Declaration: instances defined to be referenced throughout the class
    private  Arm a;
    private XboxController xb;
    private DriveTrain dt;                  
    private Timer timer = new Timer();              //Currently unimplemented
    private ArmController armController;
    private static Joystick j = Main.j;

    /*
    * Base Constructor for a RobotAssembly to be used in controlling all aspects of the robot
    * @param dt: the DriveTrain for operating the wheels of the robot
    * @param a: the Arm class containg intake and arm control methods
    * @param xb: the xbox controller for controlling the movements of the robot
    */
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
        
    }

    @Override
    public void teleopPeriodic() {
        //drives with joysticks at 55%
        //TODO write exponential function with asymptote to scale joystick control with more precision at low speeds and more power at high ends
        //^^^^Currently limits full power output
        this.dt.gearDrive(xb);
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
                //starts intake if 'A' button is pressed
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
                }

                //lowers arm is the DPAD value is 225, 180, or 135
                if(this.xb.getPOV()==225 || this.xb.getPOV()==180 || this.xb.getPOV()==135){
                    a.lowerArm();
                }

                //decrements gear number if the left bumper is pressed
                if(this.xb.getBumper(Hand.kLeft)){
                    dt.gear.decrementGear();
                }

                //increments gear number if the right bumper is pressed
                if(this.xb.getBumper(Hand.kRight)){
                    dt.gear.incrementGear();
                }
                
                //ends the case of xbox
                break;

            //handles code for multi-person control of the arm and intake
            case joystick:

                //TODO find correct button ID's

                //if the joystick is pushed forwards, the arm is raised
                if(j.getY()>.2){
                    a.raiseArm();
                }

                //if the joystick is pulled backwards, the arm is lowered              
                if(j.getY()<-.2) {
                    a.lowerArm();
                }

                //if the joystick is at or around center, the arm motor is disabled
                if(j.getY()>-.2 && j.getY()<.2) {
                    a.idleArm();
                }

                if(j.getRawButton(1)){
                    a.startIntake();
                }

                if(j.getRawButton(2)){
                    a.stopIntake();
                }

                if(j.getRawButton(3)){
                    a.reverseIntake();
                }
                break;
        }
    }

 
}
