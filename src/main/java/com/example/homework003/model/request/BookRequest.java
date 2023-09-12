package com.example.homework003.model.request;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequest {
    private Timestamp publishedDate;
    private Integer authorId;
    private String title;
    List<Integer> categoryId;
}
