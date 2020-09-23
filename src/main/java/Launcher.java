import com.service.SimpleFileService;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
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
//        List<String> strings = simpleFileService.getDirectoryNames("D:\\myDir", "open");
//        for (String files : strings) {
//            System.out.println(files);
//        simpleFileService.write("D:/myDir/alisa.txt", "qwerty");
        File file = new File("D:/myDir/open/nbv");
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getPath());
        simpleFileService.delete("D:/myDir/open/nbv");

    }
}
