import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws Exception {
        Connection c = DbUtil.getConnection();
        Util.insertInfo(c, "福州");
        Util.insertInfo(c, "北京");
        Util.insertInfo(c, "上海");
        Scanner sc=new Scanner(System.in);
        System.out.println("查询？/更新？");
        String s=sc.nextLine();
        if(s.equals("查询")){
            System.out.println("查哪里？");
            s=sc.nextLine();
            Util.queryWeather(c,s);
        }
        else if (s.equals("更新")){
            Util.updateInfo(c,s);
        }
        else System.out.println("?");
    }
}
