//package org.firstinspires.ftc.teamcode.blucru.opmode.test.drive;
//
//import com.acmerobotics.dashboard.config.Config;
//import com.acmerobotics.roadrunner.geometry.Pose2d;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
//
//@Config
//@TeleOp(name = "Drive PID Test", group = "test")
//public class DrivePIDTest extends GreenLinearOpMode {
//    public static double targetX = 0, targetY = 0, targetHeading = 0;
//
//    @Override
//    public void initialize() {
//        addDrivetrain();
//        enableFTCDashboard();
//    }
//
//    @Override
//    public void onStart() {
//        targetX = drivetrain.pose.getX();
//        targetY = drivetrain.pose.getY();
//        targetHeading = drivetrain.getExternalHeading();
//    }
//
//    @Override
//    public void periodic() {
//        drive.updatePID();
//
//        if(gamepad1.a) {
//            drivetrain.pidTo(new Pose2d(targetX, targetY, targetHeading));
//        } else {
//            drivetrain.idle();
//
//            if(gamepad1.right_stick_button) {
//                drivetrain.setHeading(Math.PI/2);
//            }
//
//            double x = gamepad1.left_stick_x;
//            double y = -gamepad1.left_stick_y;
//            double rot = -gamepad1.right_stick_x;
//
//            Pose2d drivePose = new Pose2d(x, y, rot);
//
//            drivetrain.fieldCentricDrive(drivePose);
//        }
//
//        drivetrain.drawPose();
//    }
//}