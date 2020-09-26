import com.converter.EntityToModelConverter;
import com.converter.ModelToDtoConverter;
import com.dao.SimpleFileDAO;
import com.dto.FileDto;
import com.entities.FileEntity;
import com.model.FileModel;
import com.service.SimpleFileService;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Launcher {
    public static void main(String[] args) throws IOException {

        SimpleFileService simpleFileService = new SimpleFileService();
        SimpleFileDAO simpleFileDAO = new SimpleFileDAO();
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
        System.out.println(simpleFileDAO.getFileNames("D:/myDir"));
        List<FileDto> directoryNames = simpleFileService.getDirectoryNames("D:/myDir");
        for (FileDto fd : directoryNames) {
            System.out.println(fd.getName() + " " + fd.getPath() + " " + fd.getText());
        }
        FileEntity fileEntity = new FileEntity();
        fileEntity.setName("hi");
        FileModel fileModel = new EntityToModelConverter().fileEntityToFileModel(fileEntity);
        FileDto fileDto = new ModelToDtoConverter().fileModelToFileDto(fileModel);
        fileDto.setName(fileModel.getName());
        System.out.println(directoryNames.contains(fileDto));

        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String format = formatter.format(currentDate);
    }
}
