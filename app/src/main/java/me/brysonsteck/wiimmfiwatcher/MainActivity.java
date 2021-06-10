package me.brysonsteck.wiimmfiwatcher;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import me.brysonsteck.wiimmfiwatcher.database.AppDatabase;
import me.brysonsteck.wiimmfiwatcher.fragments.AboutFragment;
import me.brysonsteck.wiimmfiwatcher.fragments.WatchCodeFragment;

public class MainActivity extends AppCompatActivity {
    AppDatabase database;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        View aboutButton = findViewById(R.id.about_button);
        if (savedInstanceState == null) {
            aboutButton.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.friend_code_input_fragment, new WatchCodeFragment(), null)
                    .setReorderingAllowed(true)
                    .commit();
        }

        database = Room.databaseBuilder(this, AppDatabase.class, "friend-codes-db").build();

        aboutButton.setOnClickListener((about) -> {
            aboutButton.setVisibility(View.INVISIBLE);
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(
                            R.anim.slide_in,
                            R.anim.fade_out,
                            R.anim.fade_in,
                            R.anim.slide_out)
                    .replace(R.id.friend_code_input_fragment, new AboutFragment(), null)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();
        });

    }
    @Override
    protected void onStop() {
        super.onStop();
    }
}