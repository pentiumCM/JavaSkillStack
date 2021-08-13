package indi.pentiumcm.concurrency;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.concurrency
 * @className: JoinTesyt
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/8/14 1:08
 * @describe: join 测试
 */


/**
 * 对子线程threadA使用了join()方法之后，我们发现主线程会等待子线程执行完成之后才往后执行。
 * 参考资料：https://blog.csdn.net/u010983881/article/details/80257703
 */
public class JoinTest {

    public static void main(String[] args) {
        System.out.println("MainThread run start.");

        //启动一个子线程
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadA run start.");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("threadA run finished.");
            }
        });
        threadA.start();

        System.out.println("MainThread join before");
        try {
            threadA.join();    //调用join()
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("MainThread run finished.");
    }
}
