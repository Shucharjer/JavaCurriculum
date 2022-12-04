package com.lk.demo;

import java.util.*;
import java.util.function.Consumer;


public class App {
    public static ArrayList<Student> studentArrayList=new ArrayList<Student>();
    public static void main(String[] args) {
        //TODO:使用数据库，目前的数据都是存在内存里的
        studentArrayList.add(new Student(123,"张三","男","计算机"));
        flog:
        while (true) {
            System.out.println("-----学生管理系统----");
            System.out.println("请选择需要功能（填写数字）：");
            System.out.println("\"1\":查询学生");
            System.out.println("\"2\":增加学生");
            System.out.println("\"3\":删除学生");
            System.out.println("\"4\":更改信息");
            System.out.println("\"5\":退出系统");
            Scanner scanner = new Scanner(System.in);
            int select = scanner.nextInt();
            switch (select) {
                case 1:
                    refer();
                    break;
                case 2:
                    add();
                    break;
                case 3:
                    remove();
                    break;
                case 4:
                    set();
                    break;
                case 5:
                    System.out.println("退出系统成功");
                    break flog;
                default:
                    System.out.println("请输入正确数字");
                    break;
            }

        }

    }

    private static void set() {
        System.out.println("请输入要更改学生信息的学号:");
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        Scanner scanner3 = new Scanner(System.in);
        int id = scanner.nextInt();
        if(getstudent(id)!=null){
            System.out.println("请输入姓名：");
            String name = scanner1.nextLine();
            System.out.println("请输入性别：");
            String gender = scanner2.nextLine();
            System.out.println("请输入专业：");
            String major = scanner3.nextLine();
            studentArrayList.remove(getstudent(id));
            studentArrayList.add(new Student(id,name,gender,major));
            System.out.println("更改成功");
            return;
        }
        System.out.println("不存在要更改信息学生的学号");
    }

    private static void remove() {
        System.out.println("请输入您要删除学生信息的学号:");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        if(getstudent(id)!=null){
            studentArrayList.remove(getstudent(id));
            System.out.println("删除成功");
            return;
        }
        System.out.println("不存在要删除学生的学号");

    }

    private static void add() {
        System.out.println("请输入学号：");
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        Scanner scanner3 = new Scanner(System.in);
        int id = scanner.nextInt();
        if(ifhave(id)){
            return;
        }
        System.out.println("请输入姓名：");
        String name = scanner1.nextLine();
        System.out.println("请输入性别：");
        String gender = scanner2.nextLine();
        System.out.println("请输入专业：");
        String major = scanner3.nextLine();
        studentArrayList.add(new Student(id,name,gender,major));
        System.out.println("添加成功");
    }

    private static void refer() {
        System.out.println("请输入你的信息：");
        Scanner scanner = new Scanner(System.in);
        System.out.println("学号：");
        int id = scanner.nextInt();
        if(!ifhave(id)){
            System.out.println("您不在学生管理系统，如需添加学生信息请输入2");
        }
    }
    public static boolean ifhave(int id){
        Student student;
        final boolean[] a = {false};
        studentArrayList.forEach(new Consumer<Student>() {
            @Override
            public void accept(Student student) {
                if(student.getId()==id){
                    a[0] = true;
                    System.out.println("您存在于学生管理系统信息如下，如需更改信息请输入4");
                    System.out.println(student);
                }
            }
        });
        return a[0];

    }
    public static Student getstudent(int id){
        final Student[] students = new Student[1];
        studentArrayList.forEach(new Consumer<Student>() {
            @Override
            public void accept(Student student) {
                if(student.getId()==id){
                       students[0] =student;
                }
            }
        });
        return students[0];
    }
}
