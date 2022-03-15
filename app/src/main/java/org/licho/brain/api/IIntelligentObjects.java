package org.licho.brain.api;

import org.licho.brain.concrete.IntelligentObject;

/**
 *
 */
public interface IIntelligentObjects extends INamedSimioCollection<IIntelligentObject> {
    IntelligentObject createObject(String type, FacilityLocation location);

    IntelligentObject createLink(String type, INodeObject startNode, INodeObject endNode,
                                 Iterable<FacilityLocation> points);

    void remote(IIntelligentObject facilityObject);

}
