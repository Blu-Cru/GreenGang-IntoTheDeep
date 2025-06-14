package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeBucket;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class IntakeSpitCommand extends InstantCommand {
    public IntakeSpitCommand(){
        super (
                () -> Robot.getInstance().intake.spit()
        );

        addRequirements((Subsystem) Robot.getInstance().intake);
    }
}
