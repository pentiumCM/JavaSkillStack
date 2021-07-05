package indi.pentiumcm.socket.bio;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.socket
 * @className: Client
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/3/23 20:20
 * @describe:
 */
public class Client2 {


    public static void main(String[] args) {
        try {

            // 初始化一个socket
            Socket socket = new Socket("127.0.0.1", 8082);

            // 构建IO，通过socket获取字符流，要发送给服务器的信息
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

            // 向服务器端发送一条消息
            bw.write("2");
            bw.flush();

            // 读取服务器返回的消息
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//            String mess = br.readLine();
//            System.out.println("服务器：" + mess);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




