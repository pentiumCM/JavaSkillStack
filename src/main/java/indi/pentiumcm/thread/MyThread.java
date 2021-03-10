package indi.pentiumcm.thread;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.thread
 * @className: MyThread
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2020/10/21 14:19
 * @describe:
 */
public class MyThread {


    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1");
            }
        });


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2");
            }
        });


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1.start();

        t2.start();

    }

}
