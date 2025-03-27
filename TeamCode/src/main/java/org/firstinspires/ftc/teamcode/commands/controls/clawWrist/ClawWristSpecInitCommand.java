package org.firstinspires.ftc.teamcode.commands.controls.clawWrist;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class ClawWristSpecInitCommand extends InstantCommand {
    public ClawWristSpecInitCommand(){
        super(
                () -> Robot.getInstance().clawWrist.specInit()
        );

        addRequirements((Subsystem) Robot.getInstance().clawWrist);
    }
}
