package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.horizSlides;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class HorizontalSlidesExtendCommand extends InstantCommand {
    public HorizontalSlidesExtendCommand(){
        super(
                () -> {
                    Robot.getInstance().horizontalSlides.autoLower();
                }
        );

        addRequirements((Subsystem) Robot.getInstance().horizontalSlides);
    }
}
