package org.firstinspires.ftc.teamcode.commands.controls.clawWrist;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.util.Robot;

public class ClawWristTransferCommand extends InstantCommand {
    public ClawWristTransferCommand(){
        super(
                () -> Robot.getInstance().clawWrist.init()
        );

        addRequirements((Subsystem) Robot.getInstance().clawWrist);
    }
}
