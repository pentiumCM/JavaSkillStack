package indi.pentiumcm.mq.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.mq.rabbitmq
 * @className: ConnectionUtil
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/4/28 17:37
 * @describe: RabbitMq 工具类
 */
public class RabbitMqUtil {

    private static ConnectionFactory factory;


    static {
        // static：静态代码块类加载执行，执行一次

        // 重量级资源
        // 1.创建连接工厂
        factory = new ConnectionFactory();

        // 2.在工厂对象中设置MQ的连接信息(ip,port,virtualhost,username,password)
        factory.setHost("192.168.142.63");
        // 设置端口号
        factory.setPort(5672);
        // 设置连接哪个虚拟主机
        factory.setVirtualHost("/test");
        // 设置访问虚拟主机的用户名和密码
        factory.setUsername("admin");
        factory.setPassword("admin");
    }

    /**
     * 定义提供连接对象
     *
     * @return
     */
    public static Connection getConnection() {
        try {
            // 3.通过工厂对象获取与MQ的链接
            Connection connection = factory.newConnection();
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 关闭通道和连接的工具方法
     *
     * @param channel
     * @param connection
     */
    public static void closeConnectionAndChanel(Channel channel, Connection connection) {

        try {
            if (channel != null) {
                channel.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
