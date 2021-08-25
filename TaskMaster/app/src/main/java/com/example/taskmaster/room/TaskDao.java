package com.example.taskmaster.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import com.example.taskmaster.room.Task;

@Dao
public interface TaskDao {

    @Insert
    void insertOne(Task task);

    @Query("SELECT * FROM task WHERE task_title LIKE :name")
    Task findByName(String name);

    @Query("SELECT * FROM task")
    List<Task> findAll();


    @Delete
    void deleteItem(Task task);
}
