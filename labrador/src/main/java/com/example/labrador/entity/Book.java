package com.example.labrador.entity;

import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private String author;
    private String country;
    private String imageLink;
    private String language;
    private String link;
    private int pages;
    private String title;
    private int year;
}
