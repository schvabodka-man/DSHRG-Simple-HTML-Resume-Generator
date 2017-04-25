package apps.scvh.com.farm.util.workers;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;

import apps.scvh.com.farm.R;

public class IgnoredFieldsWorker {

    private Context context;
    private SharedPreferences prefs;

    public IgnoredFieldsWorker(Context context) {
        this.context = context;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public ArrayList<Integer> getListofIgnoredFields() {
        ArrayList<Integer> ignoredViews = new ArrayList<>();
        if (!prefs.getBoolean(context.getString(R.string.experience_key), true)) {
            ignoredViews.add(R.id.experience);
            ignoredViews.add(R.id.create_experience);
        }
        if (!prefs.getBoolean(context.getString(R.string.education_key), true)) {
            ignoredViews.add(R.id.education);
            ignoredViews.add(R.id.create_education);
        }
        if (!prefs.getBoolean(context.getString(R.string.links_key), true)) {
            ignoredViews.add(R.id.link);
            ignoredViews.add(R.id.create_link);
        }
        if (!prefs.getBoolean(context.getString(R.string.personal_projects_key), true)) {
            ignoredViews.add(R.id.project);
            ignoredViews.add(R.id.create_project);
        }
        if (!prefs.getBoolean(context.getString(R.string.skills_key), true)) {
            ignoredViews.add(R.id.create_other_skill);
            ignoredViews.add(R.id.create_primary_skill);
            ignoredViews.add(R.id.create_secondary_skill);
            ignoredViews.add(R.id.primary_skill);
            ignoredViews.add(R.id.secondary_skill);
            ignoredViews.add(R.id.other_skill);
            ignoredViews.add(R.id.primary_skill_textview);
            ignoredViews.add(R.id.secondary_skill_textview);
            ignoredViews.add(R.id.other_skill_textview);
        }
        return ignoredViews;
    }

}
