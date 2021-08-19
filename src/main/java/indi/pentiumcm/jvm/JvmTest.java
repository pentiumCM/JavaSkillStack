package indi.pentiumcm.jvm;


import java.util.Random;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.jvm
 * @className: JvmTest
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/8/18 19:53
 * @describe: JVM 测试类
 */
public class JvmTest {


    /**
     * JVM体系结构1：类加载器
     * 类加载器：负责加载.class文件
     *
     * @param object
     */
    public void classLoaderTest(Object object) {
        // 返回此 {@code Object} 的运行时类
        final Class<?> objectClass = object.getClass();

        // 获取当前类加载器
        final ClassLoader classLoader = objectClass.getClassLoader();
        System.out.println("当前类加载器：" + classLoader);

        // 父类类加载器（设计双亲委派机制）
        System.out.println("EXC类加载器：" + classLoader.getParent());
        System.out.println("BOOT类加载器：" + classLoader.getParent().getParent());
    }

    /**
     * JVM体系结构2：运行时数据区大小情况
     */
    public void jvmInfo() {
        // 调整 JVM 的内存大小
        // -Xms1024m -Xmx1024m -XX:+PrintGCDetails

        // JVM 内存
        // 默认情况下：JVM分配的总最大内存是电脑内存的 1/4，初始化内存是 1/64
        // 返回虚拟机试图使用的最大内存
        long max = Runtime.getRuntime().maxMemory();
        System.out.println("JVM 最大内存：" + (double) max / (1024 * 1024));

        // 返回 JVM 初始化的总内存
        long total = Runtime.getRuntime().totalMemory();
        System.out.println("JVM 初始化总内存：" + (double) total / (1024 * 1024));

    }


    /**
     * JVM体系结构2：运行时数据区 —— 堆
     * 一个JVM只有一个堆，所有线程共享，所有对象实例都在堆上分配，堆是垃圾收集器管理的区域
     * 堆分为：新生代(伊甸园区，from，to)，老年代，永久代（元空间，逻辑上存在，物理上不存在）
     */
    public void jvmHeapOutOfMemoryError() {
        String str = "hello world!";

        // 模拟堆溢出问题 (OOM)
        while (true) {
            str += str + new Random().nextInt(888888888) + new Random().nextInt(999999999);
        }
    }

    /**
     * JVM体系结构2：运行时数据区 —— 栈
     * 一个JVM只有一个堆，所有线程共享，所有对象实例都在堆上分配，堆是垃圾收集器管理的区域
     */
    public void jvmStackOverflowError() {
        // 循环调用，模拟栈溢出:StackOverflowError
        A();
    }

    /**
     * 闭环调用，触发栈溢出:StackOverflowError
     */
    public void A() {
        B();
    }

    public void B() {
        A();
    }


    public static void main(String[] args) {
        JvmTest jvmTest = new JvmTest();

        // 查看 JVM类加载器
//        jvmTest.classLoaderTest(jvmTest);

        // 查看 JVM 内存信息
//        jvmTest.jvmInfo();

        // 模拟 JVM 中栈溢出
//        jvmTest.jvmStackOverflowError();

        // 模拟 JVM 中堆内存溢出
        jvmTest.jvmHeapOutOfMemoryError();
    }

}
