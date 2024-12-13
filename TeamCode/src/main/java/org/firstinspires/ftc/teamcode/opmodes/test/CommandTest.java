//package org.firstinspires.ftc.teamcode.opmodes.test;
//
//import com.arcrobotics.ftclib.command.CommandScheduler;
//
//import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
//import org.firstinspires.ftc.teamcode.subsystems.Alliance;
//import org.firstinspires.ftc.teamcode.subsystems.Robot;
//import org.firstinspires.ftc.teamcode.subsystems.drive.Drive;
//import org.firstinspires.ftc.teamcode.subsystems.intake.intake.IntakeColorSensor;
//
//public class CommandTest extends GreenLinearOpMode {
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//        robot = Robot.getInstance().create(hardwareMap);
//
//        addIntake();
//
//        robot.init();
//
//        waitForStart();
//
//        while(opModeIsActive()) {
//            stickyG1.update();
//            stickyG2.update();
//
//            CommandScheduler.getInstance().run();
//            intake.telemetry(telemetry);
//            telemetry.update();
//        }
//    }
//}
