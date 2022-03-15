package org.licho.brain.concrete;

import org.licho.brain.IFunction.Action;

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
