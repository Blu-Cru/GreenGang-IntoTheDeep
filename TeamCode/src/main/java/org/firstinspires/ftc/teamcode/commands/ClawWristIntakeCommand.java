package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class ClawWristIntakeCommand extends InstantCommand {
    public ClawWristIntakeCommand(){
        super(
                () -> Robot.getInstance().clawWrist.intake()
        );

        addRequirements((Subsystem) Robot.getInstance().clawWrist);
    }
}
