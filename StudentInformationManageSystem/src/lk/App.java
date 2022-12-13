package lk;

import java.sql.*;
import java.util.Scanner;

public class App {
    static Scanner scanner = new Scanner(System.in);
    static Scanner scanner1 = new Scanner(System.in);
    static Scanner scanner2 = new Scanner(System.in);
    static Scanner scanner3 = new Scanner(System.in);
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/test";
        String user="root";
        String pwd="123456";
        Connection connection = DriverManager.getConnection(url, user, pwd);
        if(connection==null){
            System.out.println("连接失败");
        }else{
            System.out.println("连接成功");
        }
        Statement statement = connection.createStatement();
        flag:
        while (true) {
            System.out.println("-----学生管理系统----");
            System.out.println("请选择需要功能（填写数字）：");
            System.out.println("\"1\":查询学生");
            System.out.println("\"2\":增加学生");
            System.out.println("\"3\":删除学生");
            System.out.println("\"4\":更改信息");
            System.out.println("\"5\":查询学生系统所有学生");
            System.out.println("\"6\":退出系统");
            Scanner scanner = new Scanner(System.in);
            int select = scanner.nextInt();
            switch (select) {
                case 1:
                    refer(statement);
                    break;
                case 2:
                    add(statement);
                    break;
                case 3:
                    remove(statement);
                    break;
                case 4:
                    update(statement);
                    break;
                case 5:
                    lookAll(statement);
                    break;
                case 6:
                    System.out.println("退出系统成功");
                    break flag;
                default:
                    System.out.println("请输入正确数字");
                    break;
            }

        }
        connection.close();

    }

    private static void lookAll(Statement statement) throws SQLException {
        String sql ="select * from studentchart";
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            System.out.print("学号："+resultSet.getString("id")+"\t");
            System.out.print("姓名："+resultSet.getString("name")+"\t");
            System.out.print("性别："+resultSet.getString("gender")+"\t");
            System.out.println("专业："+resultSet.getString("major"));
        }
    }

    private static void update(Statement statement) throws SQLException {
        System.out.println("请输入要更改学生信息的学号:");
        int id = scanner.nextInt();
        System.out.println("请输入姓名：");
        String name = scanner1.nextLine();
        System.out.println("请输入性别：");
        String gender = scanner2.nextLine();
        System.out.println("请输入专业：");
        String major = scanner3.nextLine();
        String sql=String.format("update studentchart set name='%s',gender='%s',major='%s' where id=%d",name,gender,major,id);
        if(statement.executeUpdate(sql)>=1){
            System.out.println("更改成功");
        }else {
            System.out.println("不存在要更改信息的学生若要添加，请输入2");
        }
    }

    private static void remove(Statement statement) throws SQLException {
        System.out.println("请输入您要删除学生信息的学号:");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        String sql="delete from studentchart where id="+id;
        if(statement.executeUpdate(sql)>=1){
            System.out.println("删除成功");
        }else {
            System.out.println("系统中不存在要删除的学生");
        }
    }


    private static void add(Statement statement) {
        System.out.println("请输入学号：");
        int id = scanner.nextInt();
        System.out.println("请输入姓名：");
        String name = scanner1.nextLine();
        System.out.println("请输入性别：");
        String gender = scanner2.nextLine();
        System.out.println("请输入专业：");
        String major = scanner3.nextLine();
        String sql=String.format("insert into studentchart values(%d,'%s','%s','%s')",id,name,gender,major);
        try {
            statement.executeUpdate(sql);
            System.out.println("添加成功");
        } catch (SQLException throwables) {
            System.out.println("添加失败，当前学号已存在于系统中具体信息如下（如需更改请输入4）：");
            sql = String.format("select * from studentchart where id=%d", id);
            ResultSet resultSet = null;
            try {
                resultSet = statement.executeQuery(sql);
                if(resultSet.next()){
                    System.out.print("学号：" + resultSet.getString("id") + "\t");
                    System.out.print("姓名：" + resultSet.getString("name") + "\t");
                    System.out.print("性别：" + resultSet.getString("gender") + "\t");
                    System.out.println("专业：" + resultSet.getString("major"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void refer(Statement statement) throws SQLException {
        System.out.println("请输入你的信息：");
        Scanner scanner = new Scanner(System.in);
        System.out.println("学号：");
        int id = scanner.nextInt();
        String sql = String.format("select * from studentchart where id=%d", id);
        ResultSet resultSet = statement.executeQuery(sql);
        if(resultSet.next()) {
            System.out.print("学号：" + resultSet.getString("id") + "\t");
            System.out.print("姓名：" + resultSet.getString("name") + "\t");
            System.out.print("性别：" + resultSet.getString("gender") + "\t");
            System.out.println("专业：" + resultSet.getString("major"));
        }else{
            System.out.println("不存在该学号学生若需添加请输入2");
        }
    }
}
