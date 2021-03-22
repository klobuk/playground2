package pl.pwpw.playground.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.pwpw.playground.repository.*;

@SpringBootTest
public class attachmentServiceTest {

    @Autowired
    private attachmentRepository attachmentRepository;

    @Autowired
    private applicationRepository applicationRepository;

    @Test
    public void appNrExist_should_return_false()
    {
        attachmentService attachmentService = new attachmentService(attachmentRepository, applicationRepository);
        //when
        String wrongAppNr = "wrong";
        boolean exist = attachmentService.appNrExist(wrongAppNr);
        Assertions.assertEquals(false, exist);
    }

}
