package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class ClawWristHighOutSpecCommand extends InstantCommand {
    public ClawWristHighOutSpecCommand(){
        super(
                () -> Robot.getInstance().clawWrist.highOutSpec()
        );

        addRequirements((Subsystem) Robot.getInstance().clawWrist);
    }
}
