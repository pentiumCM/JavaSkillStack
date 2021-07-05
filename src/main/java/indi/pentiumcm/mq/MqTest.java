package indi.pentiumcm.mq;

import cn.hutool.core.bean.BeanUtil;
import indi.pentiumcm.entity.TestEntity;
import org.apache.poi.ss.formula.functions.T;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.mq
 * @className: MqTest
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/6/8 9:26
 * @describe:
 */
public class MqTest {

    public static BlockingQueue blockingQueue = new LinkedBlockingQueue();

    public static void main(String[] args) {

        TestEntity entity = new TestEntity();
        entity.setName("cm");
        entity.setAge(20);

        try {
            blockingQueue.put(entity);

            TestEntity sec = new TestEntity();
            BeanUtil.copyProperties(entity, sec);
            sec.setAge(21);
            blockingQueue.put(sec);

            System.out.println("hello");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
