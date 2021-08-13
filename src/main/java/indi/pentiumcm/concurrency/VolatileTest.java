package indi.pentiumcm.concurrency;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.concurrency
 * @className: VolatileTest
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/8/13 23:56
 * @describe: volatile关键字测试
 */

/**
 * volatile 修饰的成员变量在每次被线程访问时，都强制从共享内存中重新读取该成员变量的值。
 * 而且，当成员变量发生变化时，会强制线程将变化值回写到共享内存。
 * 这样在任何时刻，两个不同的线程总是看到某个成员变量的同一个值。
 * 参考资料：https://www.jianshu.com/p/97b72fd68cbf
 */
public class VolatileTest {


    public volatile static Boolean stop = false;

    public static void main(String args[]) throws InterruptedException {
        Thread testThread = new Thread() {
            @Override
            public void run() {
                int i = 1;
                while (!stop) {
                    i++;
//                    System.out.println("内部 i=" + i);
                }
                System.out.println("Thread stop i=" + i);
            }
        };
        testThread.start();
        Thread.sleep(1000);
        stop = true;
        System.out.println("now, in main thread stop is: " + stop);

        testThread.join();
    }
}


class MyRunnable implements Runnable {
    private volatile boolean active;

    public void run() {
        active = true;
        while (active) {
            // 代码
            System.out.println("线程执行中");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        active = false;
        System.out.println("停止线程");
    }
}
