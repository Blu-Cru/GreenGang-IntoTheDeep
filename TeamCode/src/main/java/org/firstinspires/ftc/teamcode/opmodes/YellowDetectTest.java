//package org.firstinspires.ftc.teamcode.opmodes;
//
//import android.util.Size;
//
////import com.acmerobotics.dashboard.config.Config;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//import org.firstinspires.ftc.robotcore.external.stream.CameraStreamSource;
//import org.firstinspires.ftc.teamcode.testing.YellowDetectProcessor;
//import org.firstinspires.ftc.vision.VisionPortal;
////import com.acmerobotics.dashboard.FtcDashboard;
//
//@TeleOp(name = "Yellow detect test", group = "test")
//public class YellowDetectTest extends LinearOpMode {
//    VisionPortal visionPortal;
//    YellowDetectProcessor yd;
//    @Override
//    public void runOpMode() throws InterruptedException {
//        yd = new YellowDetectProcessor();
//        visionPortal = new VisionPortal.Builder()
//                .setCamera(hardwareMap.get(WebcamName.class, "webcam"))
//                .setCameraResolution(new Size(1280, 720))
//                .setStreamFormat(VisionPortal.StreamFormat.MJPEG)
//                .addProcessor(yd)
//                .build();
//
//        visionPortal.resumeStreaming();
//        visionPortal.setProcessorEnabled(yd, false);
//        FtcDashboard.getInstance().startCameraStream((CameraStreamSource) visionPortal, 30);
//
//    }
//}
