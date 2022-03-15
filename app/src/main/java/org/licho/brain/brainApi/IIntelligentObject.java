package org.licho.brain.brainApi;

/**
 *
 */
public interface IIntelligentObject extends IElementObject {
    FacilityLocation Location();

    void Location(FacilityLocation location);

    FacilitySize getSize();

    void setSize(FacilitySize size);

}
