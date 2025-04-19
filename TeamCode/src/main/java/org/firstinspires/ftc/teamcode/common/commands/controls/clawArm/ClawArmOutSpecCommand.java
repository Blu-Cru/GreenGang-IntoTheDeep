package org.firstinspires.ftc.teamcode.common.commands.controls.clawArm;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.common.subsystems.Robot;
public class ClawArmOutSpecCommand extends InstantCommand {
    public ClawArmOutSpecCommand(){
        super (
                () -> Robot.getInstance().clawArm.vert()
        );
        addRequirements((Subsystem) Robot.getInstance().clawArm);
    }
}
