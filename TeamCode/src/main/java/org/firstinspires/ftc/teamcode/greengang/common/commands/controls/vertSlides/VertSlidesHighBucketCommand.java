package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.vertSlides;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class VertSlidesHighBucketCommand extends InstantCommand {
    public VertSlidesHighBucketCommand(){
        super(
                () -> Robot.getInstance().vs.highBucket()
        );
        addRequirements((Subsystem) Robot.getInstance().vs);
    }
}
