package ch.lebois.troyserver.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @PROJECT Hermann
 */

@Entity
public class Image {
    @Id
    @GeneratedValue
    private Long id;

    private String pcNameFk;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPcNameFk() {
        return pcNameFk;
    }

    public void setPcNameFk(String pcNameFk) {
        this.pcNameFk = pcNameFk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
