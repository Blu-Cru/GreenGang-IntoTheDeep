package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeWrist;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

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