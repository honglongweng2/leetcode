package IO.ZeroCopy;

import java.io.*;
import java.nio.channels.FileChannel;

public class FileTransferDemo {

    // 不仅拷贝硬盘文件，还可以作为底层硬件之间的拷贝实现。例，kafka用这种方式直接把消息从硬盘拷贝到网卡
    private static void moveFileByChannel(File source, File target) {
        try {
            final FileInputStream sourceFis = new FileInputStream(source);
            final FileChannel sourceChannel = sourceFis.getChannel();
            final FileOutputStream targetFos = new FileOutputStream(target);
            final FileChannel targetChannel = targetFos.getChannel();
            sourceChannel.transferTo(0, source.length(), targetChannel);
            sourceFis.close();
            targetFos.close();
        } catch (IOException ignored) {
        }
    }
}
