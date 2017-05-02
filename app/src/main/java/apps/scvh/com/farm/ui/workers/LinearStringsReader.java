package apps.scvh.com.farm.ui.workers;


import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * ENG class i'm for getting list of strings from edittext's in linear layout
 * RUS класс который я юзаю чтобы получать список стрингов из эдиттекстов в леяуте
 */
public class LinearStringsReader {

    /**
     * ENG Read strings from linear layout
     * RUS собственно читает строки
     * @param layout the linear layout
     * @return the array list of strings
     */
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
