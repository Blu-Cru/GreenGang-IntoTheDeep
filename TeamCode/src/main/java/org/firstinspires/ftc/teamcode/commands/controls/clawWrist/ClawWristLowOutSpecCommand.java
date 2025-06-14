package org.firstinspires.ftc.teamcode.commands.controls.clawWrist;
import com.arcrobotics.ftclib.command.InstantCommand;

import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.util.Robot;

public class ClawWristLowOutSpecCommand extends InstantCommand {
    public ClawWristLowOutSpecCommand(){
        super(
                () -> Robot.getInstance().clawWrist.lowOutspec()
        );

        addRequirements((Subsystem) Robot.getInstance().clawWrist);
    }
}
