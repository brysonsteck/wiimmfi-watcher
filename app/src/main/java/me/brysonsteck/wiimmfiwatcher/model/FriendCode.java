package me.brysonsteck.wiimmfiwatcher.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class FriendCode implements Serializable {
    @PrimaryKey (autoGenerate = true)
    public long id;

    @ColumnInfo(name="name")
    public String name;

    @NonNull
    @ColumnInfo(name="friendCode")
    public String friendCode;

}