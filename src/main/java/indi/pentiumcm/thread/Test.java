package indi.pentiumcm.thread;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.thread
 * @className: Test
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2020/10/25 20:41
 * @describe:
 */
public class Test implements Runnable {

    @Override
    public void run() {
        System.out.println("run : " + "  当前线程：" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {

        for (int i = 0; i < 30; i++) {

            Thread thread = new Thread(new Test());
            thread.start();

        }



    }


}
