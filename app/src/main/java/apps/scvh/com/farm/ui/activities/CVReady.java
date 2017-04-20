package apps.scvh.com.farm.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tom_roush.pdfbox.pdmodel.PDDocument;

import apps.scvh.com.farm.R;
import apps.scvh.com.farm.util.CV;
import apps.scvh.com.farm.util.CVRenderer;

public class CVReady extends AppCompatActivity {


    private PDDocument cv;
    private CVRenderer renderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvready);
        CV cvBasic = (CV) getIntent().getExtras().getSerializable("cv");
        renderer = new CVRenderer(this);
        cv = renderer.renderCV(cvBasic);
        initListeners();
    }

    private void initListeners() {
        findViewById(R.id.save).setOnClickListener(v -> renderer.saveDocument(cv));
        findViewById(R.id.open).setOnClickListener(v -> renderer.openDocument(cv));
    }
}
