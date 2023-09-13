package Proxy;

import java.lang.reflect.Proxy;
import java.time.LocalDateTime;

public class JdkProxy {
    public static void main(String[] args) {
        final TicketService ticketService = new StationServiceImpl();
        TicketService instance = (TicketService) Proxy.newProxyInstance(StationServiceImpl.class.getClassLoader(),
                StationServiceImpl.class.getInterfaces(),
                (proxy, method, args1) -> {
                    System.out.println(LocalDateTime.now());
                    return method.invoke(ticketService, args1);
                });
        System.out.println(instance.getClass());
        instance.ticket();
        instance.print();
    }
}
