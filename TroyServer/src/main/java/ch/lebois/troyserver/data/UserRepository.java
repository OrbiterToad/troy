package ch.lebois.troyserver.data;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @USER Felix
 * @DATE 25.02.2018
 * @PROJECT Hermann
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByNameAndPasswordSha(String name, String passwordSha);

}
