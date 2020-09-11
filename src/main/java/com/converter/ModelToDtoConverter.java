package com.converter;

import com.dto.Dto;
import com.model.FileModel;

public class ModelToDtoConverter {
    public Dto ModelToDto(FileModel model) {
        return new Dto(model.getPath(), model.getFileName());
    }
}
