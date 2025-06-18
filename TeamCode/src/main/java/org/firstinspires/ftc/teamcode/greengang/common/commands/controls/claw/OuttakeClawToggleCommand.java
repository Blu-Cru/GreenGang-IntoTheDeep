package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class OuttakeClawToggleCommand extends InstantCommand {
    public OuttakeClawToggleCommand() {
        super (
                () -> {
                    Robot.getInstance().outtakeClaw.toggle();
                }
        );
        addRequirements(Robot.getInstance().outtakeClaw);
    }
}