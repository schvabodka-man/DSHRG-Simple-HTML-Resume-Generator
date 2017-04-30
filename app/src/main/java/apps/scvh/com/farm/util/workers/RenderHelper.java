package apps.scvh.com.farm.util.workers;


import android.content.Context;
import android.content.SharedPreferences;

import apps.scvh.com.farm.R;
import apps.scvh.com.farm.util.enums.CVFields;

public class RenderHelper {

    private Context context;
    private SharedPreferences preferences;

    public RenderHelper(Context context, SharedPreferences preferences) {
        this.context = context;
        this.preferences = preferences;
    }

    String getStringForField(CVFields flag) {
        switch (flag) {
            case EDUCATION:
                return context.getString(R.string.education);
            case EXPERIENCE:
                return context.getString(R.string.experience);
            case LINKS:
                return context.getString(R.string.links);
            case PROJECTS:
                return context.getString(R.string.personal_projects);
            case PRIMARY_SKILLS:
                return context.getString(R.string.primary_skills);
            case SECONDARY_SKILLS:
                return context.getString(R.string.secondary_skills);
            case OTHER_SKILLS:
                return context.getString(R.string.other_skills);
        }
        return ""; //It's here JFL
    }

    private int getFontSize() {
        return 0;
    }
}
