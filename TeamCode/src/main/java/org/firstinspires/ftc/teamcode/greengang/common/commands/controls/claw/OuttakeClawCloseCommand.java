package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class OuttakeClawCloseCommand extends InstantCommand {
    public OuttakeClawCloseCommand() {
        super (
                () -> {
                    Robot.getInstance().outtakeClaw.close();
                }
        );
        addRequirements(Robot.getInstance().outtakeClaw);
    }
}
