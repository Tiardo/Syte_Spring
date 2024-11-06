package com.boots.controller;

import com.boots.entity.User;
import com.boots.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @Value("${upload.path}")
    private String uploadPath;


    @GetMapping("/lk")
    public String lkErroe(Model model) {
        model.addAttribute("userForm", new User());
        return "lk";
    }

    @PostMapping("/lk")
    public String lk(
            @RequestParam("file") MultipartFile file,
            @ModelAttribute("userForm") @Valid User userForm, Model model

    ) throws IOException {

        if (file != null && !file.getOriginalFilename().isEmpty()){
            File upoadDir = new File(uploadPath);

            if (!upoadDir.exists()) {
                upoadDir.mkdir();
            }

           String uuidFile = UUID.randomUUID().toString();
           String resultFilename = uuidFile + "." + file.getOriginalFilename();
           file.transferTo(new File(uploadPath +"/"+resultFilename));

            String errorMessage = (file.getOriginalFilename() + " Добавлен");
            model.addAttribute("passwordError", errorMessage);
        }else      {
            model.addAttribute("usernameError", "Ошибка формата или файл не добавлен");
        }

        return "lk";

    }
}