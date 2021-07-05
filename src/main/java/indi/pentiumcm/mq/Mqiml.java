package indi.pentiumcm.mq;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.mq
 * @className: Mqiml
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/6/7 17:49
 * @describe: 自定义队列
 */
public class Mqiml {

    public static BlockingQueue blockingQueue = new LinkedBlockingQueue();

    public static void main(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Object take = null;
                    try {
                        take = blockingQueue.take();

                        System.out.println("当前时间:" + take);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String time = df.format(new Date());
                        blockingQueue.put(time);

                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }).start();
    }
}
