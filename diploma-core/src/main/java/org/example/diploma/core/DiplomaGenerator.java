package org.example.diploma.core;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import java.io.InputStream;

public class DiplomaGenerator {

    public XWPFDocument generate(DiplomaData data, InputStream imageStream) throws Exception {
        XWPFDocument document = new XWPFDocument();

        addImage(document, imageStream);
        addCenteredText(document, data.title(), 40, true);
        addCenteredText(document, data.subtitle(), 20, false);
        addCenteredText(document, data.awardText(), 20, false);
        addRecipient(document, data.recipientLine());
        addDetails(document, data.detailsLine());

        return document;
    }

    private void addImage(XWPFDocument document, InputStream imageStream) throws Exception {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        run.addPicture(
                imageStream,
                XWPFDocument.PICTURE_TYPE_PNG,
                "logo.png",
                Units.toEMU(275),
                Units.toEMU(183)
        );
    }

    private void addCenteredText(XWPFDocument doc, String text, int size, boolean bold) {
        XWPFParagraph p = doc.createParagraph();
        p.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = p.createRun();
        run.setText(text);
        run.setFontSize(size);
        run.setBold(bold);
    }

    private void addRecipient(XWPFDocument doc, String text) {
        XWPFParagraph p = doc.createParagraph();
        p.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = p.createRun();
        run.addBreak();
        run.addBreak();
        run.setText(text);
    }

    private void addDetails(XWPFDocument doc, String text) {
        XWPFParagraph p = doc.createParagraph();
        p.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = p.createRun();
        run.addBreak();
        run.setText(text);
    }
}
