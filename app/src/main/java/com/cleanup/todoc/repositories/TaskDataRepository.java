package com.cleanup.todoc.repositories;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskDataRepository {
    private final TaskDao taskDao;

    public TaskDataRepository(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public LiveData<List<Task>> getTasks(long id){
        return this.taskDao.getTasks(id);
    }

    public LiveData<List<Task>> getAllTasks(){
        return this.taskDao.getAllTasks();
    }

    public void createTask(Task task) {
        taskDao.insertTask(task);
    }

    public void deleteTask(long id){
        taskDao.deleteTask(id);
    }

    public void updateTask(Task task){
        taskDao.updateTask(task);
    }
}
