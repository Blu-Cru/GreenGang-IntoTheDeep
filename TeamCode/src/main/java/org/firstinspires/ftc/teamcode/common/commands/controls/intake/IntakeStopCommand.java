package org.firstinspires.ftc.teamcode.common.commands.controls.intake;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.common.subsystems.Robot;

public class IntakeStopCommand extends InstantCommand {
    public IntakeStopCommand(){
        super (
                () -> Robot.getInstance().intake.stop()
        );

        addRequirements((Subsystem) Robot.getInstance().intake);
    }
}
