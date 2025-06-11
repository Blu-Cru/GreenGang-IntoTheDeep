package org.firstinspires.ftc.teamcode.commands.controls.horizSlides;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.util.Robot;

public class HorizontalSlidesExtendHalfwayCommand extends InstantCommand {
    public HorizontalSlidesExtendHalfwayCommand(){
        super(
                () -> {
                    Robot.getInstance().horizontalSlides.extendHalfway();
                }
        );

        addRequirements((Subsystem) Robot.getInstance().horizontalSlides);
    }
}
