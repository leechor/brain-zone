package org.licho.brain.api;

/**
 *
 */
public interface ILinkVertices extends ISimioCollection<FacilityLocation> {
    void setAt(int index, FacilityLocation vertex);

    void insertAt(int index, FacilityLocation vertex);

    void insertRange(int index, Iterable<FacilityLocation> vertex);
}
