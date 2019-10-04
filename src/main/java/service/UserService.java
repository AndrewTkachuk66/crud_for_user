package service;

import model.User;

import java.util.List;

public interface UserService {
    List<User> listUsers();

    User addUser(User user);

    String deleteUser(String id);

    User updateUser(User user);

    User getUserById(String id);

    User getUserByEmail(String email);

    boolean validateDataFromUser(User user);


}
