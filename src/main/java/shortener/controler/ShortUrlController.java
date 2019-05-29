package shortener.controler;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import shortener.dto.ShortUrlDto;
import shortener.exc.EntityNotFoundException;
import shortener.model.ShortUrl;
import shortener.service.ShortUrlService;

import java.util.List;

/**
 * Controller for 'Url-shortener" project.
 *
 * @author yaroslav.shymkiv
 */
@RestController
@RequestMapping("/api")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ShortUrlController {

    private ShortUrlService urlService;

    /**
     * Endpoint to save {@link ShortUrl} item.
     *
     * @param urlDto data with short and real URL
     * @return empty response
     */
    @PostMapping("/saveShortUrl")
    public ResponseEntity saveShortUrl(@RequestBody ShortUrlDto urlDto) {
        urlService.save(urlDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Endpoint to update {@link ShortUrl} item.
     *
     * @param urlDto updated data
     * @return empty response
     */
    @PutMapping("/updateShortUrl/{id}")
    public ResponseEntity updateShortUrl(@PathVariable int id, @RequestBody ShortUrlDto urlDto)
            throws EntityNotFoundException {
        urlService.update(id, urlDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint to redirect on real URL.
     *
     * @param shortUrl the short URL
     * @return {@link RedirectView} instance
     */
    @GetMapping("/redirect/{shortUrl}")
    public RedirectView redirect(@PathVariable String shortUrl) throws EntityNotFoundException {
        String originUrl = urlService.getOriginUrl(shortUrl);
        return new RedirectView(originUrl);
    }
}
