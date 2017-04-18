package apps.scvh.com.farm.ui.activities;

import android.content.ClipData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

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

    private TextView view; //meh

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvlayout);
        ButterKnife.bind(this);
        //for fancy init
        TextView[] arrayOfViews = {name, links, experience, education, projects, primary,
                secondary, other};
        ArrayList<TextView> views = new ArrayList<>(Arrays.asList(arrayOfViews));
        initDrags(views);
    }


    private void initDrags(ArrayList<TextView> views) {
        Iterator<TextView> listIterator = views.iterator();
        while (listIterator.hasNext()) {
            view = listIterator.next();
            view.setOnTouchListener((v, event) -> {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                            v);
                    view.startDrag(data, shadowBuilder, view, 0);
                    return true;
                } else {
                    return false;
                }
            });
            view.setOnDragListener((v, event) -> {
                if (event.getAction() == DragEvent.ACTION_DRAG_ENDED) {
                    view.setX(event.getX());
                    view.setY(event.getY());
                }
                return true;
            });
        }
    }
}
