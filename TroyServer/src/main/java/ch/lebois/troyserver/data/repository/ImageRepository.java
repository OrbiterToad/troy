package ch.lebois.troyserver.data.repository;

import ch.lebois.troyserver.data.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @PROJECT Hermann
 */
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findImagesByPcNameFkOrderByNameDesc(String client);

    Image findImageByName(String name);
}
