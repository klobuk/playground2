package pl.pwpw.playground.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.pwpw.playground.application.Application;
import pl.pwpw.playground.repository.attachmentRepository;
import pl.pwpw.playground.attachment.attachment;
import pl.pwpw.playground.repository.applicationRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class attachmentService {

    private final attachmentRepository attachmentRepository;

    private final applicationRepository applicationRepository;

    public attachment store(MultipartFile file, String appNr) {
        LocalDate nowDate = LocalDate.now();
        attachment attachment = null;
        try {
            attachment = new attachment(file.getBytes(), nowDate, file.getContentType(), appNr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attachmentRepository.save(attachment);
    }

    public boolean appNrExist(String appNr)
    {
        ApplicationService applicationService = new ApplicationService(applicationRepository);
        List<Application> applicationList = applicationService.findAllApplications();
        boolean exist = false;
        for(int i = 0; i<applicationList.size(); i++)
        {
            if(applicationList.get(i).getApplicationNumber().getApplicationNumber().equals(appNr))
                exist = true;
        }
        return exist;
    }

    public List<attachment> getFiles()
    {
        return attachmentRepository.findAll();
    }
}
