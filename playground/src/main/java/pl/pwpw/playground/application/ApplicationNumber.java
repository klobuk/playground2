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
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationNumber {

    private String applicationNumber;

}
