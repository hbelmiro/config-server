package com.hbelmiro.configserver.model;

import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import org.bson.codecs.pojo.annotations.BsonId;

import java.util.Objects;

public class Configuration extends PanacheMongoEntityBase {

    @BsonId
    private String id;

    private String value;

    @SuppressWarnings("unused")
    public Configuration() {
    }

    public Configuration(String id, String value) {
        this.id = id;
        this.value = value;
    }

    @SuppressWarnings("unused")
    public String getId() {
        return id;
    }

    @SuppressWarnings("unused")
    public void setId(String id) {
        this.id = id;
    }

    @SuppressWarnings("unused")
    public String getValue() {
        return value;
    }

    @SuppressWarnings("unused")
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
