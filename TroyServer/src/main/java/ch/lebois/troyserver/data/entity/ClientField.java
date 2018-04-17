package ch.lebois.troyserver.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Felix
 * @date 11.04.2018
 * <p>
 * Project: ServerControl
 * Package: ch.lebois.troyserver.data.entity
 **/

@Entity

public class ClientField {

    @Id
    @GeneratedValue
    private Long id;

    private String client;

    private String field;
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}