package indi.pentiumcm.designmode.ioc;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.designmode.ioc
 * @className: IOCContainer
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/4/18 15:56
 * @describe: IOC容器，为应用类Application注入业务接口实现
 */

public class IOCContainer {

    public static void main(String[] args) {
        //实例化 IService 接口的实现
        ServiceImlA serviceIml = new ServiceImlA();

        //为应用类注入实现
        Application app = new Application(serviceIml);

        //调用应用类
        app.Run();
    }
}
