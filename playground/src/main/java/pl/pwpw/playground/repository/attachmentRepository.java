package pl.pwpw.playground.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwpw.playground.attachment.attachment;

public interface attachmentRepository extends JpaRepository<attachment, Long> {
}
