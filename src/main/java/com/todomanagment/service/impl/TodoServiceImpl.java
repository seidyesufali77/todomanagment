package com.todomanagment.service.impl;

import com.todomanagment.Mapper.TodoMapper;
import com.todomanagment.dto.TodoDto;
import com.todomanagment.entity.Todo;
import com.todomanagment.exception.ResourceNotFoundException;
import com.todomanagment.repository.TodoRepository;
import com.todomanagment.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoMapper todoMapper;

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public TodoDto addTodo(TodoDto addTodoDto) {
        Todo todo = todoMapper.toEntity(addTodoDto);
        Todo savedTodo = todoRepository.save(todo);
        return todoMapper.toDto(savedTodo);
    }

    @Override
    public TodoDto getTodoDto(long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo with the given id not found"));
        return todoMapper.toDto(todo); // you can use the mapper here too
    }

    @Override
    public List<TodoDto> getAll() {
        List<Todo> todos = todoRepository.findAll();
        return todoMapper.toDtoList(todos);
    }

    @Override
    public TodoDto updateTodo( TodoDto todoDto,long id ){
    Todo existingTodo = todoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Todo with the given id not found"));
    //existingTodo.setId(todoDto.getId());
    existingTodo.setTitle(todoDto.getTitle());
    existingTodo.setDescription(todoDto.getDescription());
    existingTodo.setCompleted(todoDto.isCompleted());
    Todo updatedTodo = todoRepository.save(existingTodo);
    return todoMapper.toDto(updatedTodo);
    }

    @Override
    public void deleteTodo( long id ){
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo with the given id not found"));

        todoRepository.delete(todo);

    }

    @Override
    public TodoDto completeTodo( long id ){
    Todo todo = todoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Todo with the given id not found"));
        todo.setCompleted(true);
    Todo updatedTodo = todoRepository.save(todo);
    return todoMapper.toDto(updatedTodo);
    }

    @Override
    public TodoDto inCompleteTodo( long id ){
    Todo todo = todoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Todo with the given id not found"));
        todo.setCompleted(false);
    Todo updatedTodo = todoRepository.save(todo);
        return todoMapper.toDto(updatedTodo);
    }
}
