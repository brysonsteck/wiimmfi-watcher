package me.brysonsteck.wiimmfiwatcher.wiimmfi;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import org.jsoup.*;

import java.util.ArrayList;

import me.brysonsteck.wiimmfiwatcher.R;

public class WiimmfiActivity extends AppCompatActivity {
    ArrayList<Player> players = new ArrayList<>();
    final String[] playerLink = new String[1];
    String friendCode;
    String roomHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        friendCode = intent.getStringExtra("friendCode");
        System.out.println(friendCode);
        setContentView(R.layout.activity_wiimmfi);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView drawer = findViewById(R.id.navigation_view);

        int nightModeFlags =
                this.getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
            // Night mode is active, we're using dark theme
            drawer.setBackgroundColor(Color.parseColor("#313131"));
        }

        toolbar.setTitle("Watching " + friendCode);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.room_fragment, new RoomFragment(friendCode, players, playerLink[0], "fc"), null)
                    .setReorderingAllowed(true)
                    .commit();
        }
        toolbar.setNavigationOnClickListener(view -> {
            drawerLayout.open();
        });
        drawer.setNavigationItemSelectedListener(menuItem -> {
            menuItem.setChecked(true);
            drawerLayout.close();
            if (menuItem.getItemId() == R.id.friend_code) {
                players.clear();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.room_fragment, new RoomFragment(friendCode, players, playerLink[0], "fc"), null)
                        .setReorderingAllowed(true)
                        .commit();
            }
            if (menuItem.getItemId() == R.id.roles) {
                players.clear();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.room_fragment, new RoomFragment(friendCode, players, playerLink[0], "roles"), null)
                        .setReorderingAllowed(true)
                        .commit();
            }
            if (menuItem.getItemId() == R.id.login_regions) {
                players.clear();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.room_fragment, new RoomFragment(friendCode, players, playerLink[0], "login_regions"), null)
                        .setReorderingAllowed(true)
                        .commit();
            }
            if (menuItem.getItemId() == R.id.room_match) {
                players.clear();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.room_fragment, new RoomFragment(friendCode, players, playerLink[0], "room_match"), null)
                        .setReorderingAllowed(true)
                        .commit();
            }
            if (menuItem.getItemId() == R.id.world) {
                players.clear();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.room_fragment, new RoomFragment(friendCode, players, playerLink[0], "world"), null)
                        .setReorderingAllowed(true)
                        .commit();
            }
            if (menuItem.getItemId() == R.id.conn_fail) {
                players.clear();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.room_fragment, new RoomFragment(friendCode, players, playerLink[0], "conn_fail"), null)
                        .setReorderingAllowed(true)
                        .commit();
            }
            if (menuItem.getItemId() == R.id.vr_br) {
                players.clear();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.room_fragment, new RoomFragment(friendCode, players, playerLink[0], "vr_br"), null)
                        .setReorderingAllowed(true)
                        .commit();
            }
            return true;
        });
    }

}

