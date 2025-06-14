package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;
public class ClawArmOutSpecCommand extends InstantCommand {
    public ClawArmOutSpecCommand(){
        super (
                () -> Robot.getInstance().clawArm.specOuttake()
        );
        addRequirements((Subsystem) Robot.getInstance().clawArm);
    }
}
