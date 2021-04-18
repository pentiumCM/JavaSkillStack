package indi.pentiumcm.socket;

import java.io.*;
import java.net.Socket;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.socket
 * @className: Client2
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/3/23 20:20
 * @describe:
 */
public class Client2 {

    public static void main(String[] args) {

        try {

            //初始化一个socket
            Socket socket = new Socket("127.0.0.1", 9999);

            //通过socket获取字符流
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            //通过标准输入流获取字符流
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));

            while (true) {
                String str = bufferedReader.readLine();
                bufferedWriter.write(str);
                bufferedWriter.write("\n");
                bufferedWriter.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}




