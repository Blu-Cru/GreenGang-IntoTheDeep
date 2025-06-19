package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class ClawArmVertCommand extends InstantCommand {
    public ClawArmVertCommand(){
        super (
                () -> Robot.getInstance().clawArm.vert()
        );
        addRequirements(Robot.getInstance().clawArm);
    }
}
