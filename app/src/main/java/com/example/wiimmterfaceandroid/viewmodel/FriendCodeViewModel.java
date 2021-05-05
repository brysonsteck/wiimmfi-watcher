package com.example.wiimmterfaceandroid.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.wiimmterfaceandroid.database.AppDatabase;
import com.example.wiimmterfaceandroid.model.FriendCode;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import androidx.databinding.ObservableArrayList;

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

        public void setCurrentEntry(FriendCode entry) {
            currentEntry.postValue(entry);
        }

        public MutableLiveData<FriendCode> getCurrentEntry() {
            return currentEntry;
        }

        public MutableLiveData<Boolean> getSaving() {
            return saving;
        }

        public ObservableArrayList<FriendCode> getEntries() {
            return entries;
        }

        public void saveFriendCode(String name, String friendCode) {
            saving.setValue(true);
            new Thread(() -> {
                if (currentEntry.getValue() != null) {

                } else {
                    FriendCode newEntry = new FriendCode();
                    newEntry.name = name;
                    newEntry.friendCode = friendCode;
                    db.getFriendCodeDao().insert(newEntry);
                }

                saving.postValue(false);
            }).start();
        }

        public void deleteEntry(FriendCode entry) {
            new Thread(() -> {
                db.getFriendCodeDao().delete(entry);
                entries.remove(entry);
            }).start();
        }
    }
