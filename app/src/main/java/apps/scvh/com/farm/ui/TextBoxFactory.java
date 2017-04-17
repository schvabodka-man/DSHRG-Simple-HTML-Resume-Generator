package apps.scvh.com.farm.ui;


import android.content.Context;
import android.widget.EditText;

public class TextBoxFactory {

    private Context context;

    public TextBoxFactory(Context context) {
        this.context = context;
    }

    public EditText createTextBox(int stringId) {
        EditText editText = new EditText(context);
        editText.setHint(stringId);
        return editText;
    }
}
