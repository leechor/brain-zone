package com.zdpx.cctpp.simuApi;

/**
 *
 */
public interface IIntelligentObject extends IElementObject {
    FacilityLocation Location();

    void Location(FacilityLocation location);

    FacilitySize getSize();

    void setSize(FacilitySize size);

}
