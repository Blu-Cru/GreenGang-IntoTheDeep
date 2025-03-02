package org.firstinspires.ftc.teamcode.commands.controls.intakeBucket;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class NicolasCommand extends InstantCommand {
    public NicolasCommand(){
        super (
                () -> {
                    Robot.getInstance().wrist.down();
                    Robot.getInstance().intake.spit();
                }


        );

        addRequirements((Subsystem) Robot.getInstance().intake);
        addRequirements((Subsystem) Robot.getInstance().wrist);

    }
}
