package com.converter;

import com.entities.FileEntity;
import com.model.FileModel;

import java.io.File;

public class EntityToModelConverter {

    public FileModel fileEntityToFileModel(FileEntity entity) {
//        String wholePath = entity.getPath()+ entity.getName();
        FileModel fileModel = new FileModel(entity.getText(),  entity.getPath(), entity.getName(), entity.getNotes());
        return fileModel;
    }

     public FileEntity fileModelToFileEntity(FileModel model) {
        FileEntity fileEntity = new FileEntity(model.getPath(), model.getText());
        return fileEntity;
    }
}
