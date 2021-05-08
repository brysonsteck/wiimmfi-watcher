package me.brysonsteck.wiimmfiwatcher;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableArrayList;
import me.brysonsteck.wiimmfiwatcher.database.AppDatabase;
import me.brysonsteck.wiimmfiwatcher.model.FriendCode;

public class MainActivity extends AppCompatActivity {
    ObservableArrayList<FriendCode> recentFCList = new ObservableArrayList<>();
    AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        FragmentContainerView fcInput = findViewById(R.id.room_fragment);
//        this.database = Room.databaseBuilder(this, AppDatabase.class, "friend-codes-db").build();
//        new Thread(() -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            recentFCList.addAll(database.getFriendCodeDao().getAll());
//        }).start();



        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.friend_code_input_fragment, new WatchCodeFragment(), null)
                    .setReorderingAllowed(true)
                    .commit();


        }
        setContentView(R.layout.activity_main);
    }
}