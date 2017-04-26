package apps.scvh.com.farm.util.workers;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.tom_roush.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import apps.scvh.com.farm.R;
import apps.scvh.com.farm.util.cv.CVHolder;

public class FSWorker extends AsyncTask<CVHolder, Integer, Void> {

    private Context context;
    private int progress;

    public FSWorker(Context context) {
        this.context = context;
    }

    private void saveDocument(PDDocument pdfDocument, File file) {
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

    private void saveAndOpenDocument(PDDocument pdfDocument, File file) {
        saveDocument(pdfDocument, file);
        openDocument(file);
    }

    private void previewDocument(PDDocument pdfDocument) {
        try {
            File file = File.createTempFile(context.getString(R.string.temporary_file_name),
                    null, context.getCacheDir());
            saveDocument(pdfDocument, file);
            openDocument(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected Void doInBackground(CVHolder... params) {
        switch (params[0].getFlag()) {
            case PREVIEW:
                previewDocument(params[0].getCv());
                break;
            case WRITE:
                saveDocument(params[0].getCv(), params[0].getFile());
                break;
            case WRITEANDOPEN:
                saveAndOpenDocument(params[0].getCv(), params[0].getFile());
        }
        return null;
    }
}
