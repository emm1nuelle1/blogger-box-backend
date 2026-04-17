import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class TestSupabasePooler {
    public static void main(String[] args) {
        String[] regions = {
            "eu-west-3", "eu-central-1", "eu-west-1", "eu-west-2",
            "us-east-1", "us-east-2", "us-west-1", "us-west-2"
        };
        
        String project = "bwwlufqnobdyfhgtdncd";
        String user = "postgres." + project;
        String pass = "LAVICTOIRE34_";
        
        System.out.println("Testing regions...");
        for (String region : regions) {
            String url = "jdbc:postgresql://aws-0-" + region + ".pooler.supabase.com:5432/postgres?sslmode=require&connectTimeout=5";
            System.out.println("Trying " + region + "...");
            try {
                Connection conn = DriverManager.getConnection(url, user, pass);
                System.out.println("SUCCESS! The region is: " + region);
                conn.close();
                return;
            } catch (Exception e) {
                if (e.getMessage().contains("Tenant or user not found")) {
                    System.out.println("  -> Wrong region (tenant not found)");
                } else if (e.getMessage().contains("password authentication failed")) {
                    System.out.println("  -> SUCCESS! Found region, but wrong password: " + region);
                    return;
                } else {
                    System.out.println("  -> Error: " + e.getMessage());
                }
            }
        }
    }
}
