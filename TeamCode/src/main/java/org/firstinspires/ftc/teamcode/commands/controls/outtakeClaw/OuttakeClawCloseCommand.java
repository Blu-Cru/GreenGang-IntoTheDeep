package org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class OuttakeClawCloseCommand extends InstantCommand {
    public OuttakeClawCloseCommand() {
        super (
                () -> {
                    Robot.getInstance().outtakeClaw.close();
                }
        );
        addRequirements((Subsystem) Robot.getInstance().outtakeClaw);
    }
}
