package ch.lebois.troyserver.data;

import org.springframework.data.repository.CrudRepository;

/**
 * Project: Hermann
 * Package: ch.lebois.troyserver.repository
 **/
public interface MessageRepository extends CrudRepository<Message, Long> {
}
