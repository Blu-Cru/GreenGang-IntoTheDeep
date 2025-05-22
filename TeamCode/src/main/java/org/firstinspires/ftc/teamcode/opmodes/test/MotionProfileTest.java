package org.firstinspires.ftc.teamcode.opmodes.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.profile.MotionProfileGenerator;
import com.acmerobotics.roadrunner.profile.MotionSegment;
import com.acmerobotics.roadrunner.profile.MotionState;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoControllerEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.gamepad.StickyGamepad;
import org.firstinspires.ftc.teamcode.subsystems.util.MotionProfile;
@Config
@TeleOp(name="mpt", group="test")
public class MotionProfileTest extends LinearOpMode {
    public StickyGamepad StickyG1;
    
    public static String name = "wrist";
    public static double vMax = 5, aMax = 25, xI = 0, xF = 1;
    public MotionProfile mp;


    @Override
    public void runOpMode() throws InterruptedException {
        FtcDashboard dashboard = FtcDashboard.getInstance();
        Servo test = hardwareMap.get(Servo.class, name);
        ServoControllerEx controller = (ServoControllerEx) test.getController();
        controller.setServoPwmDisable(test.getPortNumber());
        mp = new MotionProfile(0,0,0,0);


        waitForStart();


        while(opModeIsActive()) {

            if (gamepad1.a) {
                mp = new MotionProfile(xF, test.getPosition(), vMax, aMax).start();
            }

            TelemetryPacket packet = new TelemetryPacket();
            packet.put("ServoPosition", test.getPosition());
            dashboard.sendTelemetryPacket(packet);

            test.setPosition(mp.getInstantTargetPosition());
            mp.telemetry(telemetry);
            telemetry.addData("name", name);
            telemetry.addData("position", test.getPosition());
            telemetry.update();
        }
    }

}
