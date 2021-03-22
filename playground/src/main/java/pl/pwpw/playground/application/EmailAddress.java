package pl.pwpw.playground.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 *
 */
@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data

public class EmailAddress {

    private String emailAddress;

}
