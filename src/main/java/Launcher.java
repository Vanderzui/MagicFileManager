import com.service.SimpleFileService;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Launcher {
    public static void main(String[] args) throws IOException {

        SimpleFileService simpleFileService = new SimpleFileService();
//        simpleFileService.create("D:\\myDir", "alisa.txt");
//        simpleFileService.open("D:\\myDir", "alisa.txt");
//        simpleFileService.write("D:\\myDir", "alisa.txt", "алиса");
//        simpleFileService.open("D:\\myDir", "alisa.txt");
//        File miniDir = new File("D:\\myDir", "miniDir");
//        simpleFileService.createDirectory("D:\\myDir", "newDir2");
//        File file = new File("D:\\myDir\\newDir2", "aliceDir");
//        file.mkdir();
//        System.out.println(file.getAbsolutePath());
        List<String> strings = simpleFileService.openDirectory("D:", "myDir");
        for (String files : strings) {
            System.out.println(files);
        }

    }
}
