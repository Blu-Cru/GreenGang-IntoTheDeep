package org.firstinspires.ftc.teamcode.common.commands.controls.intake;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.common.subsystems.Robot;

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
