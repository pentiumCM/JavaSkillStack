package indi.pentiumcm.vms;


import indi.pentiumcm.vms.SignatureUtil;
import indi.pentiumcm.vms.TimeMillisUtil;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm
 * @className: Demo
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2020/10/8 10:46
 * @describe:
 */
public class Demo {


    public static void main(String[] args) throws Exception {

        String timMillis = TimeMillisUtil.getTimMillis();

        String encryptStr = SignatureUtil.getEncryptStr(
                "ebbc6b245edb4562be6c04ff8da734c2",
                "ea452497b3724a9e9df94b1a3b5860c9",
                "5d4c1cdb09d041b5bfcddeb3d4f3adf8",
                timMillis,
                5);

        System.out.println("tm：" + timMillis);
        System.out.println("SignatureUtil：" + encryptStr);


    }

}
