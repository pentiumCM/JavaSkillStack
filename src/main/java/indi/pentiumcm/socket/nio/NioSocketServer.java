package indi.pentiumcm.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.socket
 * @className: NioSocketServer
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/3/23 20:20
 * @describe: socket —— NIO非阻塞模式 + Selector：解决 BIO 的阻塞问题
 */
public class NioSocketServer {


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

        // 打开 Selector(多路复用器) 处理 Channel，即创建 epoll
        Selector selector = Selector.open();

        // 将 ServerSocketChannel 注册到 Selector上，并且 Selector 对客户端 accept 连接操作感兴趣
        SelectionKey selectionKey = serverSocket.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("服务启动成功！");

        while (true) {
            // 阻塞等待需要处理的事件发生
            selector.select();

            // 获取 selector 中注册的全部事件的 SelectionKey 实例
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            // 遍历 selectionKeys 对事件进行处理
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                // 如果是 OP_ACCEPT 事件，则进行连接获取和事件注册
                if (key.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();

                    socketChannel.configureBlocking(false);
                    // 这里只注册了读事件，如果需要给客户端发送数据可以注册写事件
                    socketChannel.register(selector, SelectionKey.OP_READ);

                    System.out.println("客户端连接成功！");
                }
                // 如果是 OP_READ事件，则进行读写读取和打印
                else if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();

                    ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                    int len = socketChannel.read(byteBuffer);
                    // 如果有数据，将数据打印出来
                    if (len > 0) {
                        System.out.println("接收到消息：" + new String(byteBuffer.array()));
                    } else if (len == -1) {
                        // 如果客户端断开连接，关闭 socket
                        System.out.println("客户端断开连接！");
                        socketChannel.close();
                    }
                }
                // 从事件集合里删除本次处理的key，防止下次 select 重复处理
                iterator.remove();
            }
        }
    }
}


