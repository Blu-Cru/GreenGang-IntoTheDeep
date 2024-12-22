package org.firstinspires.ftc.teamcode.commands.controls.intakeArm;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class IntakeArmTransferCommand extends InstantCommand {
    public IntakeArmTransferCommand(){
        super(
                () -> {
                    Robot.getInstance().intakeArm.transfer();
                }
        );

        addRequirements((Subsystem) Robot.getInstance().intake);
    }
}
