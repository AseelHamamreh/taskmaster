package com.example.taskmaster.roomDB;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.taskmaster.adapter.Task;

@Database(entities = {Task.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract TaskDao taskDao();
}