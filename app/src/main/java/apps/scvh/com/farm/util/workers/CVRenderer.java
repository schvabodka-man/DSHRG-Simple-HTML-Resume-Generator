package apps.scvh.com.farm.util.workers;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream;
import com.tom_roush.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import apps.scvh.com.farm.R;
import apps.scvh.com.farm.util.cv.CV;
import apps.scvh.com.farm.util.enums.CVFields;
import apps.scvh.com.farm.util.enums.PdfLinePositions;

public class CVRenderer extends AsyncTask<CV, Integer, PDDocument> {

    private Context context;

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
            contentStream.setFont(PDType1Font.COURIER, 28);
            contentStream.newLineAtOffset(PdfLinePositions.START_OF_LINE.getCoordinate(),
                    PdfLinePositions.TOP_LEVEL.getCoordinate());
            drawName(contentStream, cv.getFullName());
            drawAbout(contentStream, cv.getAbout());
            drawList(contentStream, cv.getEducation(), CVFields.EDUCATION);
            drawList(contentStream, cv.getExperience(), CVFields.EXPERIENCE);
            drawList(contentStream, cv.getLinks(), CVFields.LINKS);
            drawList(contentStream, cv.getProjects(), CVFields.PROJECTS);
            drawList(contentStream, cv.getPrimarySkills(), CVFields.PRIMARY_SKILLS);
            drawList(contentStream, cv.getSecondarySkills(), CVFields.SECONDARY_SKILLS);
            drawList(contentStream, cv.getOtherSkills(), CVFields.OTHER_SKILLS);
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
            stream.setLeading(PdfLinePositions.BIG_LEADING.getCoordinate());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawAbout(PDPageContentStream stream, String about) {
        try {
            stream.newLine();
            stream.showText(about);
            stream.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawList(PDPageContentStream stream, ArrayList<String> list, CVFields flag)
            throws IOException {
        if (!list.isEmpty()) {
            Iterator<String> iterator = list.iterator();
            stream.showText(getStringForField(flag));
            stream.setLeading(PdfLinePositions.SMALL_LEADING.getCoordinate());
            stream.newLine();
            while (iterator.hasNext()) {
                stream.showText(iterator.next());
                if (iterator.hasNext()) {
                    stream.newLine();
                }
            }
            stream.setLeading(PdfLinePositions.BIG_LEADING.getCoordinate());
            stream.newLine();
        } else {
            Log.d(context.getString(R.string.pdf_render_debug), context.getString(R.string
                    .pdf_render_null));
        }
    }

    private String getStringForField(CVFields flag) {
        switch (flag) {
            case EDUCATION:
                return context.getString(R.string.education);
            case EXPERIENCE:
                return context.getString(R.string.experience);
            case LINKS:
                return context.getString(R.string.links);
            case PROJECTS:
                return context.getString(R.string.personal_projects);
            case PRIMARY_SKILLS:
                return context.getString(R.string.primary_skills);
            case SECONDARY_SKILLS:
                return context.getString(R.string.secondary_skills);
            case OTHER_SKILLS:
                return context.getString(R.string.other_skills);
        }
        return "";
    }

    @Override
    protected PDDocument doInBackground(CV... params) {
        return renderCV(params[0]);
    }

}
