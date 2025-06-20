package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.horizSlides;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class HorizontalSlidesExtendHalfwayCommand extends InstantCommand {
    public HorizontalSlidesExtendHalfwayCommand(){
        super(
                () -> {
                    Robot.getInstance().horizontalSlides.extendHalfWay();
                }
        );

        addRequirements(Robot.getInstance().horizontalSlides);
    }
}
