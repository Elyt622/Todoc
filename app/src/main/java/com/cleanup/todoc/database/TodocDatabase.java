package com.cleanup.todoc.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

@Database(entities = {Project.class, Task.class}, version = 1, exportSchema = false)
public abstract class TodocDatabase extends RoomDatabase {

    private static volatile TodocDatabase INSTANCE;
    public abstract ProjectDao projectDao();
    public abstract TaskDao taskDao();

    public static TodocDatabase getInstance(Context context) {
        if( INSTANCE == null ) {
            synchronized (TodocDatabase.class) {
                if ( INSTANCE == null ) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodocDatabase.class, "MyDatabase.db").allowMainThreadQueries().build();
                }
            }
        }
        return  INSTANCE;
    }
}

