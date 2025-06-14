package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class OuttakeClawLooseToggleCommand extends InstantCommand {
    public OuttakeClawLooseToggleCommand() {
        super (
                () -> {
                    Robot.getInstance().outtakeClaw.toggleLoose();
                }
        );
        addRequirements((Subsystem) Robot.getInstance().outtakeClaw);
    }
}