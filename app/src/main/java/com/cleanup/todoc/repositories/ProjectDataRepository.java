package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.model.Project;

import java.util.List;

public class ProjectDataRepository {

    private final ProjectDao projectDao;

    public ProjectDataRepository(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public LiveData<Project> getProject(long id) {
        return this.projectDao.getProject(id);
    }

    public LiveData<List<Project>> getAllProject() {
        return this.projectDao.getAllProject();
    }

    public void createProject(Project project) { projectDao.createProject(project); }
}