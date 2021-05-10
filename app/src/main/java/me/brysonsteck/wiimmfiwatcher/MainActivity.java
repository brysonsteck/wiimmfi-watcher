package me.brysonsteck.wiimmfiwatcher;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableArrayList;
import androidx.room.Room;

import me.brysonsteck.wiimmfiwatcher.database.AppDatabase;
import me.brysonsteck.wiimmfiwatcher.model.FriendCode;

public class MainActivity extends AppCompatActivity {
    ObservableArrayList<FriendCode> recentFCList = new ObservableArrayList<>();
    AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    .replace(R.id.friend_code_input_fragment, new AboutFragment(), null)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();
        });

    }
}