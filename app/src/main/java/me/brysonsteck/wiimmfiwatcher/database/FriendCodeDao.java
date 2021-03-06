package me.brysonsteck.wiimmfiwatcher.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import me.brysonsteck.wiimmfiwatcher.model.FriendCode;

@Dao
public interface FriendCodeDao {

    @Query("SELECT * FROM friendcode")
    public List<FriendCode> getAll();

//    @Query("SELECT * FROM friendcodeobj")
//    public FriendCodeObj findByCode(String friendCode);

    @Insert
    public void insert(FriendCode friendCode);

    @Update
    public void update(FriendCode friendCode);

    @Delete
    public void delete(FriendCode friendCode);

    @Query("DELETE FROM friendcode")
    public void nukeTable();

}
