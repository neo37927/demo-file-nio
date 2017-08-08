import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by xiaolin  on 2017/8/8.
 */
public class Hello {
    public static void main(String[] args) {
        try {
//            RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
//            FileInputStream inputStream = new FileInputStream("C:\\Users\\fuxin\\Desktop\\TXT_4330271978120200611495684727629");
            FileInputStream inputStream = new FileInputStream("C:\\Users\\fuxin\\Desktop\\TXT_4330271978120200611495684727629");
            FileChannel inChannel = inputStream.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(1024);

            int bytesRead = inChannel.read(buf);
            while (bytesRead != -1) {

                System.out.println("Read " + bytesRead);
                buf.flip();

                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }

                buf.clear();
                bytesRead = inChannel.read(buf);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
