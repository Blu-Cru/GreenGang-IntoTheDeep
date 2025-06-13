package org.firstinspires.ftc.teamcode.commands.spec;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.controls.arm.ClawArmInSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.wrist.ClawWristInSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.claw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vertSlides.SlidesLiftSlightlyCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vertSlides.VertSlidesStartCommand;
import org.firstinspires.ftc.teamcode.subsystems.util.Robot;

public class SpecIntakeCommand extends SequentialCommandGroup {
    public SpecIntakeCommand(){
        super(
                new SequentialCommandGroup(
                        new ClawArmInSpecCommand(),
                        new ClawWristInSpecCommand(),
                        new OuttakeClawOpenCommand(),
                        new VertSlidesStartCommand()
//                        new ConditionalCommand(
//                                new VertSlidesStartCommand(),
//                                new WaitCommand(0),
//                                () -> Robot.getInstance().distanceSensor.isFull()
//                        )
                )
        );
    }
}
