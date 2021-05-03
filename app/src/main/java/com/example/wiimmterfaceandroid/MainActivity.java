package com.example.wiimmterfaceandroid;



import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.room.Room;

import android.os.Bundle;

import com.example.wiimmterfaceandroid.database.AppDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentContainerView fcInput = findViewById(R.id.recent_friend_codes_fragment);
        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "friend-codes-db").build();

        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.friend_code_input_fragment, WatchCodeFragment.class, null)
                    .setReorderingAllowed(true)
                    .commit();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.recent_friend_codes_fragment, RecentCodesFragment.class, null)
                    .setReorderingAllowed(true)
                    .commit();
        }
    }
}