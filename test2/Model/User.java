package com.example.test2.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    @NotEmpty(message = "The ID should not be empty")
    private String ID;
    @NotEmpty(message = "The name should not be empty")
    private String name;
    @Positive(message = "The age should have a positive number")
    private int age;
    @NotNull(message = "The balance should not be null")
    @Positive(message = "Balance should have a positive value")
    private double balance;
    @NotEmpty(message = "The role should not be empty")
    @Pattern(regexp = "customer|librarian",message = "The role should be customer or librarian")
    private String role;
}
