import com.dao.SimpleFileDAO;
import com.service.SimpleFileService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Launcher {
    public static void main(String[] args) throws IOException {

        SimpleFileService simpleFileService = new SimpleFileService();
        SimpleFileDAO simpleFileDAO = new SimpleFileDAO();
        String name = "alisa.txt";
//        String substring = name.substring(name.lastIndexOf('.'));
        System.out.println(name.contains("."));
//        System.out.println(substring);
    }

}
