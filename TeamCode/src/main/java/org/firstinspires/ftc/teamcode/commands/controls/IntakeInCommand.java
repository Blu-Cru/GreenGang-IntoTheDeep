package org.firstinspires.ftc.teamcode.commands.controls;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

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
