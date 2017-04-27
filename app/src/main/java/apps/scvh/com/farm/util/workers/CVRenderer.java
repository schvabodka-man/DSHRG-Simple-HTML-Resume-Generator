package apps.scvh.com.farm.util.workers;


import android.content.Context;
import android.os.AsyncTask;

import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream;
import com.tom_roush.pdfbox.pdmodel.font.PDFont;
import com.tom_roush.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import apps.scvh.com.farm.util.cv.CV;
import apps.scvh.com.farm.util.enums.PdfLinePositions;

public class CVRenderer extends AsyncTask<CV, Integer, PDDocument> {

    private Context context;

    private PDFont font;
    private int progress;

    public CVRenderer(Context context) {
        this.context = context;
    }

    private PDDocument renderCV(CV cv) {
        PDPage page = new PDPage();
        PDDocument document = new PDDocument();
        document.addPage(page);
        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            drawName(contentStream, cv.getFullName());
            drawAbout(contentStream, cv.getAbout());
            drawEducation(contentStream, cv.getEducation());
            drawExperience(contentStream, cv.getExperience());
            drawLinks(contentStream, cv.getLinks());
            drawProjects(contentStream, cv.getProjects());
            drawPrimarySkills(contentStream, cv.getPrimarySkills());
            drawSecondarySkills(contentStream, cv.getSecondarySkills());
            drawOtherSkills(contentStream, cv.getOtherSkills());
            contentStream.endText();
            contentStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }


    private void drawName(PDPageContentStream stream, String name) {
        try {
            font = PDType1Font.COURIER;
            stream.setFont(font, 28);
            stream.moveTextPositionByAmount(PdfLinePositions.START_OF_LINE.getCoordinate(),
                    PdfLinePositions.TOP_LEVEL.getCoordinate());
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
        Iterator<String> iterator = experience.iterator();
        while (iterator.hasNext()) {
            try {
                stream.showText(iterator.next());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void drawEducation(PDPageContentStream stream, ArrayList<String> education) {
        Iterator<String> iterator = education.iterator();
        while (iterator.hasNext()) {
            try {
                stream.showText(iterator.next());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void drawProjects(PDPageContentStream stream, ArrayList<String> projects) {
        Iterator<String> iterator = projects.iterator();
        while (iterator.hasNext()) {
            try {
                stream.showText(iterator.next());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void drawLinks(PDPageContentStream stream, ArrayList<String> links) {
        Iterator<String> iterator = links.iterator();
        while (iterator.hasNext()) {
            try {
                stream.showText(iterator.next());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void drawPrimarySkills(PDPageContentStream stream, ArrayList<String> primarySkills) {
        Iterator<String> iterator = primarySkills.iterator();
        while (iterator.hasNext()) {
            try {
                stream.showText(iterator.next());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void drawSecondarySkills(PDPageContentStream stream, ArrayList<String>
            secondarySkills) {
        Iterator<String> iterator = secondarySkills.iterator();
        while (iterator.hasNext()) {
            try {
                stream.showText(iterator.next());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void drawOtherSkills(PDPageContentStream stream, ArrayList<String> otherSkills) {
        Iterator<String> iterator = otherSkills.iterator();
        while (iterator.hasNext()) {
            try {
                stream.showText(iterator.next());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected PDDocument doInBackground(CV... params) {
        return renderCV(params[0]);
    }

}
