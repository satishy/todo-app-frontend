package com.camellia.systems.todo.dao;

import com.camellia.systems.todo.domain.Task;

import java.util.List;

public interface TodoTaskDao {

    Task findById(Integer id);

    List<Task> findAll();

    List<Task> findByStatus(String status);

    void save(Task task);

    void update(Task task);

    void delete(Integer id);


}