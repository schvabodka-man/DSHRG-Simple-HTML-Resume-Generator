package apps.scvh.com.farm.util.workers;


import android.content.Context;
import android.content.SharedPreferences;

import apps.scvh.com.farm.R;
import apps.scvh.com.farm.util.enums.CVFields;
import apps.scvh.com.farm.util.enums.GravityList;
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

    private int getFontSize(TextTypes type) {
        switch (type) {
            case BIG_TEXT:
                return preferences.getInt(context.getString(R.string.big_text_key), 23);
            case SMALL_TEXT:
                return preferences.getInt(context.getString(R.string.small_text), 16);
            default:
                return 14;
        }
    }

    private GravityList getGravity(CVFields field) {
        switch (field) {
            case EDUCATION:
                return getGravityFromString(preferences.getString(context.getString(R.string
                        .education_position_key), context.getString(R.string
                        .center_key)));
            case EXPERIENCE:
                return getGravityFromString(preferences.getString(context.getString(R.string
                        .experience_position_key), context.getString(R.string
                        .center_key)));
            case LINKS:
                return getGravityFromString(preferences.getString(context.getString(R.string
                        .links_position_key), context.getString(R.string
                        .center_key)));
            case PROJECTS:
                return getGravityFromString(preferences.getString(context.getString(R.string
                        .projects_position_key), context.getString(R.string
                        .center_key)));
            case PRIMARY_SKILLS:
                return getGravityFromString(preferences.getString(context.getString(R.string
                        .primary_skills_position_key), context.getString(R.string
                        .center_key)));
            case SECONDARY_SKILLS:
                return getGravityFromString(preferences.getString(context.getString(R.string
                        .secondary_skills_position_key), context.getString(R.string
                        .center_key)));
            case OTHER_SKILLS:
                return getGravityFromString(preferences.getString(context.getString(R.string
                        .other_skills_position_key), context.getString(R.string
                        .center_key)));
            case NAME:
                return getGravityFromString(preferences.getString(context.getString(R.string
                        .name_position_key), context.getString(R.string
                        .center_key)));
            case ABOUT:
                return getGravityFromString(preferences.getString(context.getString(R.string
                        .about_position_key), context.getString(R.string
                        .center_key)));
            default:
                return GravityList.CENTER;
        }

    }

    private GravityList getGravityFromString(String gravity) {
        if (gravity.equals(context.getString(R.string.left_key))) {
            return GravityList.LEFT;
        } else if (gravity.equals(context.getString(R.string.right_key))) {
            return GravityList.RIGHT;
        } else {
            return GravityList.CENTER;
        }
    }

}
