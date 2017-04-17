package apps.scvh.com.farm.activities;


import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import apps.scvh.com.farm.R;


public class CVSettings extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvsettings);
        PreferenceManager.setDefaultValues(this, R.xml.prefs, false);
        addPreferencesFromResource(R.xml.prefs);
    }
}
