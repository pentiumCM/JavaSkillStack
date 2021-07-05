package indi.pentiumcm.mq.rabbitmq.workqueue;

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
 * @describe: 消息队列——消费者
 */
public class Consumer1 {

    public static void main(String[] args) throws IOException, TimeoutException {

        String QUEUE_NAME = "work";

        // 创建连接对象
        Connection connection = RabbitMqUtil.getConnection();

        // 创建通道
        Channel channel = connection.createChannel();

        // 通道每次只能消费一个消息
        channel.basicQos(1);

        // 通道绑定对应消息队列,声明队列,如果该队列已经创建过,则不会重复创建
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);

                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //body就是从队列中获取的数据
                String message = new String(body, "UTF-8");
                System.out.println("消费者1接收：" + message);

                // 手动确认
                // 参数1：手动确认消息标识；参数2：每次确认1个
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        // 消费信息
        // autoAck：消息确认，false为手动确认机制
        channel.basicConsume(QUEUE_NAME, false, defaultConsumer);

    }
}
