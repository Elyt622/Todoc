package com.cleanup.todoc.database;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import androidx.annotation.NonNull;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;


import static com.cleanup.todoc.model.Project.getAllProjects;

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
                            TodocDatabase.class, "MyDatabase.db").addCallback(prepopulateDatabase()).build();
                }
            }
        }
        return  INSTANCE;
    }

    private static Callback prepopulateDatabase(){
        return new Callback() {

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                Project[] projects = getAllProjects();
                ContentValues contentValues = new ContentValues();
                contentValues.put("id", projects[0].getId());
                contentValues.put("name", projects[0].getName());
                contentValues.put("color", projects[0].getColor());
                db.insert("Project", OnConflictStrategy.IGNORE, contentValues);

                contentValues.put("id", projects[1].getId());
                contentValues.put("name", projects[1].getName());
                contentValues.put("color", projects[1].getColor());
                db.insert("Project", OnConflictStrategy.IGNORE, contentValues);

                contentValues.put("id", projects[2].getId());
                contentValues.put("name", projects[2].getName());
                contentValues.put("color", projects[2].getColor());
                db.insert("Project", OnConflictStrategy.IGNORE, contentValues);
            }
        };
    }
}

