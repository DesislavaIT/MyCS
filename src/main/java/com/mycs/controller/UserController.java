package com.mycs.controller;

import com.mycs.security.SecurityService;
import com.mycs.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UserController {

    @Value("${csv.input.path}")
    private String UPLOAD_DIRECTORY;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private MyCSController myCSController;

    @GetMapping({ "/login"})
    public String login(Model model, String error, String logout) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping("/contact")
    public String contacts(Model model) {
        return "contact";
    }

    @GetMapping("/batch")
    public String batch(Model model) {
        if (!securityService.isAuthenticated()) {
            return "redirect:/login";
        }
        return "batch";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        if (!securityService.isAuthenticated()) {
            return "redirect:/login";
        }
        return "welcome";
    }

    @PostMapping("/upload")
    public String uploadFile(Model model, @RequestParam("csv") MultipartFile file, RedirectAttributes attributes) throws IOException {

        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/";
        }

        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // save the file on the local file system
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, fileName);
        fileNames.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        model.addAttribute("msg", "Uploaded images: " + fileNames);

        // return success response
        attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');

        myCSController.batchProcessing(fileName);

        return "redirect:/batch";
    }
}
