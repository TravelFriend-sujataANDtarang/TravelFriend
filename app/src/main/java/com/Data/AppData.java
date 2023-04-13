package com.Data;

import android.app.Application;
import android.content.Context;

import com.Dao.ItemsDao;
import com.Database.RoomDB;

import java.util.List;

public class AppData extends Application
{
    RoomDB database;
    String category;
    Context context;

    public static final String LAST_VERSION="LAST_VERSION";
    public static final int NEW_VERSION=3;


    public AppData(RoomDB database) {
        this.database = database;
    }

    public AppData(RoomDB database, Context context) {
        this.database = database;
        this.context = context;
    }


}
