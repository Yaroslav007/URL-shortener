package shortener.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import shortener.dao.ShortUrlRepository;
import shortener.dto.ShortUrlDto;
import shortener.exc.EntityNotFoundException;
import shortener.model.ShortUrl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Business logic for
 *
 * @author yaroslav.shymkiv
 */
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ShortUrlService {

    private ShortUrlRepository shortUrlRepository;

    /**
     * Saves {@link ShortUrl} item to DB.
     *
     * @param urlDto data with short and real URL
     */
    public void save(ShortUrlDto urlDto) {
        ShortUrl shortUrl1 = new ShortUrl(urlDto.getUrl(), urlDto.getShortUrl());
        shortUrlRepository.save(shortUrl1);
    }

    /**
     * Updates {@link ShortUrl} item.
     *
     *
     * @param id
     * @param urlDto updated data
     * @return {@link ResponseEntity} instance
     */
    public void update(int id, ShortUrlDto urlDto) throws EntityNotFoundException {
        Optional<ShortUrl> optionalShortUrl = shortUrlRepository.findById(id);
        if (!optionalShortUrl.isPresent()) {
            throw new EntityNotFoundException(String.format("Entity with %d id was not found.", id));
        }
        ShortUrl shortUrl = optionalShortUrl.get();
        shortUrl.setUrl(urlDto.getUrl());
        shortUrl.setShortUrl(urlDto.getShortUrl());
        shortUrlRepository.save(shortUrl);
    }

    /**
     * Accepts short URL and returns real URL to redirect.
     *
     * @param shortUrl short URL
     * @return real URL
     * @throws EntityNotFoundException if {@link ShortUrl} wan not found
     */
    public String getOriginUrl(String shortUrl) throws EntityNotFoundException {
        Optional<ShortUrl> optional = shortUrlRepository.findByShortUrl(shortUrl);
        if (!optional.isPresent()) {
            throw new EntityNotFoundException(String.format("Entity with %s url was not found.", shortUrl));
        }
        return optional.get().getUrl();
    }

    private ShortUrlDto toShortUrlDto(ShortUrl shortUrl) {
        return new ShortUrlDto(shortUrl.getId(), shortUrl.getUrl(), shortUrl.getShortUrl());
    }
}
