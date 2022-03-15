package org.licho.brain.brainApi;

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
