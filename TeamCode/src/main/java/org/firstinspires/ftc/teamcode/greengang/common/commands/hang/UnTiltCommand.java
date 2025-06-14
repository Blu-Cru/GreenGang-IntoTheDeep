package org.firstinspires.ftc.teamcode.greengang.common.commands.hang;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class UnTiltCommand extends InstantCommand {
    public UnTiltCommand() {
        super (
                () -> {
                    Robot.getInstance().hang.ts.untilt();
                }
        );
        addRequirements((Subsystem) Robot.getInstance().outtakeClaw);
    }
}