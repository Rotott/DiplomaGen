package org.example.diploma.ui.desktop;

import org.example.diploma.core.*;

import java.io.InputStream;
import java.nio.file.Path;

public class DesktopApp {

    public static void main(String[] args) {
        DiplomaData data = new DiplomaData(
                "DIPLOM",
                "LÖKEN",
                "TILLDELAT",
                "___________FirstName________LastName_________",
                "TID        ______________________        DATUM        ______________________"
        );

        DiplomaService service = new DiplomaService();

        try (InputStream image = DesktopApp.class.getClassLoader().getResourceAsStream("images/onion.png")) {
            service.createDiploma(data, image, Path.of("Diploma.docx"));
            System.out.println("Diploma.docx created successfully, refactored");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
