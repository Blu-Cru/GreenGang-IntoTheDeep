package org.firstinspires.ftc.teamcode.opmodes.test;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.bucket.auto.AutoSamplePart1;
import org.firstinspires.ftc.teamcode.commands.bucket.auto.AutoSamplePart2;
import org.firstinspires.ftc.teamcode.commands.spec.auto.AutoSpecIntake;
import org.firstinspires.ftc.teamcode.commands.spec.auto.AutoSpecOuttake;
import org.firstinspires.ftc.teamcode.commands.spec.auto.SamplePassThroughCommand;
import org.firstinspires.ftc.teamcode.subsystems.Robot;

@TeleOp(name = "auto commands test", group = "test")
public class AutoCommandsTest extends LinearOpMode {

    Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
       robot = Robot.getInstance().create(hardwareMap);

        robot.addIntake();
        robot.addOuttakeClaw();
        robot.addDrivetrain();
        robot.addIntakeColorSensor();
        robot.addIntake();
        robot.addIntakeWrist();
        robot.addOuttakeClaw();
        robot.addClawWrist();
        robot.addIntakeArm();
        robot.addVertSlides();
        robot.addTransfer();
        robot.init();

        waitForStart();

        while(opModeIsActive()) {

            if (gamepad1.a)
                new AutoSpecOuttake().schedule();
            else if (gamepad1.b)
                new AutoSpecIntake().schedule();
            else if (gamepad1.x)
                new SamplePassThroughCommand().schedule();
            else if (gamepad1.y)
                new AutoSamplePart1().schedule();
            else if (gamepad1.right_bumper)
                new AutoSamplePart2().schedule();

            CommandScheduler.getInstance().run();
        }
    }


}
