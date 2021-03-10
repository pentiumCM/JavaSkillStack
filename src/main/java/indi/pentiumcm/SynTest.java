package indi.pentiumcm;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm
 * @className: Test
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2020/9/25 21:52
 * @describe:
 */
public class SynTest {

    /**
     * 判断字符串是否为URL
     *
     * @param urls 用户头像key
     * @return true:是URL、false:不是URL
     */
    public static boolean isHttpUrl(String urls) {
        boolean isurl = false;
      /*  String regex = "(((https|http)?://)?([a-z0-9]+[.])|(www.))"
                + "\\w+[.|\\/]([a-z0-9]{0,})?[[.]([a-z0-9]{0,})]+((/[\\S&&[^,;\u4E00-\u9FA5]]+)+)?([.][a-z0-9]{0,}+|/?)";//设置正则表达式
*/
        String regex = "(((https|http)?://)?([a-z0-9]+[.])|(www.))";//设置正则表达式

        Pattern pat = Pattern.compile(regex.trim());//比对
        Matcher mat = pat.matcher(urls.trim());
        isurl = mat.matches();//判断是否匹配
        if (isurl) {
            isurl = true;
        }
        return isurl;
    }

    public static void main(String[] args) {
/*        String content = "https://192.168";

        String pattern = "http://.*|https://.*";

        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println("字符串中是否包含了 'http' 子字符串? " + isMatch);*/


        String str = "外部单据ID1";

        boolean isContain = str.contains("外部单据ID1");

        System.out.println(isContain);

    }


}
