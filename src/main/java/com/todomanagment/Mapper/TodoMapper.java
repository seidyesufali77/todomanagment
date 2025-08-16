package com.todomanagment.Mapper;
import com.todomanagment.dto.TodoDto;
import com.todomanagment.entity.Todo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    TodoDto toDto(Todo todo);
    Todo toEntity(TodoDto todoDto);
    List<TodoDto> toDtoList(List<Todo> todos);
}