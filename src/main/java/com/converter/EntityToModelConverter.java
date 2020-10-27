package com.converter;

import com.entities.FileEntity;
import com.model.FileModel;

public class EntityToModelConverter {

    public FileModel fileEntityToFileModel(FileEntity entity) {
        return new FileModel(entity.getText(), entity.getPath(), entity.getName());
    }

    public FileEntity fileModelToFileEntity(FileModel model) {
        return new FileEntity(model.getPath(), model.getText());
    }
}
