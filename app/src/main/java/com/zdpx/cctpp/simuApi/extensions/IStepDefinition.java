package com.zdpx.cctpp.simuApi.extensions;

import com.zdpx.cctpp.concrete.Guid;
import com.zdpx.cctpp.concrete.IPropertyDefinitions;
import com.zdpx.cctpp.resource.Image;
import com.zdpx.cctpp.simuApi.IPropertyReaders;

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