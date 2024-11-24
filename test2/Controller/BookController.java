package com.example.test2.Controller;

import com.example.test2.ApiResponse.ApiResponse;
import com.example.test2.Model.Book;
import com.example.test2.Model.User;
import com.example.test2.Service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/get-book")
    public ResponseEntity getBooks(){
        return ResponseEntity.status(200).body(bookService.getBooks());
    }

    @PostMapping("/add-book")
    public ResponseEntity addBook(@RequestBody @Valid Book book, Errors errors){
        if (errors.hasErrors()) return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        bookService.addBook(book);
        return ResponseEntity.status(200).body(new ApiResponse("book added"));
    }

    @PutMapping("/update-book/{id}")
    public ResponseEntity updateBook(@PathVariable String id, @RequestBody @Valid Book book, Errors errors){
        if (errors.hasErrors()) return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        if (bookService.updateBook(id,book)) return ResponseEntity.status(200).body(new ApiResponse("Book updated"));
        return ResponseEntity.status(400).body(new ApiResponse("Book not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBook(@PathVariable String id){
        if (bookService.deleteBook(id)) return ResponseEntity.status(200).body(new ApiResponse("Book is deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("Book not found"));
    }

    @GetMapping("/search/{name}")
    public ResponseEntity search(@PathVariable String name){
        if (bookService.search(name)==null) return ResponseEntity.status(400).body(new ApiResponse("Book not found"));
        return ResponseEntity.status(200).body(bookService.search(name));
    }

    @GetMapping("/get-by-category/{category}")
    public ResponseEntity getByCategory(@PathVariable String category){
        return ResponseEntity.status(200).body(bookService.getByCategory(category));
    }

    @GetMapping("/get-by-number-of-pages/{NOP}")
    public ResponseEntity getByNOP(@PathVariable int NOP){
        return ResponseEntity.status(200).body(bookService.getByNOP(NOP));
    }

    @PutMapping("/change-status/{id}")
    public ResponseEntity changeStatus(@PathVariable String id,@RequestBody @Valid User user,Errors errors){
        if (errors.hasErrors()) return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        return switch (bookService.changeStatus(id, user)) {
            case 0 -> ResponseEntity.status(200).body(new ApiResponse("Status changed"));
            case 1 -> ResponseEntity.status(400).body(new ApiResponse("Book not found"));
            case 2 -> ResponseEntity.status(400).body(new ApiResponse("The user is not librarian"));
            default -> ResponseEntity.status(400).body(new ApiResponse("Something went wrong"));
        };
    }
}
