package indi.pentiumcm.socket.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.socket
 * @className: BioSocketServer
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/3/23 20:20
 * @describe: socket —— bio
 */
public class BioSocketServer {

    /**
     * 饿汉式 - 实现单例模式
     */
    private static BioSocketServer instance = new BioSocketServer();

    private BioSocketServer() {
    }

    public static BioSocketServer getInstance() {
        if (instance == null) {
            instance = new BioSocketServer();
        }
        return instance;
    }


    // 线程池
    private static final ExecutorService threadPool = Executors.newCachedThreadPool();

    public static void handler(Socket socket) {
        byte[] bytes = new byte[1024];

        System.out.println("准备read");

        while (true) {
            // 接收客户端数据，阻塞方法，没有数据可读时就阻塞
            try {
                int read = socket.getInputStream().read(bytes);
                System.out.println("read读取完毕！");

                if (read != -1) {
                    System.out.println("接收到客户端的数据为：" + new String(bytes, 0, read));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8081);

        while (true) {
            System.out.println("等待连接！");

            // 阻塞方法
            Socket clientSocket = serverSocket.accept();
            System.out.println("有客户端连接！");

            // bio 方式起线程处理连接对象，会出现 C10K问题
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    handler(clientSocket);
                }
            });
        }
    }
}

