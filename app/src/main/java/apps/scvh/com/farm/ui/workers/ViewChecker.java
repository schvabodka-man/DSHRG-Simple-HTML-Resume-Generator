package apps.scvh.com.farm.ui.workers;


import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;


/**
 * ENG class for checking different stuff
 * RUS простой класс с помощью которого я чекаю пустые ли вьюхи
 */
public class ViewChecker {

    /**
     * ENG this one is checking if edittext text is null or not
     * RUS этот метод чекает пустой ли текст в эдиттексте
     * @param view edittext
     * @return if empty or not
     */
    public boolean isEditTextEmpty(View view) {
        EditText text = (EditText) view;
        if (text.getText().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * ENG this one is checking if linear layout have viewws or not
     * RUS этот метод чекает имеет ли леяут вьюзи
     * @param view layout
     * @return have views or not
     */
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
