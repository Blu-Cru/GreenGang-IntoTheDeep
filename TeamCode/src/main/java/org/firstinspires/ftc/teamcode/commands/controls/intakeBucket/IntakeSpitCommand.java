package org.firstinspires.ftc.teamcode.commands.controls.intakeBucket;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class IntakeSpitCommand extends InstantCommand {
    public IntakeSpitCommand(){
        super (
                () -> Robot.getInstance().intake.spit()
        );

        addRequirements((Subsystem) Robot.getInstance().intake);
    }
}
