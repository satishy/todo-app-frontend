package com.camellia.systems.todo.service;


import com.camellia.systems.todo.domain.Task;

import java.util.List;

public interface TaskService {
    void saveOrUpdate(Task task);

    Task findById(Integer id);

    void deleteById(Integer id);

    List<Task> findAll();

}
