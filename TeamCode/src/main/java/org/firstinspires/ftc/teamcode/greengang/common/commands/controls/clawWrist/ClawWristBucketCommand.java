package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class ClawWristBucketCommand extends InstantCommand {
    public ClawWristBucketCommand(){
        super(
                () -> Robot.getInstance().clawWrist.bucket()
        );
        addRequirements(Robot.getInstance().clawWrist);
    }
}
