package org.firstinspires.ftc.teamcode.commands.controls.intakeArm;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class IntakeArmIntakeCommand extends InstantCommand {
    public IntakeArmIntakeCommand(){
        super(
                () -> {
                    Robot.getInstance().intakeArm.intake();
                }
        );

        addRequirements((Subsystem) Robot.getInstance().intake);
    }
}
