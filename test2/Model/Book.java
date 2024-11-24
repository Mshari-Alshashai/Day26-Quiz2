package com.example.test2.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {

    @NotEmpty(message = "The ID should not be empty")
    private String ID;

    @NotEmpty(message = "the name should not be empty" )
    private String name;

    @Positive(message = "The number of pages should have a positive value")
    private int numberOfPages;

    @Positive(message = "The price should have a positive value")
    private int price;

    @NotEmpty(message = "The category should not be empty")
    @Pattern(regexp = "novel|academic", message = "The category should be novel or academic")
    private String category;

    @NotNull(message = "Is available should not be null")
    private boolean isAvailable;
}
