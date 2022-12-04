import java.io.*;
import java.util.Scanner;

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
            Scanner scanner = new Scanner(fileIn);
            PrintStream out = new PrintStream(fileOut);
            String str;
            char c;
            fileOut.createNewFile();
            while (scanner.hasNextLine()) {
                str = scanner.nextLine();
                for (int i = 0; i < str.length(); i++) {
                    c = str.charAt(i);
                    c = (char)((c ^ 114514) + 1919810);
                    //算术运算符的优先级比逻辑运算符的优先级高
                    out.print(c);
                }
                out.print("\n");
            }
            System.out.println("加密完成！");
            out.flush();
            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
