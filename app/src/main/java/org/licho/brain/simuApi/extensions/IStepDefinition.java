package org.licho.brain.simuApi.extensions;

import org.licho.brain.concrete.Guid;
import org.licho.brain.concrete.IPropertyDefinitions;
import org.licho.brain.resource.Image;
import org.licho.brain.simuApi.IPropertyReaders;

/**
 *
 */
public interface IStepDefinition {
    String Name();

    String Description();

    Image Icon();

    Guid UniqueID();

    void DefineSchema(IPropertyDefinitions propertyDefinitions);


    int NumberOfExits();

    IStep CreateStep(IPropertyReaders properties);

}