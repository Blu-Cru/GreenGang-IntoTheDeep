package org.firstinspires.ftc.teamcode.common.commands.controls.intakeWrist;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.common.subsystems.Robot;

public class WristParallelCommand extends InstantCommand {
    public WristParallelCommand() {
        super (
                () -> {
                    Robot.getInstance().wrist.parallel();
                }
        );
        addRequirements((Subsystem) Robot.getInstance().wrist);
    }
}