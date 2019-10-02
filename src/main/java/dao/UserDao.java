package dao;

import model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);

    void deleteUser(String id);

    void updateUser(User user);

    User getUserById(String id);

    User getUserByEmail(String email);

    List<User> listUsers();
}
