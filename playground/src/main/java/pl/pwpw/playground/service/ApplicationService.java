package pl.pwpw.playground.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pwpw.playground.application.*;

import java.time.LocalDate;
import java.util.List;

import pl.pwpw.playground.repository.applicationRepository;
@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final applicationRepository applicationRepository;

    public List<Application> findAllApplications()
    {
        return applicationRepository.findAll();
    }

    public ContactDetails findByNr(String nr) {
        Application FromDatabase = applicationRepository.SelectContactByNr(nr);
        if(FromDatabase == null)
        {
            throw new NullPointerException("Brak aplikacji o podanym numerze !!!");
        }
        ContactDetails newContactD = FromDatabase.getContactDetails();
        return newContactD;
    }

    public Application findByEmail(String email) {
        Application FromDatabase = applicationRepository.SelectAppByEmail(email);
        if(FromDatabase == null)
        {
            throw new NullPointerException("Brak aplikacji o podanym numerze !!!");
        }
        return FromDatabase;
    }
    public boolean rightEmail(String email)
    {
        return email.matches("\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b");
    }
    public boolean rightPhoneNumber(String phone)
    {
        return phone.matches("^[0-9]{9}$");
    }

    public Application storeApp(String firstName, String lastName, String emailAddress, String phoneNumber, String applicationType)
    {
        Application appToSave = new Application();
        ApplicationNumber appNr = new ApplicationNumber(createAppNr());
        appToSave.setApplicationNumber(appNr);
        appToSave.setFirstName(firstName);
        appToSave.setLastName(lastName);
        boolean rightValue = rightEmail(emailAddress);
        EmailAddress email = new EmailAddress();
        if(rightValue == true)
        {
            email.setEmailAddress(emailAddress);
        }
        else
        {
            throw new IllegalArgumentException("Adres email jest nieprawidlowy !!!");
        }
        PhoneNumber phone = new PhoneNumber();
        rightValue = rightPhoneNumber(phoneNumber);
        if (rightValue==true)
        {
            phone.setPhoneNumber(phoneNumber);
        }
        else
        {
            throw new IllegalArgumentException("Podany numer telefonu jest nieprawidlowy !!!");
        }
        ContactDetails contactDetails = new ContactDetails(email, phone);
        appToSave.setContactDetails(contactDetails);
        ApplicationType appType;
        if(applicationType.equals("APPLICATION_A"))
        {
            appType = ApplicationType.APPLICATION_A;
        }
        else
        {
            if(applicationType.equals("APPLICATION_B"))
            {
                appType = ApplicationType.APPLICATION_B;
            }
            else
            {
                appType = ApplicationType.APPLICATION_C;
            }
        }
        if(firstName == null||lastName==null||emailAddress==null||phoneNumber==null||firstName == ""||lastName==""||emailAddress==""||phoneNumber=="")
        {
            throw new IllegalArgumentException("Jeden lub więcej parametrow jest pustych !!!");
        }
        appToSave.setApplicationType(appType);
        return applicationRepository.save(appToSave);
    }

    public String createAppNr()
    {
        StringBuilder sb = new StringBuilder();
        List<Application> listOfApp = applicationRepository.findAll();
        String [] tabOfAllNumbers = new String[listOfApp.size()];
        for(int i = 0; i<tabOfAllNumbers.length; i++)
        {
            tabOfAllNumbers[i]=listOfApp.get(i).getApplicationNumber().getApplicationNumber();
        }
        String[] tabOfConvertedNumbers = new String[tabOfAllNumbers.length];
        for(int i = 0; i<tabOfAllNumbers.length; i++)
        {
            tabOfConvertedNumbers[i] = tabOfAllNumbers[i].substring(3,13);
        }
        LocalDate nowDate = LocalDate.now();
        String stringDate = nowDate.toString();
        int howMany=1;
        for(int i = 0; i<tabOfConvertedNumbers.length; i++)
        {
            if(tabOfConvertedNumbers[i].equals(stringDate))
            {
                howMany++;
            }
        }
        sb.append("PL/").append(stringDate).append("/").append(howMany);
        return sb.toString();
    }
}