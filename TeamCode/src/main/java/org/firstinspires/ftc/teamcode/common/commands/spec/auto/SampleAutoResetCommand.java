package org.firstinspires.ftc.teamcode.common.commands.spec.auto;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.common.commands.controls.clawArm.ClawArmIntakeCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.clawWrist.ClawWristIntakeCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.vs.SlidesLiftSlightlyCommand;

/*
resets all subsystems to how they were in initialization state
 */
public class SampleAutoResetCommand extends SequentialCommandGroup {
    public SampleAutoResetCommand(){
        super (

//                new OuttakeClawCloseCommand(),
                new OuttakeClawOpenCommand(),
                new ClawWristIntakeCommand(),
                new ClawArmIntakeCommand(),
                new SlidesLiftSlightlyCommand()

        );
    }

}
