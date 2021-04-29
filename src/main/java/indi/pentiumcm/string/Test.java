package indi.pentiumcm.string;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.string
 * @className: Test
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/4/21 20:57
 * @describe: 字符串测试类
 */
public class Test {


    public static void main(String[] args) {

        String a = "abc";
        String b = "abc";

        boolean f1 = (a == b);
        System.out.println(f1);


        String x = new String("abc");
        String y = new String("abc");
        boolean f2 = (x == y);
        System.out.println(f2);

        boolean f3 = (a == x);
        System.out.println(f3);


        System.out.println(x);
    }

}
