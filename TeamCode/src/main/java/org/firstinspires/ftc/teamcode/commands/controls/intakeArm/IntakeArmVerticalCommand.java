package org.firstinspires.ftc.teamcode.commands.controls.intakeArm;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class IntakeArmVerticalCommand extends InstantCommand {
    public IntakeArmVerticalCommand(){
        super(
                () -> {
                    Robot.getInstance().intakeArm.vertical();
                }
        );

        addRequirements((Subsystem) Robot.getInstance().intake);
    }
}
