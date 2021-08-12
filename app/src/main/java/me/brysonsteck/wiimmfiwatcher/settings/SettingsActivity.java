package me.brysonsteck.wiimmfiwatcher.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import me.brysonsteck.wiimmfiwatcher.R;

public class SettingsActivity extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootkey) {
        super.onCreate(savedInstanceState);
        setPreferencesFromResource(R.xml.preferences, rootkey);
    }
}
