package org.example.diploma.ui.desktop;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.example.diploma.core.DiplomaData;
import org.example.diploma.core.DiplomaService;

import java.io.InputStream;
import java.nio.file.Path;

public class DiplomaFxController {

    @FXML private TextField titleField;
    @FXML private TextField subtitleField;
    @FXML private TextField awardTextField;
    @FXML private TextField recipientField;
    @FXML private TextField detailsField;
    @FXML private Label imageLabel;

    private Path selectedImage;

    private final DiplomaInputHandler inputHandler = new DiplomaInputHandler();
    private final DiplomaService diplomaService = new DiplomaService();

    @FXML
    void onChooseImage() {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("PNG Images", "*.png")
        );

        var file = chooser.showOpenDialog(null);
        if (file != null) {
            selectedImage = file.toPath();
            imageLabel.setText(file.getName());
        }
    }

    @FXML
    void onGenerateDiploma() {
        try {
            DiplomaData data = inputHandler.collectInput(
                    titleField.getText(),
                    subtitleField.getText(),
                    awardTextField.getText(),
                    recipientField.getText(),
                    detailsField.getText(),
                    selectedImage
            );

            try (InputStream image = selectedImage.toUri().toURL().openStream()) {
                diplomaService.createDiploma(data, image, Path.of("../Diploma.docx"));
            }

            imageLabel.setText("Diploma created successfully!");
        } catch (Exception e) {
            imageLabel.setText(e.getMessage());
        }
    }
}
