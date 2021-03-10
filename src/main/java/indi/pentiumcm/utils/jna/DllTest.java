package indi.pentiumcm.utils.jna;


import com.sun.jna.Library;
import com.sun.jna.Native;


/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.utils.jna
 * @className: DllTest
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2020/9/25 19:19
 * @describe:
 */
public class DllTest {

    /**
     * DLL动态库调用方法
     *
     * @Description: 读取调用CDecl方式导出的DLL动态库方法
     * @author: LinWenLi
     * @date: 2018年7月18日 上午10:49:02
     */
    public interface CLibrary extends Library {
        // DLL文件默认路径为项目根目录，若DLL文件存放在项目外，请使用绝对路径。（此处：(Platform.isWindows()?"msvcrt":"c")指本地动态库msvcrt.dll）
//        CLibrary INSTANCE = (CLibrary) Native.loadLibrary((Platform.isWindows() ? "msvcrt" : "c"), CLibrary.class);
        CLibrary INSTANCE = (CLibrary) Native.load("src/main/java/indi/pentiumcm/utils/jna/msvcrt.dll", CLibrary.class);

        // 声明将要调用的DLL中的方法,可以是多个方法(此处示例调用本地动态库msvcrt.dll中的printf()方法)
        void printf(String format, Object... args);
    }

    // V2.0
    public interface Dll extends Library {
        Dll instance = (Dll) Native.loadLibrary("Dll1", Dll.class);

        public int add(int a, int b);

        public int substract(int a, int b);

        public void printHello();
    }


    /**
     * DLL动态库调用方法
     *
     * @Description: 读取调用DLL动态库方法
     */
    public interface C_Util extends Library {
        // DLL文件默认路径为项目根目录，若DLL文件存放在项目外，请使用绝对路径。（此处：(Platform.isWindows()?"msvcrt":"c")指本地动态库msvcrt.dll）
        // CLibrary INSTANCE = (CLibrary) Native.loadLibrary((Platform.isWindows() ? "msvcrt" : "c"), CLibrary.class);
//        CLibrary INSTANCE = (CLibrary) Native.loadLibrary("F:\\develop_code\\app\\android\\Stock\\app\\src\\main\\java\\indi\\pentiumcm\\stock\\dll\\Util.dll", CLibrary.class);
        C_Util INSTANCE = (C_Util) Native.load("src/main/java/indi/pentiumcm/utils/jna/Util.dll", C_Util.class);

        // 声明将要调用的DLL中的方法,可以是多个方法(此处示例调用本地动态库msvcrt.dll中的printf()方法)
        String Getsnr(String fcode, String fd, String ean, String sn);

    }


    /**
     * DLL动态库调用方法
     *
     * @Description: 读取调用CDecl方式导出的DLL动态库方法
     */
    public interface dllTest extends Library {
        // DLL文件默认路径为项目根目录，若DLL文件存放在项目外，请使用绝对路径。（此处：(Platform.isWindows()?"msvcrt":"c")指本地动态库msvcrt.dll）
        dllTest INSTANCE = (dllTest) Native.load("src/main/java/indi/pentiumcm/utils/jna/DllTest.dll", dllTest.class);

        // 声明将要调用的DLL中的方法,可以是多个方法(此处示例调用本地动态库msvcrt.dll中的printf()方法)
        public void ShowMessage(String text);

    }


    public static void main(String[] args) {
        CLibrary.INSTANCE.printf("Hello, World!");

//        String snr1 = C_Util.INSTANCE.Getsnr("123", "456", "789", "100");

        dllTest.INSTANCE.ShowMessage("HELLO");

        System.out.println("hello");
        // V2.0
/*        int sum = Dll.instance.add(5, 3);
        int sub = Dll.instance.substract(5, 3);
        System.out.println("add(5,3) = " + sum);
        System.out.println("substract(5,3) = " + sub);
        Dll.instance.printHello();*/
    }
}