import java.sql.Connection;
import java.sql.DriverManager;

public class TestSupabaseDirect {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://db.bwwlufqnobdyfhgtdncd.supabase.co:5432/postgres?sslmode=require";
        String user = "postgres";
        String pass = "LAVICTOIRE34_";
        
        System.out.println("Trying direct connection...");
        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("SUCCESS! Connected directly.");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
