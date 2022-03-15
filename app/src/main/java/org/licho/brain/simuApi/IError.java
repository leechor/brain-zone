package org.licho.brain.simuApi;

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
