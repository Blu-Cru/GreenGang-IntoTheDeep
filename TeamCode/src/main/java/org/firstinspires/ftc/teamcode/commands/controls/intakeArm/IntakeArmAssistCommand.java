package org.firstinspires.ftc.teamcode.commands.controls.intakeArm;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class IntakeArmAssistCommand extends InstantCommand {
    public IntakeArmAssistCommand(){
        super(
                () -> {
                    Robot.getInstance().intakeArm.assist();
                }
        );

        addRequirements((Subsystem) Robot.getInstance().intake);
    }
}
