package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class OuttakeClawLooseCloseCommand extends InstantCommand {
    public OuttakeClawLooseCloseCommand() {
        super (
                () -> {
                    Robot.getInstance().outtakeClaw.looseClose();
                }
        );
        addRequirements(Robot.getInstance().outtakeClaw);
    }
}
