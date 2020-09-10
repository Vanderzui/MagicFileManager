import com.service.SimpleFileService;

public class Launcher {
    public static void main(String[] args) {

        SimpleFileService simpleFileService = new SimpleFileService();
//        simpleFileService.create("D:\\myDir", "alisa.txt");
//        simpleFileService.open("D:\\myDir", "alisa.txt");
//        simpleFileService.write("D:\\myDir", "alisa.txt", "алиса");
//        simpleFileService.open("D:\\myDir", "alisa.txt");
//        File miniDir = new File("D:\\myDir", "miniDir");
        simpleFileService.createDirectory("D:\\myDir", "newDir");
        simpleFileService.createFile("D:\\myDir", "newFile");
        simpleFileService.open("D:\\myDir", "alisa.txt");
        simpleFileService.createFile("D:\\myDir", "alisa.txt");


    }
}
