package indi.pentiumcm.designmode.ioc;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.designmode.ioc
 * @className: Bll
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/4/18 15:53
 * @describe: IService接口的实现，具体业务类
 */

public class ServiceImlA implements IService {


    @Override
    public void say(String user) {
        System.out.println("客户端A——姓名：" + user);
    }

    @Override
    public void action(String habbit) {
        System.out.println("客户端1——兴趣爱好：" + habbit);
    }
}
