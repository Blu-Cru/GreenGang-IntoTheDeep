package org.firstinspires.ftc.teamcode.commands.controls.intakeWrist;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.GreenSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class IntakeWristIntakeCommand extends InstantCommand {
    public IntakeWristIntakeCommand(){
        super(
                () -> Robot.getInstance().intakeWrist.intake()
        );

        addRequirements((Subsystem) Robot.getInstance().intakeWrist);
    }
}
