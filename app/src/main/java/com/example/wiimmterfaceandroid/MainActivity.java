package com.example.wiimmterfaceandroid;



import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.room.Room;

import android.os.Bundle;

import com.example.wiimmterfaceandroid.database.AppDatabase;
import com.example.wiimmterfaceandroid.model.FriendCode;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<FriendCode> recentFCList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentContainerView fcInput = findViewById(R.id.room_fragment);
        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "friend-codes-db").build();
        recentFCList = database.getFriendCodeDao().getAll();

        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.friend_code_input_fragment, new WatchCodeFragment(database, recentFCList), null)
                    .setReorderingAllowed(true)
                    .commit();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.room_fragment, new RecentCodesFragment(database, recentFCList), null)
                    .setReorderingAllowed(true)
                    .commit();
        }


    }
}