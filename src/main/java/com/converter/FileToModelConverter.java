package com.converter;

import com.model.FileModel;

import java.io.File;

public class FileToModelConverter {
    public FileModel fileToFileModel(File file) {
        FileModel model = new FileModel();
        model.setPath(file.getPath());
        model.setFileName(file.getName());
        return model; //может не надо но-аргс констуктор и написать в одну строчку?
    }

    public File fileModelToFile(FileModel model) {
        return new File(model.getPath(), model.getFileName());
    }
}
