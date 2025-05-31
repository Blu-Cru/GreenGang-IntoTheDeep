package org.firstinspires.ftc.teamcode.commands.controls.intakeBucket;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.util.Robot;

public class IntakeStopCommand extends InstantCommand {
    public IntakeStopCommand(){
        super (
                () -> Robot.getInstance().intake.stop()
        );

        addRequirements((Subsystem) Robot.getInstance().intake);
    }
}
