package apps.scvh.com.farm.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import apps.scvh.com.farm.R;
import apps.scvh.com.farm.ui.workers.TextBoxFactory;
import apps.scvh.com.farm.ui.workers.ViewChecker;
import apps.scvh.com.farm.util.cv.CV;
import apps.scvh.com.farm.util.cv.CVBuilder;
import apps.scvh.com.farm.util.di.DaggerAppComponent;
import apps.scvh.com.farm.util.di.ObjectProvider;
import apps.scvh.com.farm.util.workers.IgnoredFieldsWorker;

@EActivity(R.layout.activity_cvinput)
public class CVInput extends AppCompatActivity {

    @ViewById(R.id.experience)
    LinearLayout experience;
    @ViewById(R.id.education)
    LinearLayout education;
    @ViewById(R.id.project)
    LinearLayout projects;
    @ViewById(R.id.primary_skill)
    LinearLayout primary;
    @ViewById(R.id.secondary_skill)
    LinearLayout second;
    @ViewById(R.id.other_skill)
    LinearLayout other;
    @ViewById(R.id.link)
    LinearLayout links;
    @ViewById(R.id.container)
    LinearLayout container;

    @Inject
    @Named("textBoxFactory")
    TextBoxFactory textBoxFactory;
    @Inject
    @Named("IgnoreHelper")
    IgnoredFieldsWorker ignoreHelper;
    @Inject
    @Named("viewChecker")
    ViewChecker checker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvinput);
        DaggerAppComponent.builder().objectProvider(new ObjectProvider
                (this)).build().inject(this);
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
            case R.id.cv_layout:
                startActivity(new Intent(this, CVLayout_.class));
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

    @Click(R.id.create_experience)
    void addExperience() {
        experience.addView
                (textBoxFactory.createTextBox(R.string.experience));
    }

    @Click(R.id.create_education)
    void addEducation() {
        education.addView
                (textBoxFactory.createTextBox(R.string.education));
    }

    @Click(R.id.create_project)
    void addProject() {
        projects.addView(textBoxFactory
                .createTextBox(R.string.personal_projects));
    }

    @Click(R.id.create_link)
    void addLink() {
        links.addView(textBoxFactory
                .createTextBox(R.string.link));
    }

    @Click(R.id.create_primary_skill)
    void addPrimary() {
        primary.addView
                (textBoxFactory.createTextBox(R.string.skill));
    }

    @Click(R.id.create_secondary_skill)
    void addSecondary() {
        second.addView
                (textBoxFactory.createTextBox(R.string.skill));
    }

    @Click(R.id.create_other_skill)
    void addOtherSkill() {
        other.addView
                (textBoxFactory.createTextBox(R.string.skill));
    }

    @Click(R.id.create_cv)
    void composeCV() {
        boolean emptyOrNot = isAllNonEmpty();
        if (emptyOrNot) {
            Intent intent = new Intent(this, CVReady_.class);
            intent.putExtra("cv", makeCV());
            startActivity(intent);
        } else {
            Toast.makeText(this, getString(R.string.null_field), Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isAllNonEmpty() {
        View view;
        for (int i = 0; i < container.getChildCount(); i++) {
            view = container.getChildAt(i);
            if (view.getVisibility() == View.VISIBLE && view instanceof EditText) {
                if (checker.isEditTextEmpty(view)) {
                    return false;
                }
            } else if (view.getVisibility() == View.VISIBLE && view instanceof LinearLayout) {
                if (checker.isLinearLayoutEmpty(view)) {
                    return false;
                }
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
