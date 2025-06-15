package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.vertSlides;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.subsystems.slides.VertSlides;
import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class VertSlidesHighSpecSlightLiftCommand extends InstantCommand {
    public VertSlidesHighSpecSlightLiftCommand(){
        super(
                () -> Robot.getInstance().vs.pidTo(VertSlides.highSpec+10)
        );
        addRequirements((Subsystem) Robot.getInstance().vs);
    }
}
