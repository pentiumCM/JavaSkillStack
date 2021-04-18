package indi.pentiumcm.python;

import java.lang.System;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.InputStream;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.python
 * @className: PyThread
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/3/23 11:04
 * @describe:
 */
public class PyThread {
    public static void main(String[] args) {

        Socket socket = null;

        OutputStream os = null;
        PrintStream out = null;

        InputStream is = null;
        BufferedReader br = null;
        try {
            InetAddress addr = InetAddress.getLocalHost();
//            String host = addr.getHostName();
            String host = "127.0.0.1";
            //String ip=addr.getHostAddress().toString(); //获取本机ip
            //log.info("调用远程接口:host=>"+ip+",port=>"+12345);

            // 1. connect to serverSocket
            // 初始化套接字，设置访问服务的主机和进程端口号，
            // HOST是访问python进程的主机名称，可以是IP地址或者域名，PORT是python进程绑定的端口号
            socket = new Socket(host, 9001);

            // 设置 timeout 为 5 s
            socket.setSoTimeout(5000);

            // 2. send
            // 获取输出流对象
            os = socket.getOutputStream();
            out = new PrintStream(os);
            // 发送内容
            out.print("F:\\test\\hello.jpg");
            // 告诉服务进程，内容发送完毕，可以开始处理
            out.print("over");

            //关闭socket的输出流
            socket.shutdownOutput();

            // 3. receive from server
            // 获取服务进程的输入流
            is = socket.getInputStream();
            br = new BufferedReader(new InputStreamReader(is, "utf-8"));
            String tmp = null;
            StringBuilder sb = new StringBuilder();
            // 读取内容
            while ((tmp = br.readLine()) != null) {
                sb.append(tmp).append('\n');
            }
            System.out.print(sb);
            // 解析结果
            //JSONArray res = JSON.parseArray(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 4. close socket
                socket.close();

                os.close();
                out.close();
                is.close();
                br.close();
            } catch (IOException e) {
            }
            System.out.print("远程接口调用结束.");
        }
    }
}
