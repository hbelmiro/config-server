package com.hbelmiro.configserver.model;

import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import org.bson.codecs.pojo.annotations.BsonId;

import java.util.Objects;

public class Configuration extends PanacheMongoEntityBase {

    @BsonId
    private String id;

    private String value;

    public Configuration() {
    }

    public Configuration(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Configuration that = (Configuration) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value);
    }

}
