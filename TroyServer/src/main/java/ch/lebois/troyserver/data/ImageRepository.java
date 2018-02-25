package ch.lebois.troyserver.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @USER Felix
 * @DATE 25.02.2018
 * @PROJECT Hermann
 */
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findImagesByPcNameFk(String client);

    Image findImageByName(String name);
}
