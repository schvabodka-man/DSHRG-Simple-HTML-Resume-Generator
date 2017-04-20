package apps.scvh.com.farm.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;

import apps.scvh.com.farm.R;
import apps.scvh.com.farm.ui.TextBoxFactory;
import apps.scvh.com.farm.util.CV;
import apps.scvh.com.farm.util.CVBuilder;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CVInput extends AppCompatActivity {

    @BindView(R.id.experience)
    LinearLayout experience;
    @BindView(R.id.education)
    LinearLayout education;
    @BindView(R.id.project)
    LinearLayout projects;
    @BindView(R.id.primary_skill)
    LinearLayout primary;
    @BindView(R.id.secondary_skill)
    LinearLayout second;
    @BindView(R.id.other_skill)
    LinearLayout other;
    @BindView(R.id.link)
    LinearLayout links;

    private TextBoxFactory textBoxFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvinput);
        ButterKnife.bind(this);
        initClickHandlers();
        textBoxFactory = new TextBoxFactory(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cv_parts:
                startActivity(new Intent(this, CVSettings.class));
                return true;
            default:
                return true;
        }
    }


    private void initClickHandlers() {
        findViewById(R.id.create_experience).setOnClickListener(v -> experience.addView
                (textBoxFactory.createTextBox(R.string.experience)));
        findViewById(R.id.create_education).setOnClickListener(v -> education.addView
                (textBoxFactory.createTextBox(R.string.education)));
        findViewById(R.id.create_project).setOnClickListener(v -> projects.addView(textBoxFactory
                .createTextBox(R.string.personal_projects)));
        findViewById(R.id.create_link).setOnClickListener(v -> links.addView(textBoxFactory
                .createTextBox(R.string.link)));
        findViewById(R.id.create_primary_skill).setOnClickListener(v -> primary.addView
                (textBoxFactory.createTextBox(R.string.skill)));
        findViewById(R.id.create_secondary_skill).setOnClickListener(v -> second.addView
                (textBoxFactory.createTextBox(R.string.skill)));
        findViewById(R.id.create_other_skill).setOnClickListener(v -> other.addView
                (textBoxFactory.createTextBox(R.string.skill)));
        findViewById(R.id.create_cv).setOnClickListener(v -> {
            Intent intent = new Intent(this, CVReady.class);
            intent.putExtra("cv", makeCV());
            startActivity(intent);
        });
    }

    private CV makeCV() {
        CVBuilder builder = new CVBuilder();
        builder.setAbout((EditText) findViewById(R.id.about)).
                setFullName((EditText) findViewById(R.id.full_name)).
                setEducation((LinearLayout) findViewById(R.id.education)).
                setExperience((LinearLayout) findViewById(R.id.experience)).
                setLinks((LinearLayout) findViewById(R.id.link)).
                setProjects((LinearLayout) findViewById(R.id.project)).
                setPrimarySkills((LinearLayout) findViewById(R.id.primary_skill)).
                setSecondarySkills((LinearLayout) findViewById(R.id.secondary_skill)).
                setOtherSkills((LinearLayout) findViewById(R.id.other_skill));
        return builder.buildCV();
    }
}
