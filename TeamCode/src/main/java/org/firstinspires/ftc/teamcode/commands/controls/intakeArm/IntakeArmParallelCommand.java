package org.firstinspires.ftc.teamcode.commands.controls.intakeArm;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class IntakeArmParallelCommand extends InstantCommand {
    public IntakeArmParallelCommand(){
        super(
                () -> {
                    Robot.getInstance().intakeArm.parallel();
                }
        );

        addRequirements((Subsystem) Robot.getInstance().intake);
    }
}
