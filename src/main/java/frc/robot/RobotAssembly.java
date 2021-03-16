package frc.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;

public class RobotAssembly extends TimedRobot{
    // TODO implement Arm
    private  Arm a;
    private XboxController xb;
    private DriveTrain dt;
    private PowerDistributionPanel pdp = new PowerDistributionPanel(0);

    public RobotAssembly (DriveTrain dt, Arm a, XboxController xb){
        this.dt = dt;
        this.xb = xb;
    }

    @Override
    public void teleopInit() {

    }

    public void teleopPeriodic() {
        this.dt.drive(xb);

    }


}
