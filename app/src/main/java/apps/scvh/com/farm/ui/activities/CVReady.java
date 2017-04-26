package apps.scvh.com.farm.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.tom_roush.pdfbox.pdmodel.PDDocument;

import net.rdrei.android.dirchooser.DirectoryChooserActivity;
import net.rdrei.android.dirchooser.DirectoryChooserConfig;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.inject.Named;

import apps.scvh.com.farm.R;
import apps.scvh.com.farm.ui.workers.ViewChecker;
import apps.scvh.com.farm.util.FSStates;
import apps.scvh.com.farm.util.cv.CV;
import apps.scvh.com.farm.util.cv.CVHolder;
import apps.scvh.com.farm.util.di.DaggerAppComponent;
import apps.scvh.com.farm.util.di.ObjectProvider;
import apps.scvh.com.farm.util.workers.CVRenderer;
import apps.scvh.com.farm.util.workers.FSWorker;

@EActivity(R.layout.activity_cvready)
public class CVReady extends AppCompatActivity {

    private PDDocument cv;
    private String folderPath;

    @Inject
    @Named("Renderer")
    CVRenderer renderer;
    @Inject
    @Named("FSWorker")
    FSWorker fsWorker;
    @Inject
    @Named("viewChecker")
    ViewChecker viewChecker;

    @ViewById(R.id.file_name)
    EditText fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvready);
        DaggerAppComponent.builder().objectProvider(new ObjectProvider
                (this)).build().inject(this);
        try {
            cv = renderer.execute((CV) getIntent().getExtras().getSerializable("cv")).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Click(R.id.save)
    void saveCV() {
        if (!isFilePathNull()) {
            File file = new File(new File(folderPath), fileName.getText().toString() + getString
                    (R.string.file_type));
            fsWorker.execute(new CVHolder(FSStates.WRITE, file, cv));
        } else {
            Toast.makeText(this, getString(R.string.null_file), Toast.LENGTH_SHORT).show();
        }
    }

    @Click(R.id.open)
    void previewCV() {
        fsWorker.execute(new CVHolder(FSStates.PREVIEW, null, cv));
    }

    @Click(R.id.save_and_open)
    void saveAndOpenCV() {
        if (!isFilePathNull()) {
            File file = new File(new File(folderPath), fileName.getText().toString() + getString
                    (R.string.file_type));
            fsWorker.execute(new CVHolder(FSStates.WRITEANDOPEN, file, cv));
        } else {
            Toast.makeText(this, getString(R.string.null_file), Toast.LENGTH_SHORT).show();
        }
    }

    @Click(R.id.folder)
    void chooseFolder() {
        Intent intent = new Intent(this, DirectoryChooserActivity.class);
        DirectoryChooserConfig config = DirectoryChooserConfig.builder().initialDirectory
                (getString(R.string.file_path)).allowNewDirectoryNameModification(true)
                .allowReadOnlyDirectory(false).newDirectoryName(getString(R.string
                        .new_folder)).build();
        intent.putExtra(DirectoryChooserActivity.EXTRA_CONFIG, config);
        startActivityForResult(intent, 0);
    }


    private boolean isFilePathNull() {
        if (viewChecker.isEditTextEmpty(fileName) || folderPath == null) {
            return true;
        } else {
            return false;
        }
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
