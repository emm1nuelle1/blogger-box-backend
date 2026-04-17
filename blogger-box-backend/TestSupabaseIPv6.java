import java.sql.Connection;
import java.sql.DriverManager;

public class TestSupabaseIPv6 {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://[2a05:d012:42e:5719:c9c7:9188:fe97:410f]:5432/postgres?sslmode=require";
        String user = "postgres";
        String pass = "LAVICTOIRE34_";
        
        System.out.println("Trying direct IPv6 connection...");
        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("SUCCESS! Connected directly via IPv6.");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
