package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class ClawArmFlatCommand extends InstantCommand {
    public ClawArmFlatCommand(){
        super (
                () -> Robot.getInstance().clawArm.flat()
        );
        addRequirements((Subsystem) Robot.getInstance().clawArm);
    }
}
