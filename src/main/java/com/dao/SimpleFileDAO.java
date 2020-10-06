package com.dao;

import com.entities.FileEntity;
import com.mysql.cj.jdbc.Driver;
import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class SimpleFileDAO implements FileDAO {

    @Override
    public FileEntity createFile(String path, String name) {
        FileEntity fileEntity = new FileEntity();
        File file = new File(path, name);
        if (!file.exists()) {
            try {
                file.createNewFile();
                fileEntity.setName(file.getName());
                fileEntity.setPath(file.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileEntity;
    }

    @Override
    public FileEntity openFile(String path) throws IOException {
        FileEntity fileEntity = new FileEntity();
        File file = new File(path);
        String text = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        fileEntity.setPath(path);
        fileEntity.setText(text);
        return fileEntity;
    }

    @Override
    public List<FileEntity> getFileNames(String path) {
        File file = new File(path);
        List<FileEntity> filesList = new ArrayList<>();
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isFile()) {
                FileEntity fileEntity = new FileEntity();
                fileEntity.setName(f.getName());
                filesList.add(fileEntity);
            }
        }
        return filesList;
    }

    @Override
    public List<FileEntity> getDirectoryNames(String path) {
        File file = new File(path);
        List<FileEntity> dirList = new ArrayList<>();
        File[] dirs = file.listFiles();
        for (File d : dirs) {
            if (d.isDirectory()) {
                FileEntity fileEntity = new FileEntity();
                fileEntity.setName(d.getName());
                dirList.add(fileEntity);
            }
        }
        return dirList;
    }


    @Override
    public FileEntity createDirectory(String path, String name) {
        FileEntity fileEntity = new FileEntity();
        File file = new File(path, name);
        if (!file.exists()) {
            file.mkdir();
        }
        fileEntity.setName(file.getName());
        fileEntity.setPath(file.getPath());
        return fileEntity;
    }

    @Override
    public void write(String path, String text) {
        new File(path).delete();
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8))) {
            writer.write(text);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String openNote(String path) throws IOException, SQLException {
        Properties prop = new Properties();
        InputStream input = getClass().getResourceAsStream("/databaseProperties.xml");
        prop.loadFromXML(input);
        String url = prop.getProperty("url");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        StringBuilder resultNote = new StringBuilder();
        Driver driver = new Driver();
        DriverManager.registerDriver(driver);
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            String sql;
            sql = "SELECT * FROM note_table where path='" + path + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String date = resultSet.getString("date");
                String text = resultSet.getString("text");
                resultNote.append(date).append(" : ").append(text).append(System.getProperty("line.separator"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return resultNote.toString();
    }

    @Override
    public void makeNote(String path, String text) throws IOException, SQLException {
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String date = formatter.format(currentDate);
        Properties prop = new Properties();
        InputStream input = getClass().getResourceAsStream("/databaseProperties.xml");
        prop.loadFromXML(input);
        String url = prop.getProperty("url");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
//            String sqlPath;
//            sqlPath = "INSERT into path_table(path) values('" + path + "')";
//            sqlNote = "INSERT into note_table(date, text) values('" + date + "', '" + text + "')";
            String sqlNote = "INSERT INTO note_table (date, text, path)  values ('" + date + "', '" + text + "', '" + path + "')";
//            statement.execute(sqlPath);
            statement.executeUpdate(sqlNote);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteNote(String path) throws IOException, SQLException {
        path = ("/note" + path).replace("root", "file");
        Properties prop = new Properties();
        InputStream input = getClass().getResourceAsStream("/databaseProperties.xml");
        prop.loadFromXML(input);
        String url = prop.getProperty("url");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        Driver driver = new Driver();
        DriverManager.registerDriver(driver);
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            String sql = "DELETE FROM note_table where path='" + path + "'";
            statement.executeUpdate(sql);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
