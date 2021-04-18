package indi.pentiumcm.socket;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.socket
 * @className: Client_json
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/3/24 11:47
 * @describe:
 */
public class Client_json {

    public static void main(String args[]) throws Exception {
        //为了简单起见，所有的异常都直接往外抛
        String host = "127.0.0.1";  //要连接的服务端IP地址
        int port = 9001;   //要连接的服务端对应的监听端口
        //与服务端建立连接
        Socket client = new Socket(host, port);
        //建立连接后就可以往服务端写数据了

        Map<String, Object> sendData = new HashMap<>();
        sendData.put("1", "11111");
        sendData.put("2", "22222");

        DataOutputStream outputStream = null;
        outputStream = new DataOutputStream(client.getOutputStream());

        JSONObject json = new JSONObject(sendData);
        String jsonString = json.toString();
        byte[] jsonByte = jsonString.getBytes();

        System.out.println("发的数据长度为:" + jsonByte.length);
        outputStream.write(jsonByte);
        outputStream.flush();
        System.out.println("传输数据完毕");
        client.shutdownOutput();


        //以下为接受服务器端发来的数据
        DataInputStream inputStream = null;
        String strInputStream = "";

        inputStream = new DataInputStream(new BufferedInputStream(client.getInputStream()));
        byte[] by = new byte[20480];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int nbyte;
        while ((nbyte = inputStream.read(by)) != -1) {
            baos.write(by, 0, nbyte);
        }
        strInputStream = new String(baos.toByteArray());


        System.out.println(strInputStream.toString());
//        System.out.println(js.get("nextDayHot"));

    }
}
