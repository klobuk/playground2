package pl.pwpw.playground.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 *
 */
@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data

public class ContactDetails {

    @Embedded
    private EmailAddress emailAddress;

    @Embedded
    private PhoneNumber phoneNumber;


}
