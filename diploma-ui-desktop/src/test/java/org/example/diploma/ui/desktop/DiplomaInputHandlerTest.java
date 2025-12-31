package org.example.diploma.ui.desktop;

import org.example.diploma.core.DiplomaData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class DiplomaInputHandlerTest {
    private DiplomaInputHandler controller;

    @BeforeEach
    void setup() {
        controller = new DiplomaInputHandler();
    }

    @Test
    void testValidInputCreatesDiplomaData() {
        String title = "Diploma";
        String subTitle = "Junior comp ";
        String awardText = "Award Text";
        String recipientLine = "John Doe";
        String detailsLine = "Time: 59.43    Date: 2025-12-31";
        Path dummyImage = Path.of("dummy.png");

        DiplomaData data = controller.collectInput(
                title,
                subTitle,
                awardText,
                recipientLine,
                detailsLine,
                dummyImage
        );
        assertAll(
                () -> assertEquals(title, data.title(), "Title mismatch"),
                () -> assertEquals(subTitle, data.subtitle(), "Subtitle mismatch"),
                () -> assertEquals(awardText, data.awardText(), "Award text mismatch"),
                () -> assertEquals(recipientLine, data.recipientLine(), "Recipient mismatch"),
                () -> assertEquals(detailsLine, data.detailsLine(), "Details line mismatch")
        );
    }


    @Nested
    class TitleTests {
        @Test
        void nullTitleThrows() {
            Path dummyImage = Path.of("dummy.png");
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                    () -> controller.collectInput(null, "Subtitle", "Award", "John", "Date", dummyImage)
            );
            assertEquals(DiplomaInputHandler.TITLE_REQUIRED_MSG, ex.getMessage());
        }

        @Test
        void testMissingTitleThrows() {
            Path dummyImage = Path.of("dummy.png");
            Exception ex = assertThrows(IllegalArgumentException.class,
                    () -> controller.collectInput("", "Subtitle", "Award", "John", "Date", dummyImage));
            assertEquals(DiplomaInputHandler.TITLE_REQUIRED_MSG, ex.getMessage());

        }
    }

    @Nested
    class RecipientTests {
        @Test
        void nullRecipientTThrows() {
            Path dummyImage = Path.of("dummy.png");
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                    () -> controller.collectInput("Diploma", "Subtitle", "Award", null, "Date", dummyImage)
            );
            assertEquals(DiplomaInputHandler.RECIPIENT_REQUIRED_MSG, ex.getMessage());
        }

        @Test
        void testMissingRecipientThrows() {
            Path dummyImage = Path.of("dummy.png");

            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                    () -> controller.collectInput("Diploma", "Subtitle", "Award", "", "Date", dummyImage)
            );

            assertEquals(DiplomaInputHandler.RECIPIENT_REQUIRED_MSG, ex.getMessage());
        }
    }

    @Nested
    class DetailsTests {
        @Test
        void nullDetailsThrows() {
            Path dummyImage = Path.of("dummy.png");
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                    () -> controller.collectInput("Diploma", "Subtitle", "Award", "John", null, dummyImage)
            );
            assertEquals(DiplomaInputHandler.DETAILS_REQUIRED_MSG, ex.getMessage());
        }

        @Test
        void testMissingDetailsThrows() {
            Path dummyImage = Path.of("dummy.png");
            Exception ex = assertThrows(IllegalArgumentException.class,
                    () -> controller.collectInput("Diploma", "Subtitle", "Award", "John", "", dummyImage));
            assertEquals(DiplomaInputHandler.DETAILS_REQUIRED_MSG, ex.getMessage());
        }
    }

    @Nested
    class ImageTests {
        @Test
        void nonPngImageThrows() {
            Path jpg = Path.of("image.jpg");

            Exception ex = assertThrows(IllegalArgumentException.class,
                    () -> controller.collectInput("Diploma", "Subtitle", "Award", "John", "Date", jpg)
            );
            assertTrue(ex.getMessage().contains(DiplomaInputHandler.IMAGE_PNG_REQUIRED_MSG));
        }
    }


}
