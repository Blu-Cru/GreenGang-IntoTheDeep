package org.firstinspires.ftc.teamcode.commands.controls.intakeBucket;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.util.Robot;

public class IntakeInCommand extends InstantCommand{
    public IntakeInCommand(){
        super(
                () -> {
                    Robot.getInstance().intake.in();
                }
        );

        addRequirements((Subsystem) Robot.getInstance().intake);
    }
}
