package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import model.User;
import service.UserService;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", this.userService.listUsers());
        return "user";
    }

    //For add and update users both
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {
        if(user.getId() == null)
            userService.addUser(user);
        else userService.updateUser(user);
        return "redirect:/users";
    }

    @RequestMapping("/remove/{id}")
    public String deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @ResponseBody
    @RequestMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") String id, Model model) {
        User user = userService.getUserById(id);
        if(user != null){
            return user;
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/getUserByEmail")
    public User getUserByEmail(@RequestParam("email") String email, Model model) {
        User user = userService.getUserByEmail(email);
        if(user != null){
            return user;
        }
        return null;
    }

    @RequestMapping("/edit/{id}")
    public String editUser(@PathVariable("id") String id, Model model) {
        model.addAttribute("user", this.userService.getUserById(id));
        model.addAttribute("listUsers", this.userService.listUsers());
        return "user";
    }
}
