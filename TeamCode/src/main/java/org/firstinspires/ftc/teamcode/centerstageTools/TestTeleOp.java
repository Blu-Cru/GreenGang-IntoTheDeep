package org.firstinspires.ftc.teamcode.centerstageTools;

//import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

//import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
//import org.firstinspires.ftc.teamcode.subsystems.Grabber;
//import org.firstinspires.ftc.teamcode.subsystems.PlaneLauncher;
//import org.firstinspires.ftc.teamcode.subsystems.Wrist;


//@Config
@TeleOp(name = "TestTeleOp",group = "TeleOp")
public class TestTeleOp extends LinearOpMode {
//    public static int armRotateOuttakePos = 1450;
//    public static int hangUp = 3620;
//    public static int hangDown = -250;
//    public static int armExtendIntakePos = 100;


//    Arm arm;
    Drivetrain drivetrain;
//    Grabber grabber;
//    Wrist wrist;
//    PlaneLauncher launcher;

    double y;
    double x;
    double rx, power;



    @Override
    public void runOpMode() throws InterruptedException {
        power = 0;

        drivetrain = new Drivetrain(hardwareMap);
//        arm = new Arm(hardwareMap);
//        grabber = new Grabber(hardwareMap);
//        wrist = new Wrist(hardwareMap);
//        launcher = new PlaneLauncher(hardwareMap);
//
//        grabber.init();
//        wrist.init();
//        arm.teleInit();
//        launcher.init();


        while(opModeInInit()) {
            telemetry.update();
        }

        waitForStart();

        // Retrieve the IMU from the hardware map
        IMU imu = hardwareMap.get(IMU.class, "imu");
// Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
// Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);

        while(opModeIsActive()) {

            y = -gamepad1.left_stick_y;
            x = gamepad1.left_stick_x;
            rx = -gamepad1.right_stick_x;


            //Robot moves slower
            if(gamepad1.right_trigger > 0.4) {
                Drivetrain.drivePower = 0.3;
            }
            else {
                Drivetrain.drivePower = 0.6;
            }

            drivetrain.drive(x, y, rx);

            // Field Centric Attempt:

            if (gamepad1.options) {
                imu.resetYaw();
            }

            double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
            double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

            double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
            double frontLeftPower = (rotY + rotX + rx) / denominator;
            double backLeftPower = (rotY - rotX + rx) / denominator;
            double frontRightPower = (rotY - rotX - rx) / denominator;
            double backRightPower = (rotY + rotX - rx) / denominator;

            // call a method to use these?

            // DRIVE METHODS

            //2nd driver
            /*
            X - arm parallel
            triangle - outtake
            left bumper - left claw
            right bumper - right claw
            right trigger - arm extends manually
            left trigger - arm retracts manually
             */

//            //Makes the arm parallel to the ground
//            if (gamepad2.a) { //button X
//                arm.armRotateTargetPos = 700;
//                wrist.wristSetPos(wrist.depositPos);
//            }
//
//            //Positioning the arm to put pixels on the board
//            if (gamepad2.y) { //triangle
//                arm.armRotateTargetPos = armRotateOuttakePos;
//                wrist.wristSetPos(wrist.depositPos);
//            }
//            //the grabbers are closed until the buttons are pushed
//            if (gamepad2.left_bumper) {
//                grabber.leftGrabberSetPos(0.75);
//            }
//            else {
//                grabber.leftGrabberSetPos(0.5); //closed
//            }
//
//            if (gamepad2.right_bumper) {
//                grabber.rightGrabberSetPos(0); //0.7
//            }
//            else {
//                grabber.rightGrabberSetPos(0.25); //right Grabber closed 1
//            }
//
//            if(Math.abs(gamepad2.right_stick_y) > 0.1 && gamepad2.right_stick_button) {
//                arm.setArmExtendPower(-gamepad2.right_stick_y);
//            } else {
//                arm.setArmExtendPower(0);
//            }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //1st driver

            /*
            joysticks - driving
            left bumper - wrist down
            right bumper - wrist up
            dpad up - hang up
            dpad down - hang down
            options - plane launcher
            left trigger - arm rotates manually
             */
            //home position w/ wrist down
//            if(gamepad1.left_bumper) {
//                wrist.wristSetPos(Wrist.intakePos); //ground level
//                arm.armRotateTargetPos = 0;
//            }
//
//            //home position w/ wrist up
//            if (gamepad1.right_bumper) { //X button
//                arm.armRotateTargetPos = 0;
//                wrist.wristSetPos(wrist.depositPos-0.1);
//            }
//
//            /////////////////Hang/////////////////
//            if(gamepad1.dpad_up) {
//                wrist.wristSetPos(Wrist.depositPos);
//                arm.armRotateTargetPos = 1200;
//                arm.autoArmExtend(0.6, hangUp);
//            }
//            if (gamepad1.dpad_down) { //circle
//                arm.autoArmExtend(0.3, hangDown);
//                arm.armRotateTargetPos = 1200;
//            }
//
//            if(gamepad1.options) {
//                launcher.LauncherSetPower(launcher.launcherPos);
//            }
//            else{
//                launcher.LauncherSetPower(0);
//            }
//
//            //MANUAL ROTATION left trigger
//            if(Math.abs(gamepad1.left_trigger) > 0.1) {
//                // if trigger is being used, set rotate power
//                arm.setArmRotatePower(gamepad1.left_trigger);
//            }
//            else {
//                // otherwise set extend power to 0
//                arm.setArmRotatePower(0);
//            }
//
//            arm.update();
//
//            arm.telemetry(telemetry);
//            telemetry.addData("left_trigger", gamepad1.left_trigger);
//            telemetry.addData("Rotation power:", power);
//            telemetry.addData("gamepad1.b:", gamepad1.b);
//            telemetry.addData("gamepad2.y:", gamepad2.y);
//            telemetry.addData("gamepad2.left trigger:", gamepad2.left_trigger);
//            telemetry.addData("gamepad2.right trigger:", gamepad2.right_trigger);
//            telemetry.update();
        }
    }
}
