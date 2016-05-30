package ar.com.jalmeyda.dropwizard.poc.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

/**
 * Created by Juan Almeyda on 5/13/2016.
 */
public class Saying {

    private Long id;

    @Length(max = 20)
    private String content;

    public Saying() {
        // Jackson deserialization
    }

    public Saying(long id, String content) {
        this.id = id;
        this.content = content;
    }

    @JsonProperty
    public Long getId() {
        return id;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
