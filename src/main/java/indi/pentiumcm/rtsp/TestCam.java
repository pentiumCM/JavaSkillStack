package indi.pentiumcm.rtsp;

import com.github.sarxos.webcam.Webcam;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.hls
 * @className: TestCam
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/3/15 14:20
 * @describe:
 */


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.*;

import javax.imageio.ImageIO;
import javax.swing.*;


/**
 * Example of how to stream MJPEG with Webcam Capture.
 *
 * @author Bartosz Firyn (SarXos)
 */
public class TestCam {


    /**
     * 创建BufferedImage对象
     */
    public static BufferedImage FrameToBufferedImage(Frame frame) {
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bufferedImage = converter.getBufferedImage(frame);
//		bufferedImage=rotateClockwise90(bufferedImage);
        return bufferedImage;
    }

    /**
     * 处理图片，将图片旋转90度。
     */
    public static BufferedImage rotateClockwise90(BufferedImage bi) {
        int width = bi.getWidth();
        int height = bi.getHeight();
        BufferedImage bufferedImage = new BufferedImage(height, width, bi.getType());
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                bufferedImage.setRGB(j, i, bi.getRGB(i, j));
            }
        }
        return bufferedImage;
    }


    public static void main(String[] args) throws IOException {

        String file = "rtsp://admin:admin@192.168.1.218/1";
        FFmpegFrameGrabber grabber = FFmpegFrameGrabber.createDefault(file);
        grabber.setOption("rtsp_transport", "tcp"); // 使用tcp的方式，不然会丢包很严重
        grabber.setImageWidth(960);
        grabber.setImageHeight(540);
        System.out.println("grabber start");
        grabber.start();

        //1.播放视频
        CanvasFrame canvasFrame = new CanvasFrame("摄像机");
        canvasFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvasFrame.setAlwaysOnTop(true);
        OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();
        while (true) {
            Frame frame = grabber.grabImage();
            opencv_core.Mat mat = converter.convertToMat(frame);
            canvasFrame.showImage(frame);
        }


        //2.帧截图
//        File outPut = new File("E:/image/a.jpg");
//        while (true) {
//            Frame frame = grabber.grabImage();
//            if (frame != null) {
//                ImageIO.write(FrameToBufferedImage(frame), "jpg", outPut);
//                grabber.stop();
//                grabber.release();
//                System.out.println("图片已保存");
//                break;
//            }
//        }


    }
}



