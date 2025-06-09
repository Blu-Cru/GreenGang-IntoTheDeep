package org.firstinspires.ftc.teamcode.commands.hang;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.util.Robot;

public class TiltCommand extends InstantCommand {
    public TiltCommand() {
        super (
                () -> {
                    Robot.getInstance().hang.ts.tilt();
                }
        );
        addRequirements((Subsystem) Robot.getInstance().outtakeClaw);
    }
}