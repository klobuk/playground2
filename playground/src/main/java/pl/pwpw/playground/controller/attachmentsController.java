package pl.pwpw.playground.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.pwpw.playground.application.Application;
import pl.pwpw.playground.application.ApplicationNumber;
import pl.pwpw.playground.attachment.attachment;
import pl.pwpw.playground.service.attachmentService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class attachmentsController {

    private final attachmentService attachmentService;

    @RequestMapping("/uploadAttachment")
    public String get(Model model)
    {
        List<attachment> attachments = attachmentService.getFiles();
        model.addAttribute("attachments",attachments);
        return "attachment";
    }

    @PostMapping("/uploadFiles")
    public String uploadFile(@RequestParam("file") MultipartFile files,@RequestParam("AppNr") String AppNr)
    {
        boolean AppNrExist = attachmentService.appNrExist(AppNr);
        if(AppNrExist==true)
        attachmentService.store(files, AppNr);
        return "redirect:/uploadAttachment";
    }
}
