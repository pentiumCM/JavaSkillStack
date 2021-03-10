package indi.pentiumcm.thread;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.thread
 * @className: Tets2
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2020/10/25 20:46
 * @describe:
 */
public class Tets2 extends Thread {

    @Override
    public void run() {
        System.out.println("开始run thread");
    }

    public static void main(String[] args) {

        for (int i = 0; i < 30; i++) {

            Tets2 tets2 = new Tets2();


        }
    }
}
