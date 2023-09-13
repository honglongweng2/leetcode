package Proxy;

public class StationServiceImpl implements TicketService{
    @Override
    public void ticket() {
        System.out.println("运行原逻辑");
    }

    @Override
    public void print() {
        System.out.println("打印");
    }
}
