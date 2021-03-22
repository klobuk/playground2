package pl.pwpw.playground.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.pwpw.playground.application.Application;


public interface applicationRepository extends JpaRepository<Application, Long> {

    @Query(value = "SELECT * FROM APPLICATION WHERE APPLICATION_NUMBER LIKE %:keyword%", nativeQuery = true)
    Application SelectContactByNr(@Param("keyword") String keyword);

    @Query(value = "SELECT * FROM APPLICATION WHERE EMAIL_ADDRESS like %:keyword%", nativeQuery = true)
    Application SelectAppByEmail(@Param("keyword") String keyword);

    /*
    @Query(value = "SELECT EMAIL_ADDRESS, PHONE_NUMBER FROM APPLICATION WHERE APP_ID = :id", nativeQuery = true)
    String SelectContactById(@Param("id") Long id);
     */
}