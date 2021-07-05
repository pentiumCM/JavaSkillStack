package indi.pentiumcm;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
/**
 * 获取项目根路径的方法
 */

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm
 * @className: Test
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2020/9/25 21:52
 * @describe:
 */
public class SynTest {


    public static void stop(Timer timer) {

        System.out.println("停止定时器！");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
    }

    public static void main(String[] args) {


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {

                System.out.println("11232");
            }
        }, 2000, 1000);

        stop(timer);



    }


}








