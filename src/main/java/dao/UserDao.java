package dao;

import model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);

    void deleteUser(int id);

    void updateUser(User user);

    User getUserById(int id);

    User getUserByEmail(String email);

    List<User> listUsers();
}
