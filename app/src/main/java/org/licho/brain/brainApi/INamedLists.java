package org.licho.brain.brainApi;

/**
 *
 */
public interface INamedLists extends INamedSimioCollection<INamedList> {
    INamedList AddStringList(String Name);

    INamedList AddObjectList(String Name);

    INamedList AddNodeList(String Name);

    INamedList AddTransporterList(String Name);
}
