package indi.pentiumcm.python;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.python
 * @className: Jython_test
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/3/23 10:11
 * @describe:
 */
public class Jython_test {


    public static void main(String[] args) {

//        System.setProperty("python.home", "D:\\jython2.7.0");

        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("F:\\develop_code\\python\\machinelearn\\machinelearn\\test\\add.py");

        // 第一个参数为期望获得的函数（变量）的名字，第二个参数为期望返回的对象类型
        PyFunction pyFunction = interpreter.get("add", PyFunction.class);
        int a = 5, b = 10;
        //调用函数，如果函数需要参数，在Java中必须先将参数转化为对应的“Python类型”
        PyObject pyobj = pyFunction.__call__(new PyInteger(a), new PyInteger(b));
        System.out.println("the anwser is: " + pyobj);
    }
}


