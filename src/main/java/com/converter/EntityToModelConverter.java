package com.converter;

import com.entities.FileEntity;
import com.model.FileModel;

public class EntityToModelConverter {

    public FileModel fileEntityToFileModel(FileEntity entity) {
        FileModel fileModel = new FileModel(entity.getText(), entity.getPath(), entity.getName());
        return fileModel;
    }

    public FileEntity fileModelToFileEntity(FileModel model) {
        FileEntity fileEntity = new FileEntity(model.getPath(), model.getText());
        return fileEntity;
    }
}
