package apps.scvh.com.farm.util.workers;


import android.content.Context;
import android.content.SharedPreferences;

import apps.scvh.com.farm.R;
import apps.scvh.com.farm.util.enums.CVFields;
import apps.scvh.com.farm.util.enums.TextTypes;

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

    String getDivWithClassForField(CVFields flag) {
        switch (flag) {
            case EDUCATION:
                return context.getString(R.string.education_div);
            case EXPERIENCE:
                return context.getString(R.string.experience_div);
            case LINKS:
                return context.getString(R.string.links_div);
            case PROJECTS:
                return context.getString(R.string.projects_div);
            case PRIMARY_SKILLS:
                return context.getString(R.string.primary_skills_div);
            case SECONDARY_SKILLS:
                return context.getString(R.string.secondary_skills_div);
            case OTHER_SKILLS:
                return context.getString(R.string.other_skills_div);
            case NAME:
                return context.getString(R.string.name_div);
            case ABOUT:
                return context.getString(R.string.about_div);
        }
        return "";
    }

    int getFontSize(TextTypes type) {
        switch (type) {
            case BIG_TEXT:
                return Integer.parseInt(preferences.getString(context.getString(R.string
                        .big_text_key), String.valueOf(23)));
            case SMALL_TEXT:
                return Integer.parseInt(preferences.getString(context.getString(R.string
                        .small_text_key), String.valueOf(23)));
            default:
                return 14;
        }
    }

    String getGravity(CVFields field) {
        switch (field) {
            case EDUCATION:
                return preferences.getString(context.getString(R.string
                        .education_position_key), context.getString(R.string
                        .center_key));
            case EXPERIENCE:
                return preferences.getString(context.getString(R.string
                        .experience_position_key), context.getString(R.string
                        .center_key));
            case LINKS:
                return preferences.getString(context.getString(R.string
                        .links_position_key), context.getString(R.string
                        .center_key));
            case PROJECTS:
                return preferences.getString(context.getString(R.string
                        .projects_position_key), context.getString(R.string
                        .center_key));
            case PRIMARY_SKILLS:
                return preferences.getString(context.getString(R.string
                        .primary_skills_position_key), context.getString(R.string
                        .center_key));
            case SECONDARY_SKILLS:
                return preferences.getString(context.getString(R.string
                        .secondary_skills_position_key), context.getString(R.string
                        .center_key));
            case OTHER_SKILLS:
                return preferences.getString(context.getString(R.string
                        .other_skills_position_key), context.getString(R.string
                        .center_key));
            case NAME:
                return preferences.getString(context.getString(R.string
                        .name_position_key), context.getString(R.string
                        .center_key));
            case ABOUT:
                return preferences.getString(context.getString(R.string
                        .about_position_key), context.getString(R.string
                        .center_key));
            default:
                return context.getString(R.string.right_key);
        }

    }


}
