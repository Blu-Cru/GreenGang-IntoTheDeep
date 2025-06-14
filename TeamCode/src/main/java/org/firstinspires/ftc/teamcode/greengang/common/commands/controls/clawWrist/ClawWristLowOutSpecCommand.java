package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class ClawWristLowOutSpecCommand extends InstantCommand {
    public ClawWristLowOutSpecCommand(){
        super(
                () -> Robot.getInstance().clawWrist.lowOutspec()
        );

        addRequirements((Subsystem) Robot.getInstance().clawWrist);
    }
}
