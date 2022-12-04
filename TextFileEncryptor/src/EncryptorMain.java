import java.io.File;
import java.util.Scanner;

public class EncryptorMain {
    public static void main(String[] args) {
        System.out.println("感谢使用本加密器！");
        for (;;) {
            System.out.println("输入1加密，输入2解密，输入其它内容则退出。");
            String usage = getUsage();
            if (usage == null) {
                return;
            }
            else {
                System.out.println("请输入需要加密的文本文件路径：");
                if (usage.equals("1")) {
                    Encryptor encryptor = new Encryptor();
                    encryptor.start();
                    System.out.println("加密中……");
                }
                else {
                    AntiEncryptor antiEncryptor = new AntiEncryptor();
                    antiEncryptor.start();
                    System.out.println("解密中……");
                }
                System.out.println("完成时会提示，请耐心等待");
            }

        }
    }
    private static String getUsage() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("1") || input.equals("2")) {
            return input;
        } else {
            return null;
        }
    }
    public static String getPath() {
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean flag;
        while (true) {
            input = scanner.nextLine();
            input = input.replace("\\","\\\\");
            if (new File(input).exists()) {
                break;
            }
            System.out.println("寻找的文件不存在！请检查后重试。");
        }
        return input;
    }
}
