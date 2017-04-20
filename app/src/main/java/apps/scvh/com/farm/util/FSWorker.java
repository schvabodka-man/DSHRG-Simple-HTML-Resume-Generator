package apps.scvh.com.farm.util;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.tom_roush.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FSWorker {

    private Context context;

    public FSWorker(Context context) {
        this.context = context;
    }

    public void saveDocument(PDDocument pdfDocument, File file) {
        try {
            file.delete();
            pdfDocument.save(new FileOutputStream(file));
            pdfDocument.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openDocument(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        context.startActivity(intent);
    }

    public void saveAndOpenDocument(PDDocument pdfDocument, File file) {
        saveDocument(pdfDocument, file);
        openDocument(file);
    }

    public void previewDocument(PDDocument pdfDocument) {
        try {
            File file = File.createTempFile("temporary_cv.pdf", null, context.getCacheDir());
            saveDocument(pdfDocument, file);
            openDocument(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
