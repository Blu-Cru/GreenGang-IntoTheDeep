package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.vertSlides;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;
//Lowers slides from the spec scoring height to ensure a firm click onto the specimen bar

public class VertSlidesHighSpecAutoScoreCommand extends InstantCommand {
    public VertSlidesHighSpecAutoScoreCommand(){
        super(
                () -> Robot.getInstance().vs.highSpecScore()
        );
        addRequirements( Robot.getInstance().vs);
    }
}
