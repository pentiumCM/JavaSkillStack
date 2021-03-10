package indi.pentiumcm.vms;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.vms
 * @className: TimeMillisUtil
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/1/22 16:39
 * @describe: 时间戳
 */
public class TimeMillisUtil {
    private static long timMillis;//时间戳
    private static long counter = 0L;//计数器

    /**
     * 获取计数器
     *
     * @return
     */
    private static synchronized String getCounter() {
        ++counter;
        if (counter < 10L) {
            return "000000" + String.valueOf(counter);
        } else if (counter < 100L) {
            return "00000" + String.valueOf(counter);
        } else if (counter < 1000L) {
            return "0000" + String.valueOf(counter);
        } else if (counter < 10000L) {
            return "000" + String.valueOf(counter);
        } else if (counter < 100000L) {
            return "00" + String.valueOf(counter);
        } else if (counter < 1000000L) {
            return "0" + String.valueOf(counter);
        } else if (counter < 10000000L) {
            return String.valueOf(counter);
        } else {
            counter = 1L;
            return "000000" + String.valueOf(counter);
        }
    }

    /**
     * 获取组合时间戳
     *
     * @return
     */
    public static String getTimMillis() {
        timMillis = System.currentTimeMillis();
        return getCounter() + String.valueOf(timMillis);
    }
}
