package com.example.wiimmterfaceandroid;



import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.FragmentContainerView;
import androidx.room.Room;

import android.os.Bundle;

import com.example.wiimmterfaceandroid.database.AppDatabase;
import com.example.wiimmterfaceandroid.model.FriendCode;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ObservableArrayList<FriendCode> recentFCList = new ObservableArrayList<>();
    AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentContainerView fcInput = findViewById(R.id.room_fragment);
        this.database = Room.databaseBuilder(this, AppDatabase.class, "friend-codes-db").build();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            recentFCList.addAll(database.getFriendCodeDao().getAll());
        }).start();



        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.friend_code_input_fragment, new WatchCodeFragment(), null)
                    .setReorderingAllowed(true)
                    .commit();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.room_fragment, new RecentCodesFragment(), null)
                    .setReorderingAllowed(true)
                    .commit();
        }


    }
}