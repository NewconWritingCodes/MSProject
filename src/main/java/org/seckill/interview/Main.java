package org.seckill.interview;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface SayByeBye {

    public void sayByeBye();

}


interface SayGoodBye {

    public void sayGoodBye(String content);

}
  // 这两个接口类的实现类SayByeImplement


class SayByeImpl implements SayGoodBye,SayByeBye {


    public void sayGoodBye(String content) {
        System.out.println("say:"+content);
    }

    public void sayByeBye() {
        System.out.println("say:拜拜！");
    }

}
 // JDK代理类JDKProxy

class JDKProxy implements InvocationHandler {
    // 代理目标对象
    private Object target;

    // 创建目标对象的代理对象
    public Object createProxyInstance(Object target) {
        // 代理的目标对象
        this.target = target;
        // 创建代理对象
        // 1、定义代理类的类加载器
        // 2、代理类要实现的接口列表
        // 3、 指派方法调用的调用处理程序
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    /**
     * proxy:目标对象的代理实例,改代理实例不管运行多少次都是class $Proxy4 ;
     * method：对于代理实例调用接口方法的Method实例;
     * args：方法参数
     */

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
		/*
		 * 测试invoke方法的三个参数的含义
		 */
        System.out.println("proxy:"+proxy.getClass());
        System.out.println("method:"+method.getName());
        if(args!=null&&args.length>0){
            for(Object obj : args){
                System.out.println("args:"+obj);
            }
        }

        //声明返回值
        Object returnValue = null;
        beforeMethod();
        returnValue = method.invoke(target, args);
        afterMethod();
        return returnValue;
    }

    public void beforeMethod(){
        System.out.println("----方法执行之前的操作----");
    }
    public void afterMethod(){
        System.out.println("----方法执行之后的操作----");
    }
}
 // 测试类ProTest
public class Main {

    public static void main(String[] args) {

        //真是主题角色
        SayByeImpl sayByeImpl = new SayByeImpl();


//		//不使用代理的情况
//		 sayByeImpl.sayGoodBye("我要离婚！");
//		 sayByeImpl.sayByeBye();


        //代理主题角色,这里用代理主题和真实主题实现的同样接口类来接收创建的代理主题，就是创建一个目标类
        SayGoodBye sayGoodBye = (SayGoodBye) new JDKProxy().createProxyInstance(sayByeImpl);
        SayByeBye sayByeBye = (SayByeBye) new JDKProxy().createProxyInstance(sayByeImpl);
        sayGoodBye.sayGoodBye("受不了了，我要离婚！");
        System.out.println("00000000000000000000000000000000000000000000000");
        sayByeBye.sayByeBye();
    }

}


