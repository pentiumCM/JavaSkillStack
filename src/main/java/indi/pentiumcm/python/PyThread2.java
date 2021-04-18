package indi.pentiumcm.python;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.python
 * @className: PyThread
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/3/23 11:04
 * @describe:
 */
public class PyThread2 {
    public static void main(String[] args) {

        Socket socket = null;
        try {
            InetAddress addr = InetAddress.getLocalHost();
            String host = addr.getHostName();
            //String ip=addr.getHostAddress().toString(); //获取本机ip
            //log.info("调用远程接口:host=>"+ip+",port=>"+12345);

            // 1. connect to serverSocket
            // 初始化套接字，设置访问服务的主机和进程端口号，
            // HOST是访问python进程的主机名称，可以是IP地址或者域名，PORT是python进程绑定的端口号
            socket = new Socket(host, 12345);

            // 2. send
            // 获取输出流对象
            OutputStream os = socket.getOutputStream();
            PrintStream out = new PrintStream(os);
            // 发送内容
            out.print("F:\\test\\hello.jpg");
            // 告诉服务进程，内容发送完毕，可以开始处理
            out.print("over");

            //关闭socket的输出流
            socket.shutdownOutput();

            // 3. receive from server
            // 获取服务进程的输入流
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
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
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
            }
            System.out.print("远程接口调用结束.");
        }
    }
}
