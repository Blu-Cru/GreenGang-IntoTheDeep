package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.Robot;
import org.firstinspires.ftc.teamcode.subsystems.outtake.outtake.OuttakeClaw;

public class OuttakeClawOpenCommand extends InstantCommand {
    public OuttakeClawOpenCommand(){
        super(
                () -> Robot.getInstance().outtakeClaw.open()
        );



        addRequirements((Subsystem) Robot.getInstance().outtakeClaw);
    }
}
