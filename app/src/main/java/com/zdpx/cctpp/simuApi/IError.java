package com.zdpx.cctpp.simuApi;

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
