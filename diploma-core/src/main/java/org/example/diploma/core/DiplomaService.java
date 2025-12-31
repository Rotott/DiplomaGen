package org.example.diploma.core;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Path;

public class DiplomaService {

    private final DiplomaGenerator generator = new DiplomaGenerator();

    public void createDiploma(
            DiplomaData data,
            InputStream image,
            Path outputFile
            ) throws Exception {

        try (XWPFDocument doc = generator.generate(data, image);
             FileOutputStream out = new FileOutputStream(outputFile.toFile())) {

            doc.write(out);
        }
    }
}
