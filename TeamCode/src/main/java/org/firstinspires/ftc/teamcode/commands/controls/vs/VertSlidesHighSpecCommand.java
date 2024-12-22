package org.firstinspires.ftc.teamcode.commands.controls.vs;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class VertSlidesHighSpecCommand extends InstantCommand {
    public VertSlidesHighSpecCommand(){
        super(
                () -> Robot.getInstance().vs.highSpec()
        );
        addRequirements((Subsystem) Robot.getInstance().vs);
    }
}
