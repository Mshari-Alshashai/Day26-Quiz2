package com.example.test2.Service;

import com.example.test2.Model.Book;
import com.example.test2.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookService {

    ArrayList<Book> books= new ArrayList<>();

    public ArrayList<Book> getBooks(){
        return books;
    }

    public void addBook(Book book){
        books.add(book);
    }

    public boolean updateBook(String id, Book book){
        for (Book b:books){
            if (b.getID().equals(id)){
                books.set(books.indexOf(b),book);
                return true;
            }
        }
        return false;
    }

    public boolean deleteBook(String id){
        for (Book b:books){
            if (b.getID().equals(id)){
                books.remove(b);
                return true;
            }
        }
        return false;
    }

    public Book search(String name){
        for (Book book:books){
            if (book.getName().equals(name)){
                return book;
            }
        }
        return null;
    }

    public ArrayList<Book> getByCategory(String category){
        ArrayList<Book> bookArrayList=new ArrayList<>();
        for (Book book:books){
            if (book.getCategory().equals(category)){
                bookArrayList.add(book);
            }
        }
        return bookArrayList;
    }

    public ArrayList<Book> getByNOP(int NOP){
        ArrayList<Book> bookArrayList=new ArrayList<>();
        for (Book book:books){
            if (book.getNumberOfPages()>=NOP){
                bookArrayList.add(book);
            }
        }
        return bookArrayList;
    }

    public int changeStatus(String id, User user){
        if (user.getRole().equals("librarian")) {
            for (Book book : books) {
                if (book.getID().equals(id)){
                    book.setAvailable(false);
                    return 0;
                }
            }
            return 1;
        }
        return 2;
    }
}
