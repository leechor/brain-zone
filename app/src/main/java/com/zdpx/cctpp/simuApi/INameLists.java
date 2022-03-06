package com.zdpx.cctpp.simuApi;

/**
 *
 */
public interface INameLists extends INamedSimioCollection<INamedList>, IMutableSimioCollection<INamedList> {
    INamedList addStringList(String name);

    INamedList addObjectList(String name);

    INamedList addNodeList(String name);

    INamedList addTransportList(String name);
}
