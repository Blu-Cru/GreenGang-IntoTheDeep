package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.controls.arm.ClawArmIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.controls.wrist.ClawWristIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.controls.horizSlides.HorizontalSlidesRetractCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeWrist.WristParallelCommand;
import org.firstinspires.ftc.teamcode.commands.controls.claw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vertSlides.SlidesLiftSlightlyCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vertSlides.VertSlidesStartCommand;
import org.firstinspires.ftc.teamcode.subsystems.util.Robot;
import org.firstinspires.ftc.teamcode.subsystems.outtake.arm.ClawArm;

/*
resets all subsystems to how they were in initialization state
 */
public class ResetCommand extends SequentialCommandGroup {
    public ResetCommand(){
        super (

//                new OuttakeClawCloseCommand(),
                new OuttakeClawOpenCommand(),
                new ClawWristIntakeCommand(),
                new ClawArmIntakeCommand(),
                new HorizontalSlidesRetractCommand(),
                new WristParallelCommand(),
//                new OuttakeClawOpenCommand(),
//                new ConditionalCommand(
//                        new WaitCommand(50),//previous 100
//
//                            // false;
//                        new WaitCommand(1000),
//
//                        () -> Robot.getInstance().clawArm.state == ClawArm.STATE.INSPEC
//                ),
                new VertSlidesStartCommand() //may need to swap
        );
    }

}
