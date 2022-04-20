package bees.HoneyTip.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Category {

    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
