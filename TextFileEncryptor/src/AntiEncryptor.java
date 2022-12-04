import java.io.*;
import java.util.Scanner;

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
            Scanner scanner = new Scanner(fileIn);
            PrintStream out = new PrintStream(fileOut);
            String str;
            char c;
            fileOut.createNewFile();
            while (scanner.hasNextLine()) {
                str = scanner.nextLine();
                for (int i = 0; i < str.length(); i++) {
                    c = str.charAt(i);
                    c = (char)(c - 1919810 ^ 114514);
                    out.print(c);
                }
                out.print('\n');
            }
            System.out.println(fileIn + "解密完成！");
            out.flush();
            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
