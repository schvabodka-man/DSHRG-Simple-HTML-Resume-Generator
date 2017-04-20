package apps.scvh.com.farm.util;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream;
import com.tom_roush.pdfbox.pdmodel.font.PDFont;
import com.tom_roush.pdfbox.pdmodel.font.PDType1Font;
import com.tom_roush.pdfbox.util.PDFBoxResourceLoader;

import java.io.File;
import java.io.FileOutputStream;
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
            contentStream.endText();
            contentStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    public void saveDocument(PDDocument pdfDocument) {
        File documents = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/documents");
        documents.mkdir();
        File file = new File(documents, "CV.pdf");
        try {
            pdfDocument.save(new FileOutputStream(file));
            pdfDocument.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openDocument(PDDocument pdfDocument) {
        try {
            File file = File.createTempFile("temporary_cv.pdf", null, context.getCacheDir());
            pdfDocument.save(file);
            pdfDocument.close();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            context.startActivity(intent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
