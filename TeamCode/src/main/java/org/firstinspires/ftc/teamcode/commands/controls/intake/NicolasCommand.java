package org.firstinspires.ftc.teamcode.commands.controls.intake;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class NicolasCommand extends InstantCommand {
    // intake spits, intake wrist goes down
    public NicolasCommand(){
        super (
                () -> {
                    Robot.getInstance().intake.nicolasSpit();
                }


        );

        addRequirements((Subsystem) Robot.getInstance().intake);
    }
}
