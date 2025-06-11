package org.firstinspires.ftc.teamcode.commands.controls.wrist;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.util.Robot;

public class ClawWristBucketCommand extends InstantCommand {
    public ClawWristBucketCommand(){
        super(
                () -> Robot.getInstance().clawWrist.bucket()
        );
        addRequirements((Subsystem) Robot.getInstance().clawWrist);
    }
}
