package com.example.wiimmterfaceandroid.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity
public class FriendCode {
    @ColumnInfo(name="name")
    public String name;

    @ColumnInfo(name="friendCode")
    public String friendCode;
}
