package com.converter;

import com.dto.FileDto;
import com.model.FileModel;

public class ModelToDtoConverter {
    public FileDto fileModelToFileDto(FileModel model) {
        return new FileDto(model.getPath(), model.getName(), model.getText());
    }

    public FileModel fileDtoToFileModel(FileDto fileDto) {
        return new FileModel(fileDto.getPath(), fileDto.getText());
    }
}
