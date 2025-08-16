package com.todomanagment.service;

import com.todomanagment.dto.TodoDto;

import java.util.List;

public interface TodoService {
    TodoDto addTodo( TodoDto addTodoDto);
    TodoDto getTodoDto(long id);
    List<TodoDto> getAll();
    TodoDto updateTodo( TodoDto todoDto,long id);
    //deleteTodo(long id); // Uncomment if you want to implement delete functionality
    void deleteTodo(long id); // Uncomment if you want to implement delete functionality
    TodoDto completeTodo(long id); // Uncomment if you want to implement complete functionality
    TodoDto inCompleteTodo(long id);// Uncomment if you want to implement incomplete functionality

}
