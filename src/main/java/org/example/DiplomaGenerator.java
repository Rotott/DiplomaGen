package org.example;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DiplomaGenerator {
    public static void main(String[] args) {
        try {
            XWPFDocument document = new XWPFDocument();
            FileOutputStream out = new FileOutputStream("Diploma.docx");

            // Add Image
            XWPFParagraph imageParagraph = document.createParagraph();
            imageParagraph.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun imageRun = imageParagraph.createRun();
            try (InputStream is = Files.newInputStream(Paths.get("src/main/java/org/example/images/onion.png"))) {
                imageRun.addPicture(is, XWPFDocument.PICTURE_TYPE_PNG, "onion.png", Units.toEMU(275), Units.toEMU(183));
            } catch (Exception e) {
                e.printStackTrace();

            }

            // Title
            XWPFParagraph title = document.createParagraph();
            XWPFRun titleRun = title.createRun();
            titleRun.setText("DIPLOM");
            titleRun.setBold(true);
            titleRun.setFontSize(40);
            title.setAlignment(ParagraphAlignment.CENTER);

            // Subtitle
            XWPFParagraph subtitle = document.createParagraph();
            XWPFRun subtitleRun = subtitle.createRun();
            subtitleRun.setText("LÖKEN");
            subtitleRun.setFontSize(20);
            subtitle.setAlignment(ParagraphAlignment.CENTER);

            // Award text
            XWPFParagraph award = document.createParagraph();
            XWPFRun awardRun = award.createRun();
            awardRun.setText("TILLDELAT");
            awardRun.setFontSize(20);
            award.setAlignment(ParagraphAlignment.CENTER);

            // Space for recipient
            XWPFParagraph recipient = document.createParagraph();
            recipient.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun recipientRun = recipient.createRun();
            recipientRun.addBreak();
            recipientRun.addBreak();
            recipientRun.setText("___________FirstName________LastName_________");

            // Instructor and Date
            XWPFParagraph details = document.createParagraph();
            XWPFRun detailsRun = details.createRun();
            detailsRun.addBreak();
            detailsRun.setText("TID        ______________________        DATUM        ______________________");
            details.setAlignment(ParagraphAlignment.CENTER);

            document.write(out);
            out.close();
            document.close();
            System.out.println("Diploma.docx created successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
