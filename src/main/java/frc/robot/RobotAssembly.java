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

    }

    @Override
    public void teleopPeriodic() {
        this.dt.drive(xb);
        switch(armController){
            case xbox:
                
                //checks to see if the 'a' button on the controller has been presesed since last check
                //if(pressed) --> raises arms
                if(((XboxController) armController.h).getAButtonPressed()){
                    a.raiseArm();
                }

                //checks to see if the 'b' button on the controller has been presesed since last check
                if(((XboxController) armController.h).getBButtonPressed()){
                    a.lowerArm();
                }

                //checks to see if the 'x' button on the controller has been presesed since last check
                if(((XboxController) armController.h).getXButtonPressed()){
                    a.raiseArm();
                }

                //checks to see if the 'y' button on the controller has been presesed since last check
                if(((XboxController) armController.h).getYButtonPressed()){
                    a.lowerArm();
                }
                break;
            case joystick:
                //TODO Handle joystick control of the arm
                if(((Joystick) armController.h).getRawButton(0)){
                    
                }
                break;
        }
    }


}
