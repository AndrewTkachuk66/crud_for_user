package service;

import dao.UserDao;
import model.User;

import javax.transaction.Transactional;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private static final String patternForNameAndSurname = "[a-zA-Z]{2,10}";
    private static final String patternForAddress = "^[_A-Za-z0-9-\\+ ]{2,23}";
    private static final String patternForEmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    @Transactional
    public User addUser(User user) {
        if (validateDataFromUser(user))
            userDao.addUser(user);
        return user;
    }

    @Override
    @Transactional
    public String deleteUser(String id) {
        userDao.deleteUser(id);
        return id;
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        if (validateDataFromUser(user))
            userDao.updateUser(user);
        return user;
    }

    @Override
    @Transactional
    public User getUserById(String id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public boolean validateDataFromUser(User user) {
        if (!user.getName().matches(patternForNameAndSurname))
            return false;
        if (!user.getSurname().matches(patternForNameAndSurname))
            return false;
        if (!user.getAddress().matches(patternForAddress))
            return false;
        if (!user.getEmail().matches(patternForEmail))
            return false;

        return true;
    }
}
