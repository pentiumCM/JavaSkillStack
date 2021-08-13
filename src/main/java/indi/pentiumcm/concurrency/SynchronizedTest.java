package indi.pentiumcm.concurrency;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.concurrency
 * @className: SynchronizedTest
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/8/14 1:27
 * @describe:
 */

/**
 * synchronized 关键字声明的方法同一时间只能被一个线程访问。
 * synchronized 修饰的 **方法** 或者 **代码块**。但是不能修饰构造器和属性。
 */
public class SynchronizedTest {

    public static boolean stop = false;

    public synchronized static void setStop(boolean stop) {
        VolatileTest.stop = stop;
    }

    public synchronized static boolean getStop() {
        return VolatileTest.stop;
    }

    public static void main(String args[]) throws InterruptedException {
        Thread testThread = new Thread() {
            @Override
            public void run() {
                int i = 1;
//                while (!stop) {
                while (!getStop()) {
                    i++;
                }
                System.out.println("Thread stop i=" + i);
            }
        };
        testThread.start();
        Thread.sleep(1000);
//        stop = true;
        setStop(true);
        System.out.println("now, in main thread stop is: " + stop);

        testThread.join();
    }
}



