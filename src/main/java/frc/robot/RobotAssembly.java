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
    //private PowerDistributionPanel pdp = new PowerDistributionPanel(0);
    private Timer timer = new Timer();
    private ArmController armController = Arm.getArmController();

    public RobotAssembly (DriveTrain dt, Arm a, XboxController xb) {
        this.dt = dt;
        this.xb = xb;
        this.a = a;
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
        this.dt.drive(xb);
        /*
        * A Button: Starts Intake Motor (Arm.startIntake)
        * B Button: Stops Intake Motor  (Arm.stopIntake)
        * Y Button: Reverses the Intake Motor (Arm.reverseIntake)
        * DPad Right-Up(315)/Up(0)/Left-Up(45): Raises the arm (Arm.raiseArm)
        * DPad Right-Down(225)/Down(180)/Left-Down(135)
        */
    //     switch(armController){
    //         //handles code for the single control operation
    //         case xbox:
                
    //             //starts intake if 'A' button is pressed
    //             if(((XboxController) armController.h).getAButton()){
    //                 a.startIntake();
    //             }

    //             //stops intake if 'B' button is pressed
    //             if(((XboxController) armController.h).getBButton()){
    //                 a.stopIntake();
    //             }

    //             //reverses intake if 'Y' button is pressed
    //             if(((XboxController) armController.h).getYButton()){
    //                 a.reverseIntake();
    //             }

    //             //raises arm is the DPAD value is 315, 360, or 45
    //             if(((XboxController) armController.h).getPOV()==315 || ((XboxController) armController.h).getPOV()==360 || ((XboxController) armController.h).getPOV()==45){
    //                 a.raiseArm();
    //                 System.out.println("Rasing arm");
    //             }

    //             //lowers arm is the DPAD value is 225, 180, or 135
    //             if(((XboxController) armController.h).getPOV()==225 || ((XboxController) armController.h).getPOV()==180 || ((XboxController) armController.h).getPOV()==135){
    //                 a.lowerArm();
    //                 System.out.println("Lowers arm");
    //             }

    //             //stops intake if the DPAD has no input (idle)
    //             if(((XboxController) armController.h).getPOV()==315 || ((XboxController) armController.h).getPOV()==360 || ((XboxController) armController.h).getPOV()==45){
    //                 a.idleArm();
    //                 System.out.println("Rasing arm");
    //             }

    //             //ends the case of xbox
    //             break;

    //         //handles code for multi-person control of the 
    //         case joystick:
    //             //TODO Handle joystick control of the arm
    //             if(((Joystick) armController.h).getRawButton(0)){

    //             }
    //             break;
    //     }
    }


}
