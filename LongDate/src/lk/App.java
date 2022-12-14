package lk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("----万年历----");
        System.out.println("请选择您需要的功能（请输入数字）：");
        System.out.println("\"1\":查询当前时间信息");
        System.out.println("\"2\":查询指定日期相关信息");
        System.out.println("\"3\":天数计算器");
        System.out.println("输入其余数字为退出系统");
        Scanner scanner=new Scanner(System.in);
        int select = scanner.nextInt();
        switch (select){
            case 1: currentMessage();
                break;
            case 2: customMessage();
                break;
            case 3:  dayCalculator();
                break;
        }
    }




    private static void dayCalculator() {
        SimpleDateFormat simpleFormatter=new SimpleDateFormat("yyyy年MM月dd日");
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入起始日期（格式为某年某月某日）：");
        Date startTime = null;
        Date endTime = null;
        try {
            startTime=simpleFormatter.parse(scanner.nextLine());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("请输入截至时间（格式为某年某月某日）：");
        try {
            endTime=simpleFormatter.parse(scanner.nextLine());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int listTime= (int) ((endTime.getTime()-startTime.getTime())/(1000*60*60*24));
        System.out.println("两日期相差"+listTime+"天");
    }

    private static void customMessage() {
        SimpleDateFormat simpleFormatter=new SimpleDateFormat("yyyy年MM月dd日");
        System.out.println("请输入您想查询的日期（格式为某年某月某日）：");
        Scanner scanner = new Scanner(System.in);
        String string=scanner.nextLine();
        Date date = null;
        try {
            date=simpleFormatter.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int dayofweek=instance.get(Calendar.DAY_OF_WEEK);
        int dayofyear=instance.get(Calendar.DAY_OF_YEAR);
        int dayofmonth=instance.get(Calendar.DAY_OF_MONTH);
        int weekofyear=instance.get(Calendar.WEEK_OF_YEAR);
        System.out.println("该天为："+getDayOfWeek(dayofweek));
        System.out.println("该天为当年的第"+dayofyear+"天");
        System.out.println("该天为当月第"+dayofmonth);
        System.out.println("该周为当年第"+weekofyear+"周");
    }

    private static void currentMessage() {
        Calendar instance = Calendar.getInstance();
        int year=instance.get(Calendar.YEAR);
        int month=instance.get(Calendar.MONTH)+1;
        int date=instance.get(Calendar.DATE);
        int dayofweek=instance.get(Calendar.DAY_OF_WEEK);
        System.out.println("您所在时区当前时间为:"+year+"年"+month+"月"+date+"日"+getDayOfWeek(dayofweek));
    }

    private static String getDayOfWeek(int dayofweek) {
        String[] strings = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        return strings[dayofweek-1];
    }
}
