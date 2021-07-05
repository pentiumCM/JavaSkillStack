package indi.pentiumcm.socket.bio;


import cn.hutool.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.socket
 * @className: SocketUtils
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/3/23 21:45
 * @describe: socket 工具类
 */
public class Test {


    public static void main(String[] args) {
        SocketUtils_json socketUtils = SocketUtils_json.getInstance();

        Map<String, Object> sendData2 = new HashMap<>();
        sendData2.put("imgpath", "D:\\data\\person_img2");
        sendData2.put("output", "F:\\test\\output2");
        socketUtils.sendSocketMsg(sendData2, new SocketUtils_json.SocketUtilCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                System.out.println("成功2!");

//                Map<String, Object> stringObjectMap = result.toMap();
//                Set<String> keySet = stringObjectMap.keySet();
//
//                for (String item : keySet) {
//                    System.out.println(item + "：" + stringObjectMap.get(item));
//                }
            }

            @Override
            public void onError(Exception e) {
                System.out.println("失败2");
            }
        });

    }
}
