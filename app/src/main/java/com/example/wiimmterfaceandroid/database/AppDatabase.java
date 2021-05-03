package com.example.wiimmterfaceandroid.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.wiimmterfaceandroid.model.FriendCode;

@Database(entities = {FriendCode.class}, version=1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FriendCodeDao getFriendCodeDao();
}
