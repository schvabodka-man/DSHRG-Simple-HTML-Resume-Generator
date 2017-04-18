package apps.scvh.com.farm.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import apps.scvh.com.farm.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CVLayout extends AppCompatActivity {

    @BindView(R.id.name_position)
    TextView name;
    @BindView(R.id.links_position)
    TextView links;
    @BindView(R.id.experience_position)
    TextView experience;
    @BindView(R.id.education_position)
    TextView education;
    @BindView(R.id.projects_position)
    TextView projects;
    @BindView(R.id.primary_position)
    TextView primary;
    @BindView(R.id.secondary_position)
    TextView secondary;
    @BindView(R.id.other_position)
    TextView other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvlayout);
        ButterKnife.bind(this);
        initDrags();
    }

    private final class DraggableViews implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            float x = event.getRawX();
            float y = event.getRawY();
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) v.getLayoutParams();
            return true;
        }
    }

    private void initDrags() {
        name.setOnTouchListener(new DraggableViews());
        links.setOnTouchListener(new DraggableViews());
        experience.setOnTouchListener(new DraggableViews());
        education.setOnTouchListener(new DraggableViews());
        projects.setOnTouchListener(new DraggableViews());
        primary.setOnTouchListener(new DraggableViews());
        secondary.setOnTouchListener(new DraggableViews());
        other.setOnTouchListener(new DraggableViews());
    }
}
