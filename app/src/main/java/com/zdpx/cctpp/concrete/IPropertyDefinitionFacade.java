package com.zdpx.cctpp.concrete;

/**
 *
 */
public interface IPropertyDefinitionFacade {
    String Name();

    void Name(String name);

    String Description();

    void Description(String description);

    boolean InitiallyExpanded();

    void InitiallyExpanded(boolean initialExpanded);

}
