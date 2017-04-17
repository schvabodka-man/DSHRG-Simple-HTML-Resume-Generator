package apps.scvh.com.farm.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import apps.scvh.com.farm.R;
import apps.scvh.com.farm.ui.TextBoxFactory;
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
            case R.id.cv_layout:
                return true;
            case R.id.cv_parts:
                Intent intent = new Intent(this, CVSettings.class);
                startActivity(intent);
                return true;
            default:
                return true;
        }
    }


    public void initClickHandlers() {
        findViewById(R.id.create_experience).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                experience.addView(textBoxFactory.createTextBox(R.string.experience));
            }
        });
        findViewById(R.id.create_education).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                education.addView(textBoxFactory.createTextBox(R.string.education));
            }
        });
        findViewById(R.id.create_project).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projects.addView(textBoxFactory.createTextBox(R.string.personal_projects));
            }
        });
        findViewById(R.id.create_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                links.addView(textBoxFactory.createTextBox(R.string.link));
            }
        });
        findViewById(R.id.create_primary_skill).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                primary.addView(textBoxFactory.createTextBox(R.string.skill));
            }
        });
        findViewById(R.id.create_secondary_skill).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                second.addView(textBoxFactory.createTextBox(R.string.skill));
            }
        });
        findViewById(R.id.create_other_skill).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                other.addView(textBoxFactory.createTextBox(R.string.skill));
            }
        });
    }
}
