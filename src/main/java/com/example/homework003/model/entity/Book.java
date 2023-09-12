package com.example.homework003.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    private Integer bookId;
    private String title;
    private Timestamp publishedDate;
    private Author author;
    private List<Category> categories = new ArrayList<>();

}
