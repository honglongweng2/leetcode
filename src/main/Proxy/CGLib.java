package Proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.time.LocalDateTime;

public class CGLib {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(StationServiceImpl.class);
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            System.out.println("运行前逻辑");
            Object res = methodProxy.invokeSuper(o, objects);
            System.out.println("运行后逻辑");
            return res;
        });
        StationServiceImpl proxy = (StationServiceImpl) enhancer.create();
        System.out.println(proxy.getClass().getName());
        proxy.ticket();
        proxy.print();
    }
}
