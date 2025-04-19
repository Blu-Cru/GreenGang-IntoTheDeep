package org.firstinspires.ftc.teamcode.common.commands.controls.clawWrist;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.common.subsystems.Robot;

public class ClawWristSpecInitCommand extends InstantCommand {
    public ClawWristSpecInitCommand(){
        super(
                () -> Robot.getInstance().clawWrist.specInit()
        );

        addRequirements((Subsystem) Robot.getInstance().clawWrist);
    }
}
