package indi.pentiumcm.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.task
 * @className: TimerTakIml
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/6/4 17:04
 * @describe:
 */
public class TimerTakIml {

    // 发送人员信息的定时器
    private static Timer timer = new Timer();

    private static ConcurrentHashMap<String, TimerTask> taskMap = new ConcurrentHashMap<>();

    ExecutorService executorService = Executors.newFixedThreadPool(30);


    /**
     * 启动定时任务
     *
     * @param timer     定时器
     * @param timerTask 定时任务
     */
    public static void startTimerTask(Timer timer, TimerTask timerTask) {
        timer.purge();
        if (timerTask != null) {
            timer.schedule(timerTask, 1000, 3000);
        }
    }


    public static void main(String[] args) {

        // 设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                String time = df.format(new Date());
                System.out.println("定时器当前时间：" + time);
            }
        };

        startTimerTask(timer, timerTask);

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    long curTime = System.currentTimeMillis();
                    long endTime = 0;
                    try {
                        endTime = df.parse("2021-06-08 09:17:20").getTime();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if (curTime > endTime) {
                        System.out.println("停止定时任务！");
                        timerTask.cancel();
                        break;
                    }
                }
                System.out.println("成功关闭定时任务！");
            }
        }).start();
    }
}
