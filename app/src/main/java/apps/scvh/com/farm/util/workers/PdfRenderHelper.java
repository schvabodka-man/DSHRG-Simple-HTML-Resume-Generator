package apps.scvh.com.farm.util.workers;


import android.content.Context;

import com.tom_roush.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;

import apps.scvh.com.farm.R;
import apps.scvh.com.farm.util.enums.CVFields;

public class PdfRenderHelper {

    private Context context;

    public PdfRenderHelper(Context context) {
        this.context = context;
    }

    String getStringForField(CVFields flag) {
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
        return ""; //It's here JFL
    }

    void printWrappedText(String text, PDPageContentStream stream) {
        try {
            stream.showText(text);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
