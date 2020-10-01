package com.service;

import com.converter.EntityToModelConverter;
import com.converter.ModelToDtoConverter;
import com.dao.FileDAO;
import com.dao.SimpleFileDAO;
import com.dto.FileDto;
import com.entities.FileEntity;
import com.model.FileModel;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class SimpleFileService implements FileService {
    private final EntityToModelConverter entityToModelConverter = new EntityToModelConverter();
    private final ModelToDtoConverter modelToDtoConverter = new ModelToDtoConverter();
    FileDAO fileDAO = new SimpleFileDAO();

    @Override
    public String getRootDir() throws IOException {
        Properties properties = new Properties();
        InputStream input = getClass().getResourceAsStream("/props.xml");
        properties.loadFromXML(input);
        String root = properties.getProperty("ROOT");
        return root;
    }

    @Override
    public FileDto openFile(String path) throws IOException {
        FileModel fileModel = entityToModelConverter.fileEntityToFileModel(fileDAO.openFile(path));
        FileDto fileDto = modelToDtoConverter.fileModelToFileDto(fileModel);
        return fileDto;
    }

    @Override
    public FileDto createFile(String path, String fileName) {
        FileModel fileModel = entityToModelConverter.fileEntityToFileModel(fileDAO.createFile(path, fileName));
        FileDto fileDto = modelToDtoConverter.fileModelToFileDto(fileModel);
        return fileDto;
    }

    @Override
    public String getFileExtension(String name) {
        if(name.contains(".")) {
            return name.substring(name.lastIndexOf('.'));
        }
        return "noEx";
    }

    @Override
    public String getIcons(String extension) throws IOException {
        Properties prop = new Properties();
        InputStream input = getClass().getResourceAsStream("/props.xml");
        prop.loadFromXML(input);
        String icon;
        if(extension.equals(".txt")) {
            icon = prop.getProperty(".txt");
        } else if(extension.equals(".jpg")) {
            icon = prop.getProperty(".jpg");
        } else if(extension.equals(".pdf")) {
            icon = prop.getProperty(".pdf");
        } else {
            icon = prop.getProperty("noEx");
        }
        return icon;
    }

    @Override
    public List<FileDto> getFileNames(String path) throws IOException {
        List<FileEntity> fileNames = fileDAO.getFileNames(path);
        List<FileModel> fileModels = new ArrayList<>();
        for (FileEntity fe : fileNames) {
            FileModel fileModel = entityToModelConverter.fileEntityToFileModel(fe);
            fileModel.setName(fe.getName());
            fileModels.add(fileModel);
        }
        List<FileDto> fileDtos = new ArrayList<>();
        for (FileModel fm : fileModels) {
            FileDto fileDto = modelToDtoConverter.fileModelToFileDto(fm);
            fileDto.setName(fm.getName());
            String icons = getIcons(getFileExtension(fm.getName()));
            fileDto.setIcon(icons);
            fileDtos.add(fileDto);
        }
        return fileDtos;
    }

    @Override
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

    @Override
    public void write(String path, String text) {
        FileDAO simpleFileDAO = new SimpleFileDAO();
        simpleFileDAO.write(path, text);
    }

    @Override
    public List<FileDto> getDirectoryNames(String path) {
        List<FileEntity> fileNames = fileDAO.getDirectoryNames(path);
        List<FileModel> fileModels = new ArrayList<>();
        for (FileEntity fe : fileNames) {
            FileModel fileModel = entityToModelConverter.fileEntityToFileModel(fe);
            fileModel.setName(fe.getName());
            fileModels.add(fileModel);
        }
        List<FileDto> fileDtos = new ArrayList<>();
        for (FileModel fm : fileModels) {
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
    public void makeNote(String path, String text) {
        fileDAO.makeNote(path, text);
    }

    @Override
    public void deleteNote(String path) {
        fileDAO.deleteNote(path);
    }

    @Override
    public String checkURL(String path) {
        if (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }
        return path;
    }

    public String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= " + contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }
}
























