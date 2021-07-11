package com.example.demo.control;

import com.example.demo.entities.Role;
import com.example.demo.entities.UsersTable;
import com.example.demo.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/lists/user-list")
    public String findAll(Model model){
        List<UsersTable> users = usersService.findAll();
        model.addAttribute("usersTable", new UsersTable()); // Нужно для добавления записи Оо иначе ошибка
        model.addAttribute("users", users);
        model.addAttribute("roles", Arrays.stream(Role.values()).toArray());
        return "/lists/user-list";
    }

    @GetMapping("/lists/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        usersService.deleteUser(id);
        return "redirect:/lists/user-list";
    }

    @PostMapping("/lists/user-create")
    public String createUser(UsersTable usersTable, Model model) {
        usersService.saveUser(usersTable);
        return "redirect:/lists/user-list";
    }

    @GetMapping("/update/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Integer id, Model model) {
        UsersTable user = usersService.getUserById(id);
        model.addAttribute("user", user);
        return "/update/user-update";
    }

    @PostMapping("/update/user-update")
    public String updateUser(UsersTable usersTable) {
        usersService.saveUser(usersTable);
        return "redirect:/list/user-list";
    }
}
