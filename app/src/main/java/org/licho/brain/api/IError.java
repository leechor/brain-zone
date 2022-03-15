package org.licho.brain.api;

/**
 *
 */
public interface IError {
    String getObjectType();

    String getObjectName();

    String getPropertyName();

    String getPropertyValue();

    String getErrorText();
}
