package ch.lebois.troyserver.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Project: Hermann
 * Package: ch.lebois.troyserver.data
 **/

@Entity
public class Message {

    @Id
    @GeneratedValue
    private Long id;

    private String pcNameFk;

    private String type;
    private String text;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
