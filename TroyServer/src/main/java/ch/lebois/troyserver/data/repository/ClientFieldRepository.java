package ch.lebois.troyserver.data.repository;

import ch.lebois.troyserver.data.entity.ClientField;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Felix
 * @date 11.04.2018
 * <p>
 * Project: ServerControl
 * Package: ch.lebois.troyserver.data.repository
 **/
public interface ClientFieldRepository extends JpaRepository<ClientField, Integer> {

    List<ClientField> findClientFieldsByClient(String client);

    ClientField findClientFieldByClientAndField(String client, String field);


}
