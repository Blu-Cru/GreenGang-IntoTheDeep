package org.firstinspires.ftc.teamcode.commands.controls.vs;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class VertSlidesLowLiftCommand extends InstantCommand {
    public VertSlidesLowLiftCommand(){
        super(
                () -> Robot.getInstance().vs.lowBucket()
        );
        addRequirements((Subsystem) Robot.getInstance().vs);
    }
}
