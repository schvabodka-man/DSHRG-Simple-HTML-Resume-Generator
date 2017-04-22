package apps.scvh.com.farm.ui;


import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class ViewChecker {

    public boolean isEditTextEmpty(View view) {
        EditText text = (EditText) view;
        if (text.getText().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isLinearLayoutEmpty(View view) {
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
}
