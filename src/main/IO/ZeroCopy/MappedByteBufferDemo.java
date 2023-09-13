package IO.ZeroCopy;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedByteBufferDemo {

    // 文件映射内存
    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("MappedFile.txt", "rw");
        // 获取通道
        FileChannel channel = file.getChannel();
        /**
         * 参数1 读写模式
         * 参数2 直接修改的起始位置
         * 参数3 映射到内存的大小，也既将文件的多大映射到内存
         */
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 1024);

        buffer.put(0, (byte) 'H');
        buffer.put(3, (byte) '9');

        file.close();
        System.out.println("修改成功");
    }
}
