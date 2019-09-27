package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public String addUser(@ModelAttribute("user") User user){
        if(user.getId() == 0){
            //new person is added
            userService.addUser(user);
        }else {
            userService.addUser(user);
        }
        return "redirect:/users";
    }

    @RequestMapping("/remove/{id}")
    public String deleteUser(@PathVariable("id") int id){
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @RequestMapping("/getUserById/{id}")
    public String getUserById(@PathVariable("id") int id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

    @RequestMapping("/getUserByEmail/{email}")
    public String getUserByEmail(@PathVariable("email") String email, Model model){
        model.addAttribute("user", userService.getUserByEmail(email));
        return "user";
    }
}
