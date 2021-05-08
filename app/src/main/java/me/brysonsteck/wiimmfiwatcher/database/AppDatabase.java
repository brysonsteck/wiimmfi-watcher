package me.brysonsteck.wiimmfiwatcher.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import me.brysonsteck.wiimmfiwatcher.model.FriendCode;

@Database(entities = {FriendCode.class}, version=1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FriendCodeDao getFriendCodeDao();
}
