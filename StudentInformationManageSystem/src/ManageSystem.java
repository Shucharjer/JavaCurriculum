public class ManageSystem {
    public static void main(String[] args) {
        //TODO:使用数据库，目前的数据都是存在内存里的
        boolean flag = true;
        Actions.DatabaseAction.connect();
        while (flag) {
            System.out.println("-----学生管理系统----");
            System.out.println("请选择需要功能（填写数字）：");
            System.out.println("\"1\":查询学生");
            System.out.println("\"2\":增加学生");
            System.out.println("\"3\":删除学生");
            System.out.println("\"4\":更改信息");
            System.out.println("\"5\":退出系统");
            int select = Actions.InputScanner.getInt();
            switch (select) {
                case 1:
                    Actions.DatabaseAction.find();
                    break;
                case 2:
                    Actions.DatabaseAction.add();
                    break;
                case 3:
                    Actions.DatabaseAction.delete();
                    break;
                case 4:
                    Actions.DatabaseAction.update();
                    break;
                case 5:
                    flag = false;
                    System.out.println("退出系统成功");
                    break;
                default:
                    System.out.println("请输入正确数字");
                    break;
            }
        }
        Actions.InputScanner.scanner.close();
        Actions.DatabaseAction.close();
    }
}
