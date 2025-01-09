package org.firstinspires.ftc.teamcode.commands.controls.clawWrist;
import com.arcrobotics.ftclib.command.InstantCommand;

import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class ClawWristOutSpecCommand extends InstantCommand {
    public ClawWristOutSpecCommand(){
        super(
                () -> Robot.getInstance().clawWrist.Spec()
        );

        addRequirements((Subsystem) Robot.getInstance().clawWrist);
    }
}
