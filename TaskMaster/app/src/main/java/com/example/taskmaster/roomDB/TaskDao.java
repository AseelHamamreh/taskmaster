package com.example.taskmaster.roomDB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import com.example.taskmaster.adapter.Task;

@Dao
public interface TaskDao {

    @Insert
    void insertOne(Task task);

    @Query("SELECT * FROM task WHERE task_title LIKE :name")
    Task findByName(String name);

    @Query("SELECT * FROM task")
    List<Task> findAll();

}
