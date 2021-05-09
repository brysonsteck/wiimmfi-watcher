package me.brysonsteck.wiimmfiwatcher;


import android.content.ClipData;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import androidx.sqlite.db.SimpleSQLiteQuery;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import me.brysonsteck.wiimmfiwatcher.database.AppDatabase;
import me.brysonsteck.wiimmfiwatcher.model.FriendCode;
import me.brysonsteck.wiimmfiwatcher.viewmodel.FriendCodeViewModel;

public class MainActivity extends AppCompatActivity {
    ObservableArrayList<FriendCode> recentFCList = new ObservableArrayList<>();
    AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.friend_code_input_fragment, new WatchCodeFragment(), null)
                    .setReorderingAllowed(true)
                    .commit();


        }
        setContentView(R.layout.activity_main);
        database = Room.databaseBuilder(this, AppDatabase.class, "friend-codes-db").build();
        View aboutButton = findViewById(R.id.about_button);
        ExtendedFloatingActionButton clearButton = findViewById(R.id.clear_button);
        FriendCodeViewModel viewModel = new ViewModelProvider(MainActivity.this).get(FriendCodeViewModel.class);
        clearButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                new Thread(() -> {
                    database.clearAllTables();
                    database.query(new SimpleSQLiteQuery("DELETE FROM friendcode"));
                });
            }
        });


        aboutButton.setOnClickListener((about) -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.friend_code_input_fragment, new AboutFragment(), null)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();
        });

    }
}