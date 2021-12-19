package com.example.task313.controller;

import com.example.task313.model.User;
import com.example.task313.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final RoleService roleService;

    @Autowired
    public UserController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(value = "/")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout, Model model) {
        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);
        return "login";
    }

    @GetMapping(value = "/user")
    public String getUserInfo(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        return "userPanel";
    }


    @GetMapping(value = "/admin")
    public String getAllUsers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());
        return "adminPanel";
    }
}
