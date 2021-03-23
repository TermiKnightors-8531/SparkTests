package frc.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
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

    //TESTS
    VictorSPX test_motor = new VictorSPX(10);

    public RobotAssembly (DriveTrain dt, Arm a, XboxController xb) {
        this.dt = dt;
        this.xb = xb;
        test_motor.configFactoryDefault();
        test_motor.setNeutralMode(NeutralMode.Brake);
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
        if(armController == null) {
            System.out.println("arm is null");
        }
    }

    @Override
    public void teleopPeriodic() {
        this.dt.drive(xb);
        test_motor.set(ControlMode.PercentOutput, .5);
        //TODO debug error (null pointer)
        // switch(armController){
        //     case xbox:
                
        //         //checks to see if the 'a' button on the controller has been presesed since last check
        //         //if(pressed) --> raises arms
        //         if(((XboxController) armController.h).getAButtonPressed()){
        //             a.raiseArm();
        //         }

        //         //checks to see if the 'b' button on the controller has been presesed since last check
        //         if(((XboxController) armController.h).getBButtonPressed()){
        //             a.lowerArm();
        //         }

        //         //checks to see if the 'x' button on the controller has been presesed since last check
        //         if(((XboxController) armController.h).getXButtonPressed()){
        //             a.raiseArm();
        //         }

        //         //checks to see if the 'y' button on the controller has been presesed since last check
        //         if(((XboxController) armController.h).getYButtonPressed()){
        //             a.lowerArm();
        //         }
        //         break;
        //     case joystick:
        //         //TODO Handle joystick control of the arm
        //         if(((Joystick) armController.h).getRawButton(0)){
                    
        //         }
        //         break;
        // }
    }


}
