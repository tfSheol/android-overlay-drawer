package fr.sheol.overlay.drawer.app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import fr.sheol.overlay.drawer.R;

/**
 * Created by Sheol on 18/02/2017.
 */

public class SettingFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        getPreferenceManager().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // TODO: 24/02/2017 send broadcast to correct part of drawer
    }
}
