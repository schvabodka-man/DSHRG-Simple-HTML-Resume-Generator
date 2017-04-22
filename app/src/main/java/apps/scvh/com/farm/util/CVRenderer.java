package apps.scvh.com.farm.util;


import android.content.Context;

import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream;
import com.tom_roush.pdfbox.pdmodel.font.PDFont;
import com.tom_roush.pdfbox.pdmodel.font.PDType1Font;
import com.tom_roush.pdfbox.util.PDFBoxResourceLoader;

import java.io.IOException;
import java.util.ArrayList;

public class CVRenderer {

    private Context context;

    public CVRenderer(Context context) {
        this.context = context;
        PDFBoxResourceLoader.init(context);
    }

    public PDDocument renderCV(CV cv) {
        PDPage page = new PDPage();
        PDDocument document = new PDDocument();
        document.addPage(page);
        PDFont font = PDType1Font.COURIER;
        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setFont(font, 14);
            drawName(contentStream, cv.getFullName());
            drawAbout(contentStream, cv.getAbout());
            contentStream.endText();
            contentStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }


    private void drawName(PDPageContentStream stream, String name) {
        try {
            stream.showText(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawAbout(PDPageContentStream stream, String about) {
        try {
            stream.showText(about);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawExperience(PDPageContentStream stream, ArrayList<String> experience) {

    }

    private void drawEducation(PDPageContentStream stream, ArrayList<String> education) {

    }

    private void drawProjects(PDPageContentStream stream, ArrayList<String> projects) {

    }

    private void drawLinks(PDPageContentStream stream, ArrayList<String> links) {

    }

    private void drawPrimarySkills(PDPageContentStream stream, ArrayList<String> primarySkills) {

    }

    private void drawSecondarySkills(PDPageContentStream stream, ArrayList<String>
            secondarySkills) {

    }

    private void drawOtherSkills(PDPageContentStream stream, ArrayList<String> otherSkills) {

    }
}
