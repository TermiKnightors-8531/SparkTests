package frc.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
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
    }

    @Override
    public void teleopPeriodic() {
        this.dt.drive(xb);
        switch(armController){
            //handles code for the single control operation
            case xbox:
                
                //raises arm if 'A' button is pressed
                if(((XboxController) armController.h).getAButton()){
                       a.raiseArm();
                }

                //lowers arm is 'B' button is pressed
                if(((XboxController) armController.h).getBButton()){
                       a.lowerArm();
                }

                //stops arm movement if the A or B button is not pressed
                if(!((XboxController) armController.h).getAButton()&&((XboxController) armController.h).getAButton()){
                    a.idleArm();
                }

                //ends the case of xbox
                break;

            //handles code for multi-person control of the 
            case joystick:
                //TODO Handle joystick control of the arm
                if(((Joystick) armController.h).getRawButton(0)){

                }
                break;
        }
    }


}
