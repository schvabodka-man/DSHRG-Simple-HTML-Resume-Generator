package apps.scvh.com.farm.util.workers;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.tom_roush.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import apps.scvh.com.farm.R;

public class FSWorker {

    private Context context;

    public FSWorker(Context context) {
        this.context = context;
    }

    public void saveDocument(PDDocument pdfDocument, File file) {
        try {
            file.delete();
            pdfDocument.save(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openDocument(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), context.getString(R.string.pdf_mime_type));
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        context.startActivity(intent);
    }

    public void saveAndOpenDocument(PDDocument pdfDocument, File file) {
        saveDocument(pdfDocument, file);
        openDocument(file);
    }

    public void previewDocument(PDDocument pdfDocument) {
        try {
            File file = File.createTempFile(context.getString(R.string.temporary_file_name),
                    null, context.getCacheDir());
            saveDocument(pdfDocument, file);
            openDocument(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
