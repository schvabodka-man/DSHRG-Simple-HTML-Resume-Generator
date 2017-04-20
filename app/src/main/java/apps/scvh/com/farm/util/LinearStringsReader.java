package apps.scvh.com.farm.util;


import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Iterator;

public class LinearStringsReader {

    public ArrayList<String> readStrings(LinearLayout layout) {
        Iterator<View> iterator = layout.getTouchables().iterator();
        ArrayList<String> strings = new ArrayList<>();
        EditText text;
        while (iterator.hasNext()) {
            text = (EditText) iterator.next();
            if (text.getText().toString().isEmpty()) {
                break;
            } else {
                strings.add(text.getText().toString());
            }
        }
        return strings;
    }
}
