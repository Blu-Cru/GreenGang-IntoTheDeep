package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.vertSlides;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class VertSlidesLowBucketCommand extends InstantCommand {
    public VertSlidesLowBucketCommand(){
        super(
                () -> Robot.getInstance().vs.lowBucket()
        );
        addRequirements( Robot.getInstance().vs);
    }
}
