package org.firstinspires.ftc.teamcode.commands.controls.vertSlides;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.util.Robot;

public class VertSlidesStartCommand extends InstantCommand {
    public VertSlidesStartCommand(){
        super(
                () -> Robot.getInstance().vs.start()
        );
        addRequirements((Subsystem) Robot.getInstance().vs);
    }
}
