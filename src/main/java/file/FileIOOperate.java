package file;

import java.io.*;

/**
 * 文件读取
 * 字节
 * 字符
 * 行读取
 * <p>
 * Created by xiaolin  on 2017/8/8.
 */
public class FileIOOperate {
    private static final String FILE_PATH = "C:\\Users\\fuxin\\Desktop\\TXT_4330271978120200611495684727629";

    /**
     * 以字节为单位读取文件内容
     *
     * @param filePath：需要读取的文件路径
     */
    public static void readFileByByte(String filePath) {
        File file = new File(filePath);
        // InputStream:此抽象类是表示字节输入流的所有类的超类。
        InputStream ins = null;
        try {
            // FileInputStream:从文件系统中的某个文件中获得输入字节。
            ins = new FileInputStream(file);
            int temp;
            // read():从输入流中读取数据的下一个字节。
            while ((temp = ins.read()) != -1) {
                System.out.write(temp);
            }
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    e.getStackTrace();
                }
            }
        }
    }

    /**
     * 以字符为单位读取文件内容
     *
     * @param filePath
     */
    public static void readFileByCharacter(String filePath) {
        File file = new File(filePath);
        // FileReader:用来读取字符文件的便捷类。
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            int temp;
            while ((temp = reader.read()) != -1) {
                if (((char) temp) != '\r') {
                    System.out.print((char) temp);
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 以行为单位读取文件内容
     *
     * @param filePath
     */
    public static void readFileByLine(String filePath) {
        File file = new File(filePath);
        // BufferedReader:从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。
        BufferedReader buf = null;
        try {
            // FileReader:用来读取字符文件的便捷类。
            buf = new BufferedReader(new FileReader(file));
            // buf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String temp = null;
            while ((temp = buf.readLine()) != null) {
                System.out.println(temp);
            }
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (buf != null) {
                try {
                    buf.close();
                } catch (IOException e) {
                    e.getStackTrace();
                }
            }
        }
    }

    public static void main(String args[]) {
        readFileByByte(FILE_PATH);
//        readFileByCharacter(FILE_PATH);
//        readFileByLine(FILE_PATH);
    }
}
