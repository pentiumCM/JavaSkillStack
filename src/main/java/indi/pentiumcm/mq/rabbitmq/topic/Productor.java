package indi.pentiumcm.mq.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import indi.pentiumcm.mq.rabbitmq.RabbitMqUtil;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.mq.rabbitmq
 * @className: Productor
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/4/28 19:49
 * @describe: 消息队列——生产者
 * topic：动态路由模式，通过通配符实现（交换机topic类型 + 两种通配符）
 */
public class Productor {

    public static void main(String[] args) throws Exception {

        String exchangeType = "topic";
        String exchangeName = "topics";
        String routingKey = "user.save.findAll";

        // 1. 创建连接
        Connection connection = RabbitMqUtil.getConnection();

        // 2. 获取连接中的通道
        Channel channel = connection.createChannel();

        // 3. 声明交换机
        // 参数1：交换机的名称；参数2：交换机的类型,direct 路由模式
        channel.exchangeDeclare(exchangeName, exchangeType);

        String msg = "I am Java!";

        // 4. 发布消息（生产者将消息发给交换机）
        // 参数1：交换机名称，如果直接发送信息到队列，则交换机名称为""
        // 参数2：目标队列名称
        // 参数3：设置当前这条消息的属性（设置过期时间 10）
        // 参数4：消息的内容
        channel.basicPublish(exchangeName, routingKey, false, null, (routingKey + msg).getBytes());
        System.out.println("生产者发送：" + msg);

        RabbitMqUtil.closeConnectionAndChanel(channel, connection);
    }

}
