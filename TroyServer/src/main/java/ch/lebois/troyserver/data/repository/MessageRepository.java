package ch.lebois.troyserver.data.repository;

import ch.lebois.troyserver.data.entity.Message;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project: Hermann
 **/
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findMessagesByType(String type);
}
