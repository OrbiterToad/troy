package ch.lebois.troyserver.repository;

import ch.lebois.troyserver.data.Client;
import org.springframework.data.repository.CrudRepository;

/**
 * Project: Hermann
 * Package: ch.lebois.troyserver.repository
 **/
public interface ClientRepository extends CrudRepository<Client, String> {
}
