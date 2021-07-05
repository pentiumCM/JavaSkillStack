package indi.pentiumcm.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.socket
 * @className: NioServer
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/3/23 20:20
 * @describe: socket —— NIO非阻塞模式：解决 BIO 的阻塞问题
 */
public class NioServer {

    static List<SocketChannel> channelList = new ArrayList<>();

    public static void handler(Socket socket) {
        byte[] bytes = new byte[1024];

        System.out.println("等待连接read");

        // 接收客户端数据，阻塞方法
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

    public static void main(String[] args) throws IOException {
        // 创建 NIO ServerSocketChannel，与 BIO 的 serverSocket 类型
        ServerSocketChannel serverSocket = ServerSocketChannel.open();

        serverSocket.socket().bind(new InetSocketAddress(9002));

        // 设置 ServerSocketChannel 为非阻塞
        serverSocket.configureBlocking(false);

        System.out.println("服务启动成功！");

        while (true) {
            // 非阻塞模式 accept 方法不会阻塞
            // NIO 的非阻塞模型时由操作系统内部实现的，底层调用了 linux 内核的 accept 函数
            SocketChannel socketChannel = serverSocket.accept();
            if (socketChannel != null) {
                System.out.println("连接成功！");

                // 设置 SocketChannel 为非阻塞
                socketChannel.configureBlocking(false);
                // 保存客户端的连接在 list 中
                channelList.add(socketChannel);
            }

            // 遍历所有连接进行数据读写
            Iterator<SocketChannel> iterator = channelList.iterator();
            while (iterator.hasNext()) {
                SocketChannel sc = iterator.next();

                ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                // 非阻塞模式 read 方法不会阻塞，否准会阻塞
                int len = sc.read(byteBuffer);
                // 如果有数据，则把数据打印出来
                if (len > 0) {
                    System.out.println("收到消息：" + new String(byteBuffer.array()));
                } else if (len == -1) {
                    // 如果客户端断开，把socket从集合中去掉
                    iterator.remove();
                    System.out.printf("客户端断开连接！");
                }
            }
        }
    }
}
