package com.example.test2.Service;

import com.example.test2.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    ArrayList<User> users= new ArrayList<>();

    public ArrayList<User> getUsers(){
        return users;
    }

    public void addUser(User user){
        users.add(user);
    }

    public boolean updateUser(String id, User user){
        for (User u:users){
            if (u.getID().equals(id)){
                users.set(users.indexOf(u),user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(String id){
        for (User u:users){
            if (u.getID().equals(id)){
                users.remove(u);
                return true;
            }
        }
        return false;
    }

    public ArrayList<User> getByBalance(double balance){
        ArrayList<User> userArrayList = new ArrayList<>();

        for (User u:users){
            if (u.getBalance()>= balance){
                userArrayList.add(u);
            }
        }
        return userArrayList;
    }

    public ArrayList<User> getByAge(int age){
        ArrayList<User> userArrayList = new ArrayList<>();

        for (User u:users){
            if (u.getAge()>= age){
                userArrayList.add(u);
            }
        }
        return userArrayList;
    }

}
