package indi.pentiumcm.utils.json;

import java.io.*;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.utils.json
 * @className: JsonParser
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2020/6/11 9:30
 * @describe: json解析器
 */
public class JsonParser {


    //获取json

    /**
     * 解析本地json文件中的数据内容
     *
     * @param jsonFile json文件路径
     * @return
     */
    public static String getJson(String jsonFile) {
        String jsonStr = "";
        try {
            File file = new File(jsonFile);
            FileReader fileReader = new FileReader(file);
            Reader reader = new InputStreamReader(new FileInputStream(file), "Utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (Exception e) {
            return null;
        }
    }


    public static void main(String[] args) {
        String json = JsonParser.getJson("F:\\data\\fourth_gear\\barrier.json");

        System.out.println("json:" + json);
    }


}
