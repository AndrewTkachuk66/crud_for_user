package controllers;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.List;

@RequestMapping(value = "/api")
@Controller()
public class ApiController {
    private UserService userService;

    @Autowired
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> listUsers(Model model) {
        return this.userService.listUsers();
    }

    //For add and update users both
    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User addUser(@ModelAttribute("user") User user) {
        if(user.getId() == null){
            return   userService.addUser(user);
        }
        return userService.updateUser(user);
    }

    @ResponseBody
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("id") String id) {
        return userService.deleteUser(id);
    }

    @ResponseBody
    @RequestMapping(value = "/user/getUserByEmail", method = RequestMethod.GET)
    public User getUserByEmail(@RequestParam("email") String email) {
        User user = userService.getUserByEmail(email);
        if(user != null){
            return user;
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") String id) {
        User user = userService.getUserById(id);
        if(user != null){
            return user;
        }
        return null;
    }
}
