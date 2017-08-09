import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;

/**
 * Created by xiaolin  on 2017/8/9.
 */
public class CommonFileTest {
    public static void main(String[] args) {
        String FILE_PATH = "C:\\Users\\fuxin\\Desktop\\TXT_4330271978120200611495684727629";
//        String FILE_PATH = "C:\\Users\\fuxin\\Desktop\\TXT_4502211978112239361498748871851";
        String[] FILE_PATH_ARRAY = {"C:\\Users\\fuxin\\Desktop\\TXT_4330271978120200611495684727629",
                "C:\\Users\\fuxin\\Desktop\\TXT_4104231986040473281500614898057",
                "C:\\Users\\fuxin\\Desktop\\TXT_3310811987112351591493507683130",

        };
        try {
            for (int i = 0; i <= FILE_PATH_ARRAY.length; i++) {
                Long beginNanoTimeUtil = System.nanoTime();
                getStringByCommonUtils(FILE_PATH);
                System.out.println(System.nanoTime() - beginNanoTimeUtil);

                Long beginNanoTimeNIO = System.nanoTime();
                getStringByNIO(FILE_PATH);
                System.out.println(System.nanoTime() - beginNanoTimeNIO);


                Long beginNanoTimeIO = System.nanoTime();
                readFile(FILE_PATH);
                System.out.println(System.nanoTime() - beginNanoTimeIO);

                System.out.println("______________________________");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String getStringByCommonUtils(String fileName) throws IOException {
        File file = new File(fileName);
        return FileUtils.readFileToString(file, "UTF-8");
    }

    private static String getStringByNIO(String fileName) throws IOException {
        StringBuilder builder = new StringBuilder();
        int i = 1;
        // 获取源文件和目标文件的输入输出流
        FileInputStream in = new FileInputStream(fileName);
        // 获取输入输出通道
        FileChannel fcIn = in.getChannel();
//        ByteBuffer buffer = MappedByteBuffer.allocate(1024 * 4);
        ByteBuffer buffer = ByteBuffer.allocate(1024 * 4);
        while (true) {
            // clear方法重设缓冲区，使它可以接受读入的数据
            buffer.clear();
            // 从输入通道中将数据读到缓冲区
            int r = fcIn.read(buffer);
            if (r == -1) {
//                System.out.println("NIO 读取次数：" + i);
                return builder.toString();
            }
            // flip方法让缓冲区可以将新读入的数据写入另一个通道
            buffer.flip();
            i++;
            builder.append(buffer);
        }
    }

    public static String readFile(String path) throws IOException {

        FileReader inputStream = null;
        try {
            inputStream = new FileReader(path);
            StringBuilder builder = new StringBuilder();
            char[] chars = new char[1024 * 5];
            int length;
            while ((length = inputStream.read(chars)) > 0) {
                builder.append(new String(chars, 0, length));
            }
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
