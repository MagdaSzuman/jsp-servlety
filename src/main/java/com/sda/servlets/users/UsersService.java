package com.sda.servlets.users;

import java.util.ArrayList;
import java.util.List;

public class UsersService {
    private static UsersService instance;

    public static UsersService instanceOf(){
        if (instance == null){
            instance = new UsersService();
        }
        return instance;
    }

    private List<User> users;

    private UsersService() {
        this.users = new ArrayList<>();

        users.add(new User("Jan", "Kowalski", 40, "man"));
        users.add(new User("Marek", "Nowak", 50, "man"));
        users.add(new User("Janina", "Nowacka", 30, "woman"));
        users.add(new User("Krystyna", "Kowal", 20, "woman"));
    }

    public List<User> findAll(){
        return new ArrayList<>(users);
    }

    public void save(User user){
        users.add(user);
    }

    public User findById(int id){
        return users.get(id);
    }
}
