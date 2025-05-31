package org.firstinspires.ftc.teamcode.commands.controls.intakeWrist;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.util.Robot;

public class WristDownCommand extends InstantCommand {
    public WristDownCommand() {
        super (
                () -> {
                    Robot.getInstance().wrist.down();
                }
        );
        addRequirements((Subsystem) Robot.getInstance().wrist);
    }
}