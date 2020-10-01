import com.dao.SimpleFileDAO;
import com.service.SimpleFileService;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Launcher {
    public static void main(String[] args) throws IOException {
        String url = "jdbc:mysql://localhost:3306/notes";
        String username = "root";
        String password = "1234";
        System.out.println("Connecting...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }

    }
}
