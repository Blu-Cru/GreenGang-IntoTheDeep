package org.firstinspires.ftc.teamcode.common.commands.controls.outtakeClaw;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.common.subsystems.Robot;

public class OuttakeClawToggleCommand extends InstantCommand {
    public OuttakeClawToggleCommand() {
        super (
                () -> {
                    Robot.getInstance().outtakeClaw.toggle();
                }
        );
        addRequirements((Subsystem) Robot.getInstance().outtakeClaw);
    }
}
