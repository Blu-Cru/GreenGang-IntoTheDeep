package org.firstinspires.ftc.teamcode.common.commands.spec;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.common.commands.controls.clawArm.ClawArmInSpecCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.clawWrist.ClawWristInSpecCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.vs.SlidesLiftSlightlyCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.vs.VertSlidesStartCommand;

public class SpecIntakeCommand extends SequentialCommandGroup {
    public SpecIntakeCommand(){
        super(
                new SequentialCommandGroup(
                        new SlidesLiftSlightlyCommand(),
                        new ClawArmInSpecCommand(),
                        new ClawWristInSpecCommand(),
                        new OuttakeClawOpenCommand(),
                        new WaitCommand(200),
                        new VertSlidesStartCommand()
                )
        );
    }
}
