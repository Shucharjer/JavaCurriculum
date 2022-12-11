package lk;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("----加密解密器----");
        System.out.println("请选择需要的功能");
        System.out.println("\"1\":加密");
        System.out.println("\"2\":解密");
        System.out.println("输入其他值为退出系统");
        Scanner scanner=new Scanner(System.in);
        Scanner scanner1=new Scanner(System.in);
        Scanner scanner2=new Scanner(System.in);
        switch (scanner.nextInt()){
            case 2:{
                System.out.println("请输入需要加密的文件路径");
                String FileInPath = scanner1.nextLine();
                System.out.println("请输入生成文件的路径");
                String FileOutPath= scanner2.nextLine();
                fileDecode(FileInPath,FileOutPath);
            } break;
            case 1:{
                System.out.println("请输入要解密的文件路径");
                String FileInPath=scanner1.nextLine();
                System.out.println("请输入解密后文件路径");
                String FileOutPath=scanner2.nextLine();
                fileEncrypt(FileInPath,FileOutPath);
            } break;
        }
    }

    private static void fileDecode(String FileInPath,String FileOutPath) throws IOException {
        FileInputStream FileIn=new FileInputStream(FileInPath);
        FileOutputStream FileOut=new FileOutputStream(FileOutPath);
        int i;
        while((i=FileIn.read())!=-1){
            FileOut.write(i-12345);
        }
        FileIn.close();
        FileOut.close();

    }

    private static void fileEncrypt(String FileInPath,String FileOutPath) throws IOException {
        FileInputStream FileIn=new FileInputStream(FileInPath);
        FileOutputStream FileOut=new FileOutputStream(FileOutPath);
        int i;
        while((i=FileIn.read())!=-1){
            FileOut.write(i+12345);
        }
        FileIn.close();
        FileOut.close();
    }
}
