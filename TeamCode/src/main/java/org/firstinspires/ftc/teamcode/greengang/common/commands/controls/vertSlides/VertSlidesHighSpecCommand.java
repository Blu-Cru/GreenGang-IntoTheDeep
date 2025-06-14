package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.vertSlides;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class VertSlidesHighSpecCommand extends InstantCommand {
    public VertSlidesHighSpecCommand(){
        super(
                () -> Robot.getInstance().vs.highSpec()
        );
        addRequirements((Subsystem) Robot.getInstance().vs);
    }
}
