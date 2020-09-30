package com.converter;

import com.dto.FileDto;
import com.model.FileModel;

public class ModelToDtoConverter {
    public FileDto fileModelToFileDto(FileModel model) {
        FileDto fileDto = new FileDto(model.getPath(), model.getName(), model.getText(), model.getNotes());
        return fileDto;
    }

    public FileModel fileDtoToFileModel(FileDto fileDto) {
        FileModel fileModel = new FileModel(fileDto.getPath(), fileDto.getText());
        return fileModel;

    }


}
