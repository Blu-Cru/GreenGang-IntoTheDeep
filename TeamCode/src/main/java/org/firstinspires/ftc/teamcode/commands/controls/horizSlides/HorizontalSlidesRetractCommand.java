package org.firstinspires.ftc.teamcode.commands.controls.horizSlides;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.util.Robot;

public class HorizontalSlidesRetractCommand extends InstantCommand{
    public HorizontalSlidesRetractCommand(){
        super(
                () -> {
                    Robot.getInstance().horizontalSlides.retract();
                }
        );

        addRequirements((Subsystem) Robot.getInstance().intake);
    }
}
