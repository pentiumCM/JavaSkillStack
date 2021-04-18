package indi.pentiumcm.socket;

import cn.hutool.json.JSONObject;
import org.python.antlr.ast.Str;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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
public class SocketUtils_json {

    /**
     * 饿汉式 - 实现单例模式
     */
    private static SocketUtils_json instance = new SocketUtils_json();

    private SocketUtils_json() {
    }

    public static SocketUtils_json getInstance() {
        if (instance == null) {
            instance = new SocketUtils_json();
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
     * @param sendData 消息
     * @param callback 回调
     */
    public void sendSocketMsg(final Map sendData, final SocketUtilCallback callback) {
        ThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // 1. connect to serverSocket。建立Socket连接
                    Socket socket = new Socket(SERVERIP, SERVERPORT);
                    // 超时时间为2分钟
                    socket.setSoTimeout(1000 * 60 * 2000);

                    // 初始客户端输入参数
                    JSONObject json = new JSONObject(sendData);
                    String jsonString = json.toString();
                    byte[] msg = jsonString.getBytes();

                    // 2. send
                    // 获取输出流对象
                    OutputStream out = socket.getOutputStream();
                    out.write(msg);
                    out.flush();
                    System.out.println("传输数据完毕");
                    socket.shutdownOutput();

                    // 3. receive from server
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
                        String receive = new String(readBytes);
                        JSONObject js = new JSONObject(receive);
                        callback.onSuccess(js);
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
        void onSuccess(JSONObject result);

        void onError(Exception e);
    }


    public static void main(String[] args) {
        SocketUtils_json socketUtils = SocketUtils_json.getInstance();

        Map<String, Object> sendData = new HashMap<>();
        sendData.put("imgpath", "F:\\img");
        sendData.put("output", "F:\\test\\output");

        socketUtils.sendSocketMsg(sendData, new SocketUtilCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                System.out.println("成功1!");

                boolean status = result.getBool("status");
                String resData = result.getStr("resData");
                if (status) {
                    System.out.printf("结果：" + resData);
                }
            }

            @Override
            public void onError(Exception e) {
                System.out.println("失败1");
            }
        });


    }
}
