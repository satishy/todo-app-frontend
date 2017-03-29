package com.camellia.systems.todo.service;


import com.camellia.systems.todo.dao.TodoTaskDao;
import com.camellia.systems.todo.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("taskService")
public class TaskServiceImpl implements TaskService  {

    TodoTaskDao todoTaskDao;

    @Autowired
    public void setTodoTaskDao(TodoTaskDao todoTaskDao) {
        this.todoTaskDao = todoTaskDao;
    }

    @Override
    public Task findById(Integer id) {
        return todoTaskDao.findById(id);
    }

    @Override
    public void deleteById(Integer id) { todoTaskDao.delete(id);}

    @Override
    public void saveOrUpdate(Task task) {
        if (findById(task.getId())==null) {
            todoTaskDao.save(task);
        } else {
            todoTaskDao.update(task);
        }

    }

    @Override
    public List<Task> findAll() { return todoTaskDao.findAll();
    }

}
