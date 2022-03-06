package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.IFunction.Action;

import java.util.Iterator;
import java.util.List;

/**
 *
 */
public interface ILibraries {
    ILibrary get();

    int NumberOfLibraries();

    void ForEachExternalLibrary(Action<ILibrary> action);

    void ForEachExternalLibraryRef(Action<ILibraryRef> action);

    IntelligentObjectDefinition ObjectDefinitionFor(String name);

    List<?> ListOfAll();
}
