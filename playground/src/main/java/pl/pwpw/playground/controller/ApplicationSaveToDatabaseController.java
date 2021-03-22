package pl.pwpw.playground.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.pwpw.playground.application.Application;
import pl.pwpw.playground.application.ApplicationType;
import org.springframework.beans.factory.annotation.Autowired;
import pl.pwpw.playground.attachment.attachment;
import pl.pwpw.playground.service.ApplicationService;
import pl.pwpw.playground.application.ContactDetails;


import java.util.List;

@Controller
@RequiredArgsConstructor
public class ApplicationSaveToDatabaseController {

    private final ApplicationService applicationService;

    @RequestMapping("/uploadApplication")
    public String get(Model model)
    {
        List<Application> applications = applicationService.findAllApplications();
        model.addAttribute("applications",applications);
        return "application";
    }

    @PostMapping("/uploadApp")
    public String uploadFile(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                             @RequestParam("emailAddress") String emailAddress, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("applicationType") String applicationType)
    {
        applicationService.storeApp(firstName, lastName, emailAddress, phoneNumber, applicationType);
        return "redirect:/uploadApplication";
    }

}