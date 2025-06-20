package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class OuttakeClawOpenCommand extends InstantCommand {
    public OuttakeClawOpenCommand(){
        super(
                () -> Robot.getInstance().outtakeClaw.open()
        );



        addRequirements(Robot.getInstance().outtakeClaw);
    }
}
