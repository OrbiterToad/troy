package ch.lebois.troyserver.repository;

import ch.lebois.troyserver.data.Message;
import org.springframework.data.repository.CrudRepository;

/**
 * Project: Hermann
 * Package: ch.lebois.troyserver.repository
 **/
public interface MessageRepository extends CrudRepository<Message, Long> {

}
