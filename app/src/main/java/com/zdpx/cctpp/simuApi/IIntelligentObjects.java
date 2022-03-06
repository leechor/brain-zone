package com.zdpx.cctpp.simuApi;

import com.zdpx.cctpp.concrete.IntelligentObject;

/**
 *
 */
public interface IIntelligentObjects extends INamedSimioCollection<IIntelligentObject> {
    IntelligentObject createObject(String type, FacilityLocation location);

    IntelligentObject createLink(String type, INodeObject startNode, INodeObject endNode,
                                 Iterable<FacilityLocation> points);

    void remote(IIntelligentObject facilityObject);

}
