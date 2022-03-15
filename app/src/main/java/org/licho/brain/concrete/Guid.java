package org.licho.brain.concrete;

import java.util.Objects;
import java.util.UUID;

/**
 *
 */
public class Guid {

    private String guid;
    public static Guid Empty;

    public Guid() {
        this.guid = UUID.randomUUID().toString();
    }

    public Guid(String guid) {
        this.guid = guid;
    }

    public static Guid NewGuid() {
        return new Guid();
    }

    public static Guid TryParse(String type) {
        try {
            return new Guid(UUID.fromString(type).toString());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return guid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guid guid1 = (Guid) o;
        return Objects.equals(guid, guid1.guid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guid);
    }
}
