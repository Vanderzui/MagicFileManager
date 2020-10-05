import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class Launcher {
    public static void main(String[] args) throws IOException {
//        String url = "jdbc:mysql://localhost:3306/notes?serverTimezone=Europe/Minsk&useSSL=false";
//        String username = "root";
//        String password = "1234";
//        System.out.println("Connecting...");


        Properties prop = new Properties();
        InputStream input = Launcher.class.getResourceAsStream("/databaseProperties.xml");
        prop.loadFromXML(input);
        String url = prop.getProperty("url");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            String sql;
            sql = "INSERT into note_table(path, date, text) values(" + "'/note/file/alisa.txt'" + ", " + "'05.10.2020'" + ", " + "'hello you'" + ")";
            statement.executeUpdate(sql);
        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }

    }
}
