package org.firstinspires.ftc.teamcode.common.commands.controls.hs;

import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.common.subsystems.Robot;

public class HorizontalSlidesRetractCommand extends InstantCommand{
    public HorizontalSlidesRetractCommand(){
        super(
                () -> {
                    Robot.getInstance().horizontalSlides.retract();
                }
        );

    }
}
