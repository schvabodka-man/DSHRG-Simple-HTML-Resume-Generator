package apps.scvh.com.farm.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jmedeisis.draglinearlayout.DragLinearLayout;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import apps.scvh.com.farm.R;

@EActivity(R.layout.activity_cvlayout)
public class CVLayout extends AppCompatActivity {

    @ViewById(R.id.components)
    DragLinearLayout dragLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvlayout);
        initDraggableLayout();
    }

    private void initDraggableLayout() {
        View child;
        for (int i = 0; i < dragLinearLayout.getChildCount(); i++) {
            child = dragLinearLayout.getChildAt(i);
            dragLinearLayout.setViewDraggable(child, dragLinearLayout);
        }
    }
}
