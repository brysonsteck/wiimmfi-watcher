package me.brysonsteck.wiimmfiwatcher;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.tabs.TabLayout;

import me.brysonsteck.wiimmfiwatcher.database.AppDatabase;
import me.brysonsteck.wiimmfiwatcher.fragments.AboutFragment;
import me.brysonsteck.wiimmfiwatcher.fragments.WatchCodeFragment;

public class MainActivity extends AppCompatActivity {
    AppDatabase database;
    final MaterialAlertDialogBuilder[] dialog = new MaterialAlertDialogBuilder[1];
    boolean shownUpdate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        View aboutButton = findViewById(R.id.about_button);
        if (savedInstanceState == null) {
            aboutButton.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.friend_code_input_fragment, new WatchCodeFragment(true), null)
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

//        TabLayout tabLayout = findViewById(R.id.tablayout_main);
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                if (tab.getText().equals("Friend Code")) {
//                        getSupportFragmentManager().beginTransaction()
//                                .replace(R.id.friend_code_input_fragment, new WatchCodeFragment(true), null)
//                                .setReorderingAllowed(true)
//                                .commit();
//
//                    }
//                else if (tab.getText().equals("Mii Name")) {
//                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.friend_code_input_fragment, new WatchCodeFragment(false), null)
//                            .setReorderingAllowed(true)
//                            .commit();
//                }
//                }

//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//
    }

    @Override
    protected void onStart() {
        super.onStart();
        final String[] newestRelease = {""};
        final boolean[] outdated = {false};
        final boolean[] failed = {false};
        Thread thread = new Thread() {
            public void run() {
                Updater updater = new Updater();
                updater.compareRelease(BuildConfig.VERSION_NAME);
                if (updater.isOutdated()) {
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("\tA newer version of Wiimmfi Watcher is available! (" + updater.getNewestRelease() + ")");
                    System.out.println("\tView the release notes and the source code here: " + updater.getGithubRelease());
                    System.out.println("\t---------------------------------------------------------------");
                } else if (updater.hasFailed()) {
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("\t\t An error has occurred while getting information from the update server.");
                    System.out.println("\t\t---------------------------------------------------------------");
                } else {
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("\t\t" + updater.getNewestRelease() + " is the latest release of Wiimmfi Watcher.");
                    System.out.println("\t\t---------------------------------------------------------------");
                }
                newestRelease[0] = updater.getNewestRelease();
                outdated[0] = updater.isOutdated();
                failed[0] = updater.hasFailed();
            }
        };
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (outdated[0] && !shownUpdate) {
            shownUpdate = true;
            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
            new MaterialAlertDialogBuilder(this)
                    .setTitle(R.string.update_title)
                    .setMessage(getResources().getString(R.string.update_message, newestRelease[0]))
                    .setNegativeButton(getResources().getString(R.string.update_negative), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setPositiveButton(getResources().getString(R.string.update_positive), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                            } catch (android.content.ActivityNotFoundException anfe) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                            }
                        }
                    })
                    .show();
        } else if (failed[0] && !shownUpdate) {
            shownUpdate = true;
            Toast.makeText(this, "An error occurred while checking for updates for Wiimmfi Watcher. Please try again later.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}