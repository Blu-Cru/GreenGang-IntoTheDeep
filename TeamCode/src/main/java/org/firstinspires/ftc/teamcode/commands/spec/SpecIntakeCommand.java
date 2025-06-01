package org.firstinspires.ftc.teamcode.commands.spec;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawArm.ClawArmInSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawWrist.ClawWristInSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.SlidesLiftSlightlyCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesStartCommand;
import org.firstinspires.ftc.teamcode.subsystems.util.Robot;

public class SpecIntakeCommand extends SequentialCommandGroup {
    public SpecIntakeCommand(){
        super(
                new SequentialCommandGroup(
                        new SlidesLiftSlightlyCommand(),
                        new ClawArmInSpecCommand(),
                        new ClawWristInSpecCommand(),
                        new OuttakeClawOpenCommand(),
                        new ConditionalCommand(
                                new VertSlidesStartCommand(),
                                new WaitCommand(0),
                                () -> Robot.getInstance().distanceSensor.isFull()
                        )
                )
        );
    }
}
