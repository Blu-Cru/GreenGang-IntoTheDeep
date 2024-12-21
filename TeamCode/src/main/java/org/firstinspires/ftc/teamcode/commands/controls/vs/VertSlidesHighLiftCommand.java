package org.firstinspires.ftc.teamcode.commands.controls.vs;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class VertSlidesHighLiftCommand extends InstantCommand {
    public VertSlidesHighLiftCommand(){
        super(
                () -> Robot.getInstance().vs.high()
        );
        addRequirements((Subsystem) Robot.getInstance().vs);
    }
}
