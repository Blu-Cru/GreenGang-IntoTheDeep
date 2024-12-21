package org.firstinspires.ftc.teamcode.commands.controls.vs;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class VertSlidesLowerCommand extends InstantCommand {
    public VertSlidesLowerCommand(){
        super(
                () -> Robot.getInstance().vs.lower()
        );
        addRequirements((Subsystem) Robot.getInstance().vs);
    }
}
