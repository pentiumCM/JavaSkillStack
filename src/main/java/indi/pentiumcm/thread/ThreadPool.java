package indi.pentiumcm.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.thread
 * @className: ThreadPool
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2020/10/22 17:40
 * @describe:
 */
public class ThreadPool {


    //创建基本线程池
    final static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            5,
            10,
            1,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(50));


    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }


    static void threadPoolTest() {

        for (int i = 0; i < 30; i++) {


            final int finali = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                        System.out.println("run : " + finali + "  当前线程：" + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };


            threadPoolExecutor.execute(runnable);
        }
    }


    /**
     * 带带返回值的线程池，通过 Callable 接口
     */
    static void thread2callable() {
        // 1. 创建线程池
        ExecutorService defaultThreadPool = Executors.newFixedThreadPool(3);

        // 2. 创建一个Callable，3秒后返回String类型
        Callable myCallable = new Callable() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                System.out.println("calld方法执行了");
                return "call方法返回值";
            }
        };


        // 3. 将 Callable 接口提交给任务队列
        FutureTask futureTask = new FutureTask<>(myCallable);

        // 4. 线程池中进行执行
        defaultThreadPool.submit(futureTask);

        // 5. 解析返回的结果
        try {
            String res = (String) futureTask.get();
            System.out.println("获取返回值: " + res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {

//        thread2callable();

        threadPoolTest();
        System.out.println("主线程");

//        threadPoolTest();


    }

}
