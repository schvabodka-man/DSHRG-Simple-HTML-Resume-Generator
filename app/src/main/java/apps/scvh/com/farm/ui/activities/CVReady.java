package apps.scvh.com.farm.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.tom_roush.pdfbox.pdmodel.PDDocument;

import net.rdrei.android.dirchooser.DirectoryChooserActivity;
import net.rdrei.android.dirchooser.DirectoryChooserConfig;

import java.io.File;

import apps.scvh.com.farm.R;
import apps.scvh.com.farm.util.CV;
import apps.scvh.com.farm.util.CVRenderer;
import apps.scvh.com.farm.util.FSWorker;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CVReady extends AppCompatActivity {


    private PDDocument cv;
    private String folderPath;

    private CVRenderer renderer;
    private FSWorker fsWorker;

    @BindView(R.id.file_name)
    EditText fileName;
    @BindView(R.id.save)
    Button save;
    @BindView(R.id.save_and_open)
    Button saveAndOpen;
    @BindView(R.id.open)
    Button open;
    @BindView(R.id.folder)
    Button folder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvready);
        CV cvBasic = (CV) getIntent().getExtras().getSerializable("cv");
        renderer = new CVRenderer(this);
        cv = renderer.renderCV(cvBasic);
        fsWorker = new FSWorker(this);
        ButterKnife.bind(this);
        initListeners();
    }

    private void initListeners() {
        save.setOnClickListener(v -> {
            File documents = new File(folderPath);
            File file = new File(documents, fileName.getText().toString());
            fsWorker.saveDocument(cv, file);
        });
        open.setOnClickListener(v -> {
            fsWorker.previewDocument(cv);
        });
        saveAndOpen.setOnClickListener(v -> {
            File documents = new File(folderPath);
            File file = new File(documents, fileName.getText().toString());
            fsWorker.saveAndOpenDocument(cv, file);
        });
        folder.setOnClickListener(v -> {
            Intent intent = new Intent(this, DirectoryChooserActivity.class);
            DirectoryChooserConfig config = DirectoryChooserConfig.builder().initialDirectory
                    (getString(R.string.file_path)).allowNewDirectoryNameModification(true)
                    .allowReadOnlyDirectory(false).newDirectoryName(getString(R.string
                            .new_folder)).build();
            intent.putExtra(DirectoryChooserActivity.EXTRA_CONFIG, config);
            startActivityForResult(intent, 0);
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == DirectoryChooserActivity.RESULT_CODE_DIR_SELECTED) {
                folderPath = data
                        .getStringExtra(DirectoryChooserActivity.RESULT_SELECTED_DIR);
            }
        }
    }
}
