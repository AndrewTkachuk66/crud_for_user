package service;

import model.User;

import java.util.List;

public interface UserService {
    List<User> listUsers();

    void addUser(User user);

    void deleteUser(String id);

    void updateUser(User user);

    User getUserById(String id);

    User getUserByEmail(String email);
}
