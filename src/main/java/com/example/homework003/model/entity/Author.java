package com.example.homework003.model.entity;

//import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {

    private Integer authorId;
    private String authorName;
    private String gender;
}
