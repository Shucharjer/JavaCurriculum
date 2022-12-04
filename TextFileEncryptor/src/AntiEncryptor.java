import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class AntiEncryptor extends Thread {
    private final String fileInPath;
    public AntiEncryptor() {
        fileInPath = EncryptorMain.getPath();
    }
    public void run() {
        File fileIn = new File(fileInPath);
        String fileOutPath = fileInPath.replace(".txt", "AntiEn.txt");
        File fileOut = new File(fileOutPath);
        try {
            InputStream in = new FileInputStream(fileIn);
            PrintStream out = new PrintStream(fileOut);
            int tempByte;
            fileOut.createNewFile();
            while ((tempByte = in.read()) != -1) {
                tempByte = (tempByte - 1919810) ^ 114514;
                //这是一个一个一个解密啊
                out.print((char)tempByte);
            }
            System.out.println("解密完成！");
            out.close();
            in.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
