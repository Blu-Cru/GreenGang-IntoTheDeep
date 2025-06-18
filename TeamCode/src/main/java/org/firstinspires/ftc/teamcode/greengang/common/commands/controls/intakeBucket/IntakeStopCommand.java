package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeBucket;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class IntakeStopCommand extends InstantCommand {
    public IntakeStopCommand(){
        super (
                () -> Robot.getInstance().intake.stop()
        );

        addRequirements(Robot.getInstance().intake);
    }
}
