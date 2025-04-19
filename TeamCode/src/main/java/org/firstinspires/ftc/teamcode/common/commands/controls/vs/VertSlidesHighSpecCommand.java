package org.firstinspires.ftc.teamcode.common.commands.controls.vs;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.common.subsystems.Robot;

public class VertSlidesHighSpecCommand extends InstantCommand {
    public VertSlidesHighSpecCommand(){
        super(
                () -> Robot.getInstance().vs.highSpec()
        );
        addRequirements((Subsystem) Robot.getInstance().vs);
    }
}
