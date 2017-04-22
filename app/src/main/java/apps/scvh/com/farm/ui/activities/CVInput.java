package apps.scvh.com.farm.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import apps.scvh.com.farm.R;
import apps.scvh.com.farm.ui.TextBoxFactory;
import apps.scvh.com.farm.util.CV;
import apps.scvh.com.farm.util.CVBuilder;
import apps.scvh.com.farm.util.IgnoredFieldsWorker;
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
    @BindView(R.id.create_experience)
    Button createExperience;
    @BindView(R.id.create_education)
    Button createEducation;
    @BindView(R.id.create_project)
    Button createProject;
    @BindView(R.id.create_link)
    Button createLink;
    @BindView(R.id.create_primary_skill)
    Button createPrimarySkill;
    @BindView(R.id.create_secondary_skill)
    Button createSecondarySkill;
    @BindView(R.id.create_other_skill)
    Button createOtherSkill;
    @BindView(R.id.create_cv)
    Button createCV;
    @BindView(R.id.container)
    LinearLayout container;

    private TextBoxFactory textBoxFactory;
    private IgnoredFieldsWorker ignoreHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvinput);
        ButterKnife.bind(this);
        initClickHandlers();
        textBoxFactory = new TextBoxFactory(this);
        ignoreHelper = new IgnoredFieldsWorker(this);
        ignoreFields(ignoreHelper.getListofIgnoredFields());
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

    @Override
    protected void onResume() {
        super.onResume();
        ignoreFields(ignoreHelper.getListofIgnoredFields());
    }

    private void ignoreFields(ArrayList<Integer> ignored) {
        View view;
        for (int i = 0; i < container.getChildCount(); i++) {
            view = container.getChildAt(i);
            if (ignored.contains(view.getId())) {
                view.setVisibility(View.GONE);
            } else {
                view.setVisibility(View.VISIBLE);
            }
        }
    }

    private void initClickHandlers() {
        createExperience.setOnClickListener(v -> experience.addView
                (textBoxFactory.createTextBox(R.string.experience)));
        createEducation.setOnClickListener(v -> education.addView
                (textBoxFactory.createTextBox(R.string.education)));
        createProject.setOnClickListener(v -> projects.addView(textBoxFactory
                .createTextBox(R.string.personal_projects)));
        createLink.setOnClickListener(v -> links.addView(textBoxFactory
                .createTextBox(R.string.link)));
        createPrimarySkill.setOnClickListener(v -> primary.addView
                (textBoxFactory.createTextBox(R.string.skill)));
        createSecondarySkill.setOnClickListener(v -> second.addView
                (textBoxFactory.createTextBox(R.string.skill)));
        createOtherSkill.setOnClickListener(v -> other.addView
                (textBoxFactory.createTextBox(R.string.skill)));
        createCV.setOnClickListener(v -> {
            boolean emptyOrNot = isAllNonEmpty();
            if (emptyOrNot) {
                Intent intent = new Intent(this, CVReady.class);
                intent.putExtra("cv", makeCV());
                startActivity(intent);
            } else {
                Toast.makeText(this, getString(R.string.null_field), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isAllNonEmpty() {
        View view;
        for (int i = 0; i < container.getChildCount(); i++) {
            view = container.getChildAt(i);
            if (view.getVisibility() == View.VISIBLE && view instanceof EditText) {
                if (isEditTextEmpty(view)) {
                    return false;
                }
            } else if (view.getVisibility() == View.VISIBLE && view instanceof LinearLayout) {
                if (isLinearLayoutEmpty(view)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isEditTextEmpty(View view) {
        EditText text = (EditText) view;
        if (text.getText().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isLinearLayoutEmpty(View view) {
        LinearLayout layout = (LinearLayout) view;
        EditText text;
        for (int i = 0; i < layout.getChildCount(); i++) {
            text = (EditText) layout.getChildAt(i);
            if (!isEditTextEmpty(text)) {
                return false;
            }
        }
        return true;
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
