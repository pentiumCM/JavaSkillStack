package indi.pentiumcm.designmode.ioc;


/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.designmode.ioc
 * @className: Application
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/4/18 15:54
 * @describe: 构造依赖注入，从外部注入实现类 / 当前类为应用类
 */

public class Application {

    private IService iService = null;

    public Application(IService iService) {
        this.iService = iService;
    }

    public void Run() {

        iService.say("陈敏");
        iService.action("篮球");
    }


    public static void main(String[] args) {
        //实例化 IService 接口的实现
        ServiceImlA serviceImlA = new ServiceImlA();
        ServiceImlB serviceImlB = new ServiceImlB();

        //为应用类注入实现
        Application app1 = new Application(serviceImlA);
        Application app2 = new Application(serviceImlB);

        //调用应用类
        app1.Run();

        app2.Run();
    }

}

