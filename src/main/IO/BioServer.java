package IO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class BioServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        System.out.println("服务器启动");
        while(true) {
            // 阻塞点1
            Socket socket = server.accept();
            System.out.println("接收请求");
            new Thread(() -> {
                try {
                    InputStream in = socket.getInputStream();
                    byte[] buffer = new byte[1024];
                    // 阻塞点2
                    int len = in.read(buffer);
                    if (len > 0) {
                        OutputStream out = socket.getOutputStream();
                        out.write(("收到： " + new String(buffer)).getBytes(StandardCharsets.UTF_8));
                        out.close();
                    }
                    in.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
