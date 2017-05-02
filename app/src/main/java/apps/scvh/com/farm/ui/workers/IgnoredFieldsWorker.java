package apps.scvh.com.farm.ui.workers;


import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

import apps.scvh.com.farm.R;

/**
 * ENG class i'm using for checking if field is used or not
 * RUS
 */
public class IgnoredFieldsWorker {

    private Context context;
    private SharedPreferences prefs;

    public IgnoredFieldsWorker(Context context, SharedPreferences prefs) {
        this.context = context;
        this.prefs = prefs;
    }

    /**
     * ENG fancy method i'm using for getting list of ignored fields
     * RUS метод который я юзаю чтобы получить список заигноренных полей
     * @return the list of ignored fields
     */
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
