package org.licho.brain.api;

/**
 *
 */
public interface IPropertyDefinition {
    String Name();

    String Description();

    void Description(String description);

    boolean Required();

    void Required(boolean required);

    String DisplayName();

    void DisplayName(String displayName);
}
