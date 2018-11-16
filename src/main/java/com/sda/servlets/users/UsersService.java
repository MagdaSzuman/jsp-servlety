package com.sda.servlets.users;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsersService {
    private static UsersService instance;

    public static UsersService instanceOf() {
        if (instance == null) {
            instance = new UsersService();
        }
        return instance;
    }

    private List<User> users;
    private Integer nextId;

    private UsersService() {
        this.users = new ArrayList<>();
        this.nextId = 1;

        save(new User("Jan", "Kowalski", 40, "man"));
        save(new User("Marek", "Nowak", 50, "man"));
        save(new User("Janina", "Nowacka", 30, "woman"));
        save(new User("Krystyna", "Kowal", 20, "woman"));
    }

    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    public void save(User user) {
        if (user.getId() != null) {
            users.stream()
                    .filter(e -> e.getId().equals(user.getId()))
                    .findFirst()
                    .ifPresent(e -> {
                        e.setFirstName(user.getFirstName());
                        e.setLastName(user.getLastName());
                        e.setAge(user.getAge());
                        e.setGender(user.getGender());
                    });
        } else {
            user.setId(nextId++);
            users.add(user);
        }
    }

    public User findById(int id) throws UserNotFoundException {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(()-> new UserNotFoundException("User with id " + id + " does not existis"));

    }

    public List<User> findByQuery(String query) {
        List<User> usersToReturn = new ArrayList<>();
        for (User user : users) {
            String userRepresentation = user.getFirstName() + " " + user.getLastName();
            if (userRepresentation.contains(query)) {
                usersToReturn.add(user);
            }
        }
        return usersToReturn;
    }

    public void delete(Integer id) {
        this.users = users.stream()
                .filter(e->!e.getId().equals(id))
                .collect(Collectors.toList());
    }
}
