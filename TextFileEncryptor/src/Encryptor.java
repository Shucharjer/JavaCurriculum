import java.io.*;

public class Encryptor extends Thread {
    private final String fileInPath;
    public Encryptor() {
        fileInPath = EncryptorMain.getPath();
    }
    public void run() {
        File fileIn = new File(fileInPath);
        String fileOutPath = fileInPath.replace(".txt", "En.txt");
        File fileOut = new File(fileOutPath);
        try {
            InputStream in = new FileInputStream(fileIn);
            PrintStream out = new PrintStream(fileOut);
            int tempByte;
            fileOut.createNewFile();
            while ((tempByte = in.read()) != -1) {
                tempByte = tempByte ^ 114514 + 1919810;
                //这是一个一个一个加密啊
                out.print((char)tempByte);
            }
            System.out.println("加密完成！");
            out.close();
            in.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
