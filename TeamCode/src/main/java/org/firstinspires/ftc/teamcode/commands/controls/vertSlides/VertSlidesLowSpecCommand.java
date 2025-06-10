package org.firstinspires.ftc.teamcode.commands.controls.vertSlides;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.util.Robot;

public class VertSlidesLowSpecCommand extends InstantCommand {
    public VertSlidesLowSpecCommand(){
        super(
                () -> Robot.getInstance().vs.lowSpec()
        );
        addRequirements((Subsystem) Robot.getInstance().vs);
    }
}
