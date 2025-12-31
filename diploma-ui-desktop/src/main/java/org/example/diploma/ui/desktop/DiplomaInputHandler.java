package org.example.diploma.ui.desktop;

import org.example.diploma.core.DiplomaData;

import java.nio.file.Path;

public class DiplomaInputHandler {

    public static final String TITLE_REQUIRED_MSG = "Title required";
    public static final String RECIPIENT_REQUIRED_MSG = "Recipient required";
    public static final String DETAILS_REQUIRED_MSG = "Some detail is required";
    public static final String IMAGE_PNG_REQUIRED_MSG = "Image must be a PNG file";


    public DiplomaData collectInput(
            String title,
            String subtitle,
            String awardText,
            String recipientLine,
            String detailsLine,
            Path imagePath
    ) {
        requireNonEmpty(title,TITLE_REQUIRED_MSG);
        requireNonEmpty(recipientLine,RECIPIENT_REQUIRED_MSG);
        requireNonEmpty(detailsLine,DETAILS_REQUIRED_MSG);

        requirePngImage(imagePath);

        return new DiplomaData(title, subtitle, awardText, recipientLine, detailsLine);
    }


    private void requireNonEmpty(String value, String message) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    private void requirePngImage(Path imagePath) {
        if (imagePath == null || !imagePath.getFileName().toString().toLowerCase().endsWith(".png")) {
            throw new IllegalArgumentException(IMAGE_PNG_REQUIRED_MSG);
        }
    }


}

