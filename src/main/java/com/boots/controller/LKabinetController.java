package com.boots.controller;

import com.boots.service.UserService;
import com.boots.entity.User;
import com.boots.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
    public class LKabinetController {
        @Autowired
        private UserRepository userRepository;
        @Autowired
        private UserService userService;


    @GetMapping("/LKabinet/{username}")
    public String getProfile(@PathVariable String username, Model model) {
        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);
        return "/LKabinet";
    }
    

    @PostMapping("/LKabinet/{username}")
    public String uploadImage(@RequestParam("image") MultipartFile image, @RequestParam("username") String username) {
        User user = userRepository.findByUsername(username);
        try {
            user.setProfileImage(image.getBytes());
            userRepository.save(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/LKabinet/" + username;

    }
}
