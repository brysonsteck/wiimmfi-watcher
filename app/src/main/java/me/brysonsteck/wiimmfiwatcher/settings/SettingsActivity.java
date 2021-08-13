package me.brysonsteck.wiimmfiwatcher.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import me.brysonsteck.wiimmfiwatcher.R;
import me.brysonsteck.wiimmfiwatcher.fragments.WatchCodeFragment;

public class SettingsActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        if (savedInstanceState != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.settings_fragment_view, new SettingsMainFragment(), null)
                    .setReorderingAllowed(true)
                    .commit();
        }
    }
}
