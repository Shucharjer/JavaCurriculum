import java.sql.*;
import java.util.Scanner;
public class Actions {
    public static class InputScanner {
        //为了不重复实例化scanner读取输入而写的静态类
        //等想起来缓冲区的时候已经写好了不想改（
        public final static Scanner scanner = new Scanner(System.in);
        public static int getInt() {
            String input = scanner.nextLine();
            int result = Integer.parseInt(input);
            if (result > 0) {
                return result;
            }
            else {
                System.out.println("非法的输入！请重试！");
                return getInt();
            }
        }
        public static String getString() {
            return scanner.nextLine();
        }
    }
    public static class DatabaseAction {
        private static Connection connection = null;
        private static Statement statement = null;
        private static ResultSet resultSet = null;
        public static void connect() {
            String url = "jdbc:mysql://localhost:3306/studentInfo?useSSL=false&serverTimezone=UTC";
            String user = "root";
            String pass = "root";
            //使用的是MySQL8.0.16，部分内容略有变动
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, user, pass);
                statement = connection.createStatement();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        public static void close() {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        public static void add() {
            connect();
            System.out.println("请输入需要增加的学生的学号：");
            int id = InputScanner.getInt();
            System.out.println("请输入需要增加的学生的姓名：");
            String name = InputScanner.getString();
            System.out.println("请输入需要增加的学生的性别：");
            String gender = InputScanner.getString();
            System.out.println("请输入需要增加的学生的主修：");
            String major = InputScanner.getString();
            String sql = "insert into info values (" + id + ", '" + name + "', '" + gender + "', '" + major + "');";
            try {
                int flag = statement.executeUpdate(sql);
                if (flag > 0) {
                    System.out.println("该学生信息已添加！");
                }
                else {
                    System.out.println("操作有误，请重试！");
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            close();
        }
        public static void delete() {
            connect();
            System.out.println("请输入要删除学生信息的学号：");
            int id = InputScanner.getInt();
            String sql = "delete from info where id = '" + id + "';";
            try {
                statement = connection.createStatement();
                int flag = statement.executeUpdate(sql);
                if (flag > 0 ) {
                    System.out.println("该学生信息已删除！");
                }
                else {
                    System.out.println("操作有误，请重试！");
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            close();
        }
        public static void update() {
            connect();
            System.out.println("请输入需要更改信息的学生的学号：");
            int idOri = InputScanner.getInt();
            System.out.println("请输入需要更改信息的内容：");
            System.out.println("\"1\"学号");
            System.out.println("\"2\"姓名");
            System.out.println("\"3\"性别");
            System.out.println("\"4\"主修");
            System.out.println("输入其它内容则返回上一级");
            String postChoose = InputScanner.getString();
            int choose = 0;
            choose = Integer.parseInt(postChoose);
            if (choose < 1 || choose > 4) {
                return;
            }
            String sql;
            switch (choose) {
                case 1:
                    System.out.println("请输入学号：");
                    int id = InputScanner.getInt();
                    sql = "update info set id = '" + id + "' where id = '" + idOri + "';";
                    break;
                case 2:
                    System.out.println("请输入姓名：");
                    String name = InputScanner.getString();
                    sql = "update info set name = '" + name + "' where id = '" + idOri + "';";
                    break;
                case 3:
                    //真的存在学生更改性别的情况吗（雾
                    System.out.println("请输入性别：");
                    String gender = InputScanner.getString();
                    sql = "update info set gender = '" + gender + "' where id = '" + idOri + "';";
                    break;
                default:
                    System.out.println("请输入主修：");
                    String major = InputScanner.getString();
                    sql = "update info set major = '" + major + "' where id = '" + idOri + "';";
                    break;
            }
            try {
                statement = connection.createStatement();
                int flag = statement.executeUpdate(sql);
                if (flag > 0) {
                    System.out.println("该学生信息已更改！");
                }
                else {
                    System.out.println("操作有误！请重试！");
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            close();
        }
        public static void find() {
            connect();
            String sql;
            System.out.println("请输入查询方式：");
            System.out.println("\"1\":学号");
            System.out.println("\"2\":姓名");
            System.out.println("\"3\":所有学生");
            System.out.println("输入其它内容则返回上一级");
            String postChoose = InputScanner.getString();
            int choose = 0;
            choose = Integer.parseInt(postChoose);
            if (choose != 1 && choose != 2 && choose != 3) {
                return;
            }
            switch (choose) {
                case 1:
                    System.out.println("请输入学号：");
                    int id = InputScanner.getInt();
                    sql = "select * from info where id = '" + id + "';";
                    break;
                case 2:
                    System.out.println("请输入姓名：");
                    String name = InputScanner.getString();
                    sql = "select * from info where name = '" + name + "';";
                    break;
                default:
                    sql = "select * from info;";
                    break;
            }
            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    System.out.println("学号：" + resultSet.getInt("id") + "\t");
                    System.out.println("姓名：" + resultSet.getString("name") + "\t");
                    System.out.println("性别：" + resultSet.getString("gender") + "\t");
                    System.out.println("主修：" + resultSet.getString("major") + "\t");
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            close();
        }
    }
}
