package com.example.firstcrud.user;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired private UserService service;

    @GetMapping("/user")
    public String showUser(Model model) {
        List<User> users = service.findAll();
        model.addAttribute("users", users);
        return "user";
    }

    @GetMapping("/adduser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "adduser";
    }

    @PostMapping("/saveuser")
    public String saveUser(@ModelAttribute("user") User user, RedirectAttributes re) {
        service.save(user);
        re.addFlashAttribute("message", "User added successfully");
        return "redirect:/user";
    }

    @GetMapping("/edituser/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model) {
        User user = service.findById(id);
        model.addAttribute("user", user);
        return "edituser";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes re) {
        service.delete(id);
        re.addFlashAttribute("message2", "User deleted successfully");
        return "redirect:/user";
    }
}
