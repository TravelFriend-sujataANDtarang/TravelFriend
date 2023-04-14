package com.example.travelfriend.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.example.travelfriend.Dao.ItemsDao;
import com.example.travelfriend.Models.Items;

@Database(entities= Items.class,version=1,exportSchema=false )
public abstract class RoomDB extends RoomDatabase{
    private static com.example.travelfriend.Database.RoomDB database;
    private static String DATABASE_NAME="MyDb";

    public synchronized static com.example.travelfriend.Database.RoomDB getInstance(Context context){
        if(database==null){
            database= Room.databaseBuilder(context.getApplicationContext(),
                            com.example.travelfriend.Database.RoomDB.class,DATABASE_NAME).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    public abstract ItemsDao mainDao();
}
