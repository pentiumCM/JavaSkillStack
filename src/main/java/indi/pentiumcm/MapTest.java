package indi.pentiumcm;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm
 * @className: Test
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2020/9/25 21:52
 * @describe:
 */
public class MapTest {


    public static void main(String[] args) {

        String arg = "874070358708058219";


        String splitStr = "string147200,874070358708058219,WM12S3600W,WQ,9407,405821,A,PLC,报废/anyType,重庆,符合,无,,有,3,有,,2020/10/5 19:57:55\n" +
                ",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,";

        String[] str = splitStr.split(",", -1);

        System.out.println(str);


/**
 * 最常见也是大多数情况下用的最多的，一般在键值对都需要使用
 */
        boolean b = true;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("熊大", "棕色");
        map.put("熊二", "黄色");
        map.put("熊三", b);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String mapKey = entry.getKey();
            Object mapValue = entry.getValue();

            Class<?> valueClass = mapValue.getClass();
            //字符串
            if (String.class.equals(valueClass)) {
                String value = (String) mapValue;
                System.out.println(mapKey + " " + value);
            }
            //字符串数组
            else if (String[].class.equals(valueClass)) {

                String[] value = (String[]) mapValue;
                System.out.println(mapKey + " " + mapValue);
            } else if (boolean.class.equals(valueClass) || Boolean.class.equals(valueClass)) {
                boolean value = (boolean) mapValue;
                System.out.println(mapKey + " " + mapValue);
            }
            //int
            else if (int.class.equals(valueClass) || Integer.class.equals(valueClass)) {
                int value = (int) mapValue;
                System.out.println(mapKey + " " + mapValue);
            }
            //int[]
            else if (int[].class.equals(valueClass)) {
                int[] value = (int[]) mapValue;
                System.out.println(mapKey + " " + mapValue);
            }
            //double || Double
            else if (double.class.equals(valueClass) || Double.class.equals(valueClass)) {
                String value = (String) mapValue;
                System.out.println(mapKey + " " + mapValue);
            }
            //double[]
            else if (double[].class.equals(valueClass)) {
            } else {
                System.out.println("类型转化异常!");
            }
        }


    }


}
