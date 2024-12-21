package org.firstinspires.ftc.teamcode.commands.controls;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class IntakeWristTransferCommand extends InstantCommand {

    public IntakeWristTransferCommand(){
        super(
                () -> Robot.getInstance().intakeWrist.transfer()
        );

        addRequirements((Subsystem) Robot.getInstance().intakeWrist);
    }

}
