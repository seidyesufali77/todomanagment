package com.todomanagment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TodoDto {
    private long id;
    private String title;
    private String description;
    private boolean completed;
}
