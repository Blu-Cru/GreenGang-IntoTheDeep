package org.firstinspires.ftc.teamcode.common.commands.controls.vs;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.common.subsystems.Robot;

public class VertSlidesHangAboveCommand extends InstantCommand {
    public VertSlidesHangAboveCommand(){
        super(
                () -> Robot.getInstance().vs.hangAbove()
        );
        addRequirements((Subsystem) Robot.getInstance().vs);
    }
}
