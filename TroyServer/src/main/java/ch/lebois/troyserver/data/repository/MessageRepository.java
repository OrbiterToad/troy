package ch.lebois.troyserver.data.repository;

import ch.lebois.troyserver.data.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project: Hermann
 **/
public interface MessageRepository extends JpaRepository<Message, Long> {
}
