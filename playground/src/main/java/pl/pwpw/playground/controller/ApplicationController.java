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

@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping("/findAllApp")
    public List<Application> find()
    {
        return applicationService.findAllApplications();
    }

    @GetMapping("/contactDetailsNr")
    public ContactDetails findContactParam(@RequestParam String APP_NR) {

        return applicationService.findByNr(APP_NR);
    }

    @GetMapping("/findByEmail/{EMAIL_ADDRESS}")
    public String findByEmail(@PathVariable String EMAIL_ADDRESS) {
        Application as = applicationService.findByEmail(EMAIL_ADDRESS);
        ApplicationType at = as.getApplicationType();
        String nr =as.getApplicationNumber().getApplicationNumber();
        String lname = as.getLastName();
        String tmp = "ApplicationType: "+at+" ApplicationNumber: "+nr+" LastName: "+lname;
        return tmp;
    }

    @GetMapping("/createNr")
    public String createAppNr()
    {
        return applicationService.createAppNr();
    }
}