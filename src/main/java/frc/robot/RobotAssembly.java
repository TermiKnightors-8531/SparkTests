package frc.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;

public class RobotAssembly extends TimedRobot{
    private  Arm a;
    private XboxController xb;
    private DriveTrain dt;
    //private PowerDistributionPanel pdp = new PowerDistributionPanel(0);
    private Timer timer = new Timer();

    public RobotAssembly (DriveTrain dt, Arm a, XboxController xb){
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

    }


}
