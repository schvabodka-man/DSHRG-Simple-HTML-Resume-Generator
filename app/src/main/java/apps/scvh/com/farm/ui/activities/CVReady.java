package apps.scvh.com.farm.ui.activities;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import com.tom_roush.pdfbox.pdmodel.PDDocument;

import java.io.File;

import apps.scvh.com.farm.R;
import apps.scvh.com.farm.util.CV;
import apps.scvh.com.farm.util.CVRenderer;
import apps.scvh.com.farm.util.FSWorker;

public class CVReady extends AppCompatActivity {


    private PDDocument cv;
    private CVRenderer renderer;
    private FSWorker fsWorker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvready);
        CV cvBasic = (CV) getIntent().getExtras().getSerializable("cv");
        renderer = new CVRenderer(this);
        cv = renderer.renderCV(cvBasic);
        fsWorker = new FSWorker(this);
        initListeners();
    }

    private void initListeners() {
        findViewById(R.id.save).setOnClickListener(v -> {
            File documents = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
                    "/documents");
            documents.mkdir();
            File file = new File(documents, "CV.pdf");
            fsWorker.saveDocument(cv, file);
        });
        findViewById(R.id.open).setOnClickListener(v -> {
            fsWorker.previewDocument(cv);
        });
        findViewById(R.id.save_and_open).setOnClickListener(v -> {
            File documents = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
                    "/documents");
            documents.mkdir();
            File file = new File(documents, "CV.pdf");
            fsWorker.saveAndOpenDocument(cv, file);
        });
    }
}
