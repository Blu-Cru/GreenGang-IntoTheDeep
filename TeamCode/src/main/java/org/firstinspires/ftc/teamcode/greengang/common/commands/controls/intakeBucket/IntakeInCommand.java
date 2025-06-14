package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeBucket;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class IntakeInCommand extends InstantCommand{
    public IntakeInCommand(){
        super(
                () -> {
                    Robot.getInstance().intake.in();
                }
        );

        addRequirements((Subsystem) Robot.getInstance().intake);
    }
}
