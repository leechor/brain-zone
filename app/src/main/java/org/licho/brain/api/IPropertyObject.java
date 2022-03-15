package org.licho.brain.api;

/**
 *
 */
public interface IPropertyObject {
    String ObjectName();

    void ObjectName(String objectName);

    IProperties getProperties();
}
