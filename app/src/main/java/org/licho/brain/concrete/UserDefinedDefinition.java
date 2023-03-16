package org.licho.brain.concrete;

import java.util.UUID;

/**
 *
 */
public class UserDefinedDefinition extends AbstractGridObjectDefinition {
    private final UUID guid;

    public UserDefinedDefinition(UUID uuid) {
        super("UserDefined");
        this.guid = uuid;
        // TODO: 2021/11/23
    }

    @Override
    public Class<?> ElementType() {
        return null;
    }

    @Override
    public Class<?> RunSpaceType() {
        return null;
    }

    @Override
    public AbstractGridObjectDefinition DefaultDefinition() {
        return null;
    }

    @Override
    public boolean IsValidIdentifier(String param0, StringBuffer error) {
        return false;
    }

    @Override
    public String GetUniqueName(String param0) {
        return null;
    }
}
