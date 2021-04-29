package indi.pentiumcm.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.rabbitmq
 * @className: ReceiveMsg
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/4/28 20:12
 * @describe: 消息队列——消费者
 */
public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {

        // 创建连接对象
        Connection connection = RabbitMqUtil.getConnection();

        // 创建通道
        Channel channel = connection.createChannel();

        // 通道绑定对应消息队列,声明队列,如果该队列已经创建过,则不会重复创建
        channel.queueDeclare("hello", false, false, false, null);

        // 消费信息
        channel.basicConsume("hello", true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);

                //body就是从队列中获取的数据
                String msg = new String(body);
                System.out.println("消费者接收：" + msg);
            }
        });

    }
}
