package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.horizSlides;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class HorizontalSlidesExtendFullyCommand extends InstantCommand {
    public HorizontalSlidesExtendFullyCommand(){
        super(
                () -> {
                    Robot.getInstance().horizontalSlides.extendFully();
                }
        );

        addRequirements((Subsystem) Robot.getInstance().horizontalSlides);
    }
}
