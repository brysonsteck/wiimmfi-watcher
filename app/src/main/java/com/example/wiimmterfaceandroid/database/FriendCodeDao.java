package com.example.wiimmterfaceandroid.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.wiimmterfaceandroid.model.FriendCode;

import java.util.List;

@Dao
public interface FriendCodeDao {

    @Query("SELECT * FROM friendcode")
    public List<FriendCode> getAll();

    @Query("SELECT * FROM friendcode")
    public FriendCode findByCode(String friendCode);

    @Insert
    public void insert(FriendCode friendCode);

    @Update
    public void update(FriendCode friendCode);

    @Delete
    public void delete(FriendCode friendCode);
}
