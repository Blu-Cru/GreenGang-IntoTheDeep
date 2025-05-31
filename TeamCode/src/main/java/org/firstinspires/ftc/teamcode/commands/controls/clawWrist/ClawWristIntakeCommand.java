package org.firstinspires.ftc.teamcode.commands.controls.clawWrist;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.util.Robot;

public class ClawWristIntakeCommand extends InstantCommand {
    public ClawWristIntakeCommand(){
        super(
                () -> Robot.getInstance().clawWrist.init()
        );

        addRequirements((Subsystem) Robot.getInstance().clawWrist);
    }
}
