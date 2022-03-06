package com.zdpx.cctpp.simuApi;

/**
 *
 */
public interface INamedList {
    String Name();

    String Description();

    void Description(String description);

    IRows Rows();
}
