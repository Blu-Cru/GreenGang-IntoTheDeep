package org.firstinspires.ftc.teamcode.opmodes.test;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.bucket.high.ScoringHighBucketCommand;
import org.firstinspires.ftc.teamcode.subsystems.util.Robot;

/*
 * This is a simple routine to test turning capabilities.
 */
@Config
@TeleOp(name = "sample outtake test", group = "test")
public class sampleOuttakeTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        CommandScheduler.getInstance().registerSubsystem(Robot.getInstance().clawArm);
        waitForStart();
        new ScoringHighBucketCommand().schedule();
        while(opModeIsActive()){
            CommandScheduler.getInstance().run();
        }

    }
}
