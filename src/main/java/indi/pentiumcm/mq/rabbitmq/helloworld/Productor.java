package indi.pentiumcm.mq.rabbitmq.helloworld;

import com.rabbitmq.client.*;
import indi.pentiumcm.mq.rabbitmq.RabbitMqUtil;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.mq.rabbitmq
 * @className: SendMsg
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/4/28 19:49
 * @describe: 消息队列——生产者
 * 直连方式：一个生产者对应一个消费者
 */
public class Productor {

    public static void main(String[] args) throws Exception {

        String QUEUE_NAME = "hello";

        // 1. 创建连接
        Connection connection = RabbitMqUtil.getConnection();

        // 2. 获取连接中的通道
        Channel channel = connection.createChannel();

        // 3. 声明队列,声明队列,如果该队列已经创建过,则不会重复创建
        // 参数1：定义的队列名称
        // 参数2：队列中的数据是否持久化（如果选择了持久化）
        // 参数3: 是否独占（当前队列是否为当前连接私有）
        // 参数4：自动删除（当此队列的连接数为0时，此队列会销毁（无论队列中是否还有数据））
        // 参数5：设置当前队列的参数
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        String msg = "I am Java!";

        // 4. 发布消息
        // 参数1：交换机名称，如果直接发送信息到队列，则交换机名称为""
        // 参数2：目标队列名称
        // 参数3：设置当前这条消息的属性（设置过期时间 10）
        // 参数4：消息的内容
        channel.basicPublish("", QUEUE_NAME, false, null, msg.getBytes());
        System.out.println("生产者发送：" + msg);

/*        channel.close();
        connection.close();*/
        RabbitMqUtil.closeConnectionAndChanel(channel, connection);

    }

}
