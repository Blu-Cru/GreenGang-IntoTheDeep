package org.firstinspires.ftc.teamcode.commands.spec.auto;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.controls.clawArm.ClawArmIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawWrist.ClawWristIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.controls.hs.HorizontalSlidesRetractCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeWrist.WristParallelCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.SlidesLiftSlightlyCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesStartCommand;
import org.firstinspires.ftc.teamcode.subsystems.Robot;
import org.firstinspires.ftc.teamcode.subsystems.outtake.arm.ClawArm;

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
