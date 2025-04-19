package org.firstinspires.ftc.teamcode.common.commands.controls.vs;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.common.subsystems.Robot;

public class VertSlidesLowBucketCommand extends InstantCommand {
    public VertSlidesLowBucketCommand(){
        super(
                () -> Robot.getInstance().vs.lowBucket()
        );
        addRequirements((Subsystem) Robot.getInstance().vs);
    }
}
