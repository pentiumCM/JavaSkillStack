package indi.pentiumcm.mq.rabbitmq.topic;

import com.rabbitmq.client.*;
import indi.pentiumcm.mq.rabbitmq.RabbitMqUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.mq.rabbitmq
 * @className: ReceiveMsg
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/4/28 20:12
 * @describe: 消息队列——消费者——工作模型
 */
public class Consumer1 {

    public static void main(String[] args) throws IOException, TimeoutException {

        String exchangeType = "topic";
        String exchangeName = "topics";
        String routingKey = "user.#";

        // 创建连接对象
        Connection connection = RabbitMqUtil.getConnection();

        // 创建通道
        Channel channel = connection.createChannel();

        // 声明交换机以及交换机类型
        channel.exchangeDeclare(exchangeName, exchangeType);

        // 临时队列
        String queueName = channel.queueDeclare().getQueue();

        // 绑定交换机和队列
        channel.queueBind(queueName, exchangeName, routingKey);

        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                //body就是从队列中获取的数据
                String message = new String(body, "UTF-8");
                System.out.println("消费者1接收：" + message);
            }
        };

        // 消费信息
        // autoAck：消息确认，false为手动确认机制
        channel.basicConsume(queueName, true, defaultConsumer);

    }
}
