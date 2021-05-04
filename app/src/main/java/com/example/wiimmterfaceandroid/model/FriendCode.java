package com.example.wiimmterfaceandroid.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class FriendCode implements Serializable {
    @ColumnInfo(name="name")
    public String name;

    @PrimaryKey
    @ColumnInfo(name="friendCode")
    public String friendCode;

}