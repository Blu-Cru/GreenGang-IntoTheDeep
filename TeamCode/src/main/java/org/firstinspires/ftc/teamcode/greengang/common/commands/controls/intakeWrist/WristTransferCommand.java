package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeWrist;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class WristTransferCommand extends InstantCommand {
    public WristTransferCommand() {
        super (
                () -> {
                    Robot.getInstance().wrist.transfer();
                }
        );
        addRequirements((Subsystem) Robot.getInstance().wrist);
    }
}