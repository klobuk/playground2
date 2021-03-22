package pl.pwpw.playground.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.pwpw.playground.application.*;
import pl.pwpw.playground.repository.applicationRepository;

import java.util.List;


@SpringBootTest
//@RequiredArgsConstructor
public class ApplicationServiceTest {

    @Autowired
    private applicationRepository repository;


    @Test
    public void findByNr_contactDetails_should_be_same_as_expected()
    {
        //given
        String number = "PL/2020-10-05/3";
        ApplicationService appService = new ApplicationService(repository);
        //when
        ContactDetails actualContactDetails = appService.findByNr(number);
        EmailAddress expectedEmail = new EmailAddress("foo@bar");
        PhoneNumber expectedPhoneNumber = new PhoneNumber("123321123345333");
        ContactDetails expectedContactDetails = new ContactDetails(expectedEmail,expectedPhoneNumber);
        //then
        Assertions.assertEquals(expectedContactDetails,actualContactDetails);
    }

    @Test
    public void findByNr_should_throw_NullPointerException()
    {
        //given
        String number = "PL/2020-10-05/10";
        ApplicationService appService = new ApplicationService(repository);
        //when
        //then
        Assertions.assertThrows(NullPointerException.class,() -> appService.findByNr(number));
    }

    @Test
    public void findByEmail_should_throw_NullPointerException()
    {
        //given
        String email = "marek@wp.pl";
        ApplicationService appService = new ApplicationService(repository);
        //when
        //then
        Assertions.assertThrows(NullPointerException.class,() -> appService.findByEmail(email));
    }

    @Test
    public void createAppNr_should_be_unique()
    {
        ApplicationService appService = new ApplicationService(repository);
        String generatedAppNr= appService.createAppNr();
        List<Application> listOfApp = appService.findAllApplications();
        String [] tabOfAllNumbers = new String[listOfApp.size()];
        for(int i = 0; i<tabOfAllNumbers.length; i++)
        {
            tabOfAllNumbers[i]=listOfApp.get(i).getApplicationNumber().getApplicationNumber();
        }

        for (String numberInDatabase : tabOfAllNumbers)
            Assertions.assertNotEquals(generatedAppNr, numberInDatabase);
    }

    @Test
    public void storeApp_should_throw_IllegalArgumentException_because_of_null_param()
    {
        //given
        String firstName = "Kamil";
        String lastName = "Klobukowski";
        String emailAddress = null;
        String phoneNumber = "999999999";
        String applicationType = "APPLICATION_B";
        ApplicationService appService = new ApplicationService(repository);

        //then
        Assertions.assertThrows(IllegalArgumentException.class, ()-> appService.storeApp(firstName, lastName, emailAddress, phoneNumber, applicationType));
    }

    @Test
    public void storeApp_should_throw_IllegalArgumentException_because_of_wrong_email()
    {
        //given
        String firstName = "Kamil";
        String lastName = "Klobukowski";
        String emailAddress = "kamil.email.kaka";
        String phoneNumber = "999999999";
        String applicationType = "APPLICATION_B";
        ApplicationService appService = new ApplicationService(repository);

        //then
        Assertions.assertThrows(IllegalArgumentException.class, ()-> appService.storeApp(firstName, lastName, emailAddress, phoneNumber, applicationType));
    }

    @Test
    public void storeApp_should_throw_IllegalArgumentException_because_of_wrong_phoneNumber()
    {
        //given
        String firstName = "Kamil";
        String lastName = "Klobukowski";
        String emailAddress = "kamil@all4.pl";
        String phoneNumber = "12345678k";
        String applicationType = "APPLICATION_B";
        ApplicationService appService = new ApplicationService(repository);

        //then
        Assertions.assertThrows(IllegalArgumentException.class, ()-> appService.storeApp(firstName, lastName, emailAddress, phoneNumber, applicationType));
    }

}
