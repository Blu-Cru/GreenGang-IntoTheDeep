package org.firstinspires.ftc.teamcode.commands.controls.hs;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class HorizontalSlidesRetractCommand extends InstantCommand{
    public HorizontalSlidesRetractCommand(){
        super(
                () -> {
                    Robot.getInstance().horizontalSlides.retract();
                }
        );

    }
}
