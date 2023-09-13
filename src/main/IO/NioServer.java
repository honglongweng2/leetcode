package IO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class NioServer {
    public static void main(String[] args) throws IOException {
        // 1. 绑定端口，开启服务
        final ServerSocketChannel channel = ServerSocketChannel.open();
        final Selector selector = Selector.open();
        channel.bind(new InetSocketAddress(8080));
        channel.configureBlocking(false);
        // 2. 在selector上注册channel
        channel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器已启动");
        while (true) {
            // 3. 阻塞
            final int select = selector.select();
            if (select < 1) {
                System.out.println("没有连接");
                continue;
            }
            // 每个注册的channel都会对应一个SelectionKey
            final Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();
            while (selectionKeys.hasNext()) {
                final SelectionKey selectionKey = selectionKeys.next();
                if (selectionKey.isAcceptable()) {
                    // 事件1：accept
                    // 若没有对应的客户端channel，accept()返回null
                    final SocketChannel newSocketChannel = channel.accept();
                    System.out.println("收到客户端的连接请求：" + newSocketChannel.getRemoteAddress());
                    newSocketChannel.configureBlocking(false);
                    // 将新的channel（客户端）注册到selector上，是一个读事件
                    newSocketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(512));
                } else if (selectionKey.isReadable()) {
                    // 事件2：read
                    handleKey(selectionKey);
                } else if (selectionKey.isWritable()) {
                    // 事件3: write
                } else {
                    // 事件4: connect
                }
                // 处理过的selectionKey需要清掉，避免重复执行
                selectionKeys.remove();
            }
        }
    }

    private static void handleKey(SelectionKey selectionKey) throws IOException{
        // 从之前注册的读事件中获取channel和ByteBuffer容器。attachment是不会清空的。
        final SocketChannel channel = (SocketChannel) selectionKey.channel();
        ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
        buffer.clear();
        channel.read(buffer);
        System.out.println("读到的消息为：" + new String(buffer.array()));
        String response = "服务器收到的消息为：" + new String(buffer.array());
        channel.write(ByteBuffer.wrap(response.getBytes(StandardCharsets.UTF_8)));
    }
}
