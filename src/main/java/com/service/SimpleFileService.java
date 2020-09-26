package com.service;

import com.converter.EntityToModelConverter;
import com.converter.ModelToDtoConverter;
import com.dao.FileDAO;
import com.dao.SimpleFileDAO;
import com.dto.FileDto;
import com.entities.FileEntity;
import com.model.FileModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimpleFileService implements FileService {
    private final EntityToModelConverter entityToModelConverter = new EntityToModelConverter();
    private final ModelToDtoConverter modelToDtoConverter = new ModelToDtoConverter();
    FileDAO fileDAO = new SimpleFileDAO();

    @Override //работает
    public FileDto openFile(String path) throws IOException {
        FileModel fileModel = entityToModelConverter.fileEntityToFileModel(fileDAO.openFile(path));
        FileDto fileDto = modelToDtoConverter.fileModelToFileDto(fileModel);
        return fileDto;
    }

    @Override //works!
    public FileDto createFile(String path, String fileName) {
       FileModel fileModel = entityToModelConverter.fileEntityToFileModel(fileDAO.createFile(path, fileName));
       FileDto fileDto = modelToDtoConverter.fileModelToFileDto(fileModel);
       return fileDto;
    }

    @Override //it works!!
    public List<FileDto> getFileNames(String path) {
        List<FileEntity> fileNames = fileDAO.getFileNames(path);
        List<FileModel> fileModels = new ArrayList<>();
        for (FileEntity fe: fileNames) {
            FileModel fileModel = entityToModelConverter.fileEntityToFileModel(fe);
            fileModel.setName(fe.getName());
            fileModels.add(fileModel);
        }
        List<FileDto> fileDtos = new ArrayList<>();
        for (FileModel fm: fileModels) {
            FileDto fileDto = modelToDtoConverter.fileModelToFileDto(fm);
            fileDto.setName(fm.getName());
            fileDtos.add(fileDto);
        }
        return fileDtos;
    }

    @Override //works!
    public FileDto createDirectory(String path, String name) {
        FileModel fileModel = entityToModelConverter.fileEntityToFileModel(fileDAO.createDirectory(path, name));
        FileDto fileDto = modelToDtoConverter.fileModelToFileDto(fileModel);
        return fileDto;
    }

    @Override
    public void delete(String path) {
        File file = new File(path);
        if (file.exists()) {
            if (file.isDirectory()) {
                for (File f : file.listFiles()) {
                    delete(f.getAbsolutePath());
                }
            }
            file.delete();
        }
    }

    @Override //работает, но какаято сомнительная херня
    public void write(String path, String text) {
        FileDAO simpleFileDAO = new SimpleFileDAO();
        simpleFileDAO.write(path, text);
    }

    //второй вариант открытия файла
//    @Override
//    public String openFile(String path) {
//        //писать проверку, что это файл??
//        String currentLine;
//        StringBuilder builder = new StringBuilder();
//        try (BufferedReader bf = new BufferedReader(new FileReader(new File(path)))) {
//            currentLine = bf.readLine();
//            while (currentLine != null) {
//                builder.append(currentLine);
//                builder.append("\n");
//                currentLine = bf.readLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return builder.toString();
//    }


    @Override //it works!!
    public List<FileDto> getDirectoryNames(String path) {
        List<FileEntity> fileNames = fileDAO.getDirectoryNames(path);
        List<FileModel> fileModels = new ArrayList<>();
        for (FileEntity fe: fileNames) {
            FileModel fileModel = entityToModelConverter.fileEntityToFileModel(fe);
            fileModel.setName(fe.getName());
            fileModels.add(fileModel);
        }
        List<FileDto> fileDtos = new ArrayList<>();
        for (FileModel fm: fileModels) {
            FileDto fileDto = modelToDtoConverter.fileModelToFileDto(fm);
            fileDto.setName(fm.getName());
            fileDtos.add(fileDto);
        }
        return fileDtos;
    }

    @Override
    public Map<String, String> openNote(String path) {
        Map<String, String> notesMap = fileDAO.openNote(path);
        return notesMap;
    }

    @Override
    public FileDto makeNote(String path, String fileName, String text) {
        FileModel fileModel = entityToModelConverter.fileEntityToFileModel(fileDAO.makeNote(path, fileName, text));
        FileDto fileDto = modelToDtoConverter.fileModelToFileDto(fileModel);
        return fileDto;
    }

}
























