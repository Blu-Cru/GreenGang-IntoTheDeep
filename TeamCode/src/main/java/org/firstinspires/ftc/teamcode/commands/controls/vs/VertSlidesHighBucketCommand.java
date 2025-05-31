package org.firstinspires.ftc.teamcode.commands.controls.vs;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.util.Robot;

public class VertSlidesHighBucketCommand extends InstantCommand {
    public VertSlidesHighBucketCommand(){
        super(
                () -> Robot.getInstance().vs.highBucket()
        );
        addRequirements((Subsystem) Robot.getInstance().vs);
    }
}
