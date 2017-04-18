package apps.scvh.com.farm.ui.activities;

import android.content.ClipData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
        TextView view;
        while (listIterator.hasNext()) {
            view = listIterator.next();
            view.setOnTouchListener(new TouchListener());
            view.setOnDragListener(new DragListner());
        }
    }

    private final class DragListner implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DROP:
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    LinearLayout container = (LinearLayout) v;
                    container.addView(view);
                    view.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
            return true;
        }
    }

    private final class TouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                        v);
                v.startDrag(data, shadowBuilder, v, 0);
                v.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }

}
