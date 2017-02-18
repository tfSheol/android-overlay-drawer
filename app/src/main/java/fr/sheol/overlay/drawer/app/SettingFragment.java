package fr.sheol.overlay.drawer.app;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import fr.sheol.overlay.drawer.R;

/**
 * Created by Sheol on 18/02/2017.
 */

public class SettingFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
