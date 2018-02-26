package ch.lebois.troyserver.data.repository;

import ch.lebois.troyserver.data.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project: Hermann
 * Package: ch.lebois.troyserver.repository
 **/
public interface ClientRepository extends JpaRepository<Client, String> {
}
