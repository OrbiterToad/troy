package ch.lebois.troyserver.data.repository;

import ch.lebois.troyserver.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @PROJECT Hermann
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByNameAndPasswordSha(String name, String passwordSha);

}
