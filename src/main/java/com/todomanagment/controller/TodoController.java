package com.todomanagment.controller;

import com.todomanagment.dto.TodoDto;
import com.todomanagment.exception.ResourceNotFoundException;
import com.todomanagment.service.TodoService;
import com.todomanagment.service.impl.TodoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/todos")
public class TodoController {
    @Autowired
    private TodoServiceImpl todoService;
    @PostMapping("/add")
    public ResponseEntity<TodoDto> addTodo(@RequestBody  TodoDto addTodoDto) {
        TodoDto saveTodo = todoService.addTodo(addTodoDto);
        return new ResponseEntity<>(saveTodo, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable long id){
        TodoDto todoDto = todoService.getTodoDto(id);
        if (todoDto != null) {
            return new ResponseEntity<>(todoDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAll(){
        List<TodoDto> todoDtos = todoService.getAll();
        if (todoDtos != null && !todoDtos.isEmpty()) {
            return new ResponseEntity<>(todoDtos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable long id){
        TodoDto updatedTodo = todoService.updateTodo(todoDto, id);
        if (updatedTodo != null) {
            return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable long id){
        try {
            todoService.deleteTodo(id);
            return ResponseEntity.ok("the todo with the given id has been deleted");
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

        @PatchMapping("/complete/{id}")
        public ResponseEntity<TodoDto> completeTodo (@PathVariable long id){
            TodoDto completedTodo=todoService.completeTodo(id);
            if (completedTodo != null) {
                return  ResponseEntity.ok(completedTodo);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    @PatchMapping("/incomplete/{id}")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable long id){
        TodoDto inCompletedTodo=todoService.inCompleteTodo(id);
        if (inCompletedTodo != null) {
            return ResponseEntity.ok(inCompletedTodo);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    }

