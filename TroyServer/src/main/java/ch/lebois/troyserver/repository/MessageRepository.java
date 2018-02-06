package ch.lebois.troyserver.repository;

import ch.lebois.troyserver.data.Message;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Felix
 * @date 05.02.2018
 * <p>
 * Project: Hermann
 * Package: ch.lebois.troyserver.repository
 **/
public interface MessageRepository extends CrudRepository<Message, Long> {

}
