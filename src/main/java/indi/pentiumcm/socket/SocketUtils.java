package indi.pentiumcm.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.socket
 * @className: SocketUtils
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/3/23 21:45
 * @describe: socket 工具类
 */
public class SocketUtils {

    /**
     * 饿汉式 - 实现单例模式
     */
    private static SocketUtils instance = new SocketUtils();

    private SocketUtils() {
    }

    public static SocketUtils getInstance() {
        if (instance == null) {
            instance = new SocketUtils();
        }
        return instance;
    }


    private static final String SERVERIP = "127.0.0.1";
    private static final int SERVERPORT = 9001;


    // 线程池
    private static final ExecutorService ThreadPool = Executors.newCachedThreadPool();

    /**
     * 发送Socket消息
     *
     * @param msg      消息
     * @param callback 回调
     */
    public void sendSocketMsg(final byte[] msg, final SocketUtilCallback callback) {
        ThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // 建立Socket连接
                    Socket socket = new Socket(SERVERIP, SERVERPORT);
                    socket.setSoTimeout(1000 * 10);
                    // 写消息
                    OutputStream out = socket.getOutputStream();
                    out.write(msg);
                    out.flush();
                    // 读取消息返回
                    byte[] readBuffer = new byte[1024];
                    InputStream in = socket.getInputStream();
                    int size = in.read(readBuffer);
                    byte[] readBytes = null;
                    if (size != -1) {
                        readBytes = new byte[size];
                        System.arraycopy(readBuffer, 0, readBytes, 0, size);
                    }
                    // 关闭流和Socket连接
                    in.close();
                    out.close();
                    socket.close();
                    // 执行发送成功回调
                    if (callback != null && readBytes != null) {
                        callback.onSuccess(readBytes);
                    }
                } catch (IOException e) {
                    // 执行发送失败回调
                    if (callback != null) {
                        callback.onError(e);
                    }
                }
            }
        });
    }

    /**
     * 回调
     */
    public interface SocketUtilCallback {
        void onSuccess(byte[] result);

        void onError(Exception e);
    }


    public static void main(String[] args) {

        SocketUtils socketUtils = SocketUtils.getInstance();

        String str = "hello";

        byte[] bytes = str.getBytes();
        socketUtils.sendSocketMsg(bytes, new SocketUtilCallback() {
            @Override
            public void onSuccess(byte[] result) {
                System.out.println("");
            }

            @Override
            public void onError(Exception e) {
                System.out.println("");
            }
        });

    }
}
