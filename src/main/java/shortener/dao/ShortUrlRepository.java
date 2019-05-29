package shortener.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shortener.model.ShortUrl;

import java.util.List;
import java.util.Optional;

/**
 * Repository for {@link ShortUrl} instance.
 *
 * @author yaroslav.shymkiv
 */
@Repository
public interface ShortUrlRepository extends CrudRepository<ShortUrl, Integer> {

    Optional<ShortUrl> findByShortUrl(String shortUrl);

    List<ShortUrl> findAll();
}
