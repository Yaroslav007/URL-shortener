package shortener.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import shortener.model.ShortUrl;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * DTO representation of {@link ShortUrl} class.
 *
 * @author yaroslav.shymkiv
 */
@Getter
@Setter
@AllArgsConstructor
public class ShortUrlDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotBlank
    private int id;
    @NotBlank
    private String url;
    @NotBlank
    @Size(max = 6)
    private String shortUrl;
}
