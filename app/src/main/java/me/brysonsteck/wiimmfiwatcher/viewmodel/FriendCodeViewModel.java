package me.brysonsteck.wiimmfiwatcher.viewmodel;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import me.brysonsteck.wiimmfiwatcher.database.AppDatabase;
import me.brysonsteck.wiimmfiwatcher.model.FriendCode;

public class FriendCodeViewModel extends AndroidViewModel {
        ObservableArrayList<FriendCode> entries = new ObservableArrayList<>();
        MutableLiveData<Boolean> saving = new MutableLiveData<>();
        MutableLiveData<FriendCode> currentEntry = new MutableLiveData<>();
        AppDatabase db;
        public FriendCodeViewModel(Application app) {
            super(app);
            saving.setValue(false);
            db = Room.databaseBuilder(app, AppDatabase.class, "friend-codes-db").build();
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                entries.addAll(db.getFriendCodeDao().getAll());
            }).start();
        }

        public ObservableArrayList<FriendCode> getEntries() {
            return entries;
        }

//    public boolean deleteAll() {
//        for (FriendCode entry: entries) {
//            db.getFriendCodeDao().nukeTable();
//        }
//        return true;
//    }

        public void saveFriendCode(String name, String friendCode) {
            saving.setValue(true);
            new Thread(() -> {
                if (currentEntry.getValue() != null) {

                } else {
                    FriendCode newEntry = new FriendCode();
                    newEntry.name = name;
                    newEntry.friendCode = friendCode;
                    db.getFriendCodeDao().insert(newEntry);
                    entries.add(newEntry);
                }

                saving.postValue(false);
            }).start();
        }

//        public void deleteEntry(FriendCode entry) {
//            new Thread(() -> {
//                db.getFriendCodeDao().delete(entry);
//                entries.remove(entry);
//            }).start();
//        }
    }
