package shortener.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Represents a URL shortener instance.
 *
 * @author yaroslav.shymkiv
 */
@Entity
@Getter
@Setter
public class ShortUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String url;

    @Column(unique = true, nullable = false, length = 6)
    private String shortUrl;

    protected ShortUrl() {
    }

    public ShortUrl(String url, String shortUrl) {
        this.url = url;
        this.shortUrl = shortUrl;
    }
}
