package apps.scvh.com.farm.util;


import android.content.Context;

import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream;
import com.tom_roush.pdfbox.pdmodel.font.PDFont;
import com.tom_roush.pdfbox.pdmodel.font.PDType1Font;
import com.tom_roush.pdfbox.util.PDFBoxResourceLoader;

import java.io.IOException;

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
        PDFont font = PDType1Font.HELVETICA;
        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setFont(font, 14);
            contentStream.drawString("HOLDER");
            contentStream.endText();
            contentStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

}
