package org.example.diploma.core;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiplomaGeneratorUnitTest {

    private DiplomaGenerator generator;
    private XWPFDocument doc;

    @BeforeEach
    void setUp() {
        generator = new DiplomaGenerator();
        doc = new XWPFDocument();
    }

    @Test
    void testAddCenteredText() {
        String text = "Hello World";
        generator.addCenteredText(doc, text, 24, true); // package-private method
        XWPFParagraph p = doc.getParagraphArray(0);
        XWPFRun run = p.getRuns().get(0);

        assertAll(
                () -> assertEquals(text, run.getText(0), "Text content mismatch in addCenteredText"),
                () -> assertTrue(run.isBold(), "Bold property should be true in addCenteredText"),
                () -> assertEquals(24, run.getFontSize(), "Font size mismatch in addCenteredText"),
                () -> assertEquals(2, p.getAlignment().getValue(), "Paragraph alignment should be CENTER in addCenteredText"));

    }

    @Test
    void testAddRecipient() {
        String recipient = "John Doe";
        generator.addRecipient(doc, recipient);
        XWPFParagraph p = doc.getParagraphArray(0);
        XWPFRun run = p.getRuns().get(0);

        assertAll(
                () -> assertTrue(run.getText(0).contains("John Doe"), "Recipient text mismatch in addRecipient"),
                () -> assertEquals(2, run.getCTR().sizeOfBrArray(), "Number of line breaks before recipient is incorrect"),
                () -> assertEquals(2, p.getAlignment().getValue(), "Paragraph alignment should be CENTER in addRecipient"));
    }

    @Test
    void testAddDetails() {
        String details = "TID ______ DATUM ______";
        generator.addDetails(doc, details);
        XWPFParagraph p = doc.getParagraphArray(0);
        XWPFRun run = p.getRuns().get(0);

        assertAll(
                () -> assertTrue(run.getText(0).contains("TID"), "Details text mismatch in addDetails"),
                () -> assertEquals(1, run.getCTR().sizeOfBrArray(), "Number of line breaks before details is incorrect"),
                () -> assertEquals(2, p.getAlignment().getValue(), "Paragraph alignment should be CENTER in addDetails")
        );

    }
}
