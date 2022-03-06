package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.entity.EntityRunSpace;
import com.zdpx.cctpp.concrete.fixed.FixedRunSpace;
import com.zdpx.cctpp.utils.simu.LinkType;

/**
 *
 */
public class LinkRunSpace extends FixedRunSpace {
    private LinkRunSpaceWrapper linkRunSpaceWrapper;
    private NodeRunSpace endNodeRunSpace;
    private Link link;
    private NodeRunSpace beginNodeRunSpace;
    public AbsBaseTrace[] absBaseTraces;
    private EntityRunSpace entityRunSpace;


    public LinkRunSpace(IntelligentObjectRunSpace statisticsDataSourceIntelligentObject,
                        MayApplication application, IntelligentObject intelligentObject) {
        super(statisticsDataSourceIntelligentObject, application, intelligentObject);
    }

    public LinkRunSpaceWrapper getLinkRunSpaceWrapper() {
        if (this.linkRunSpaceWrapper == null) {
            this.linkRunSpaceWrapper = new LinkRunSpaceWrapper(this);
        }
        return this.linkRunSpaceWrapper;
    }

    public NodeRunSpace getEndNodeRunSpace() {
        if (this.endNodeRunSpace == null) {
            this.endNodeRunSpace =
                    (NodeRunSpace) this.link.endNode.GetRunSpaceRecursionOutOfParent(this.ParentObjectRunSpace);
        }
        return this.endNodeRunSpace;
    }

    public NodeRunSpace getBeginNodeRunSpace() {
        if (this.beginNodeRunSpace == null) {
            this.beginNodeRunSpace =
                    (NodeRunSpace) this.link.beginNode.GetRunSpaceRecursionOutOfParent(this.ParentObjectRunSpace);
        }
        return this.beginNodeRunSpace;
    }

    public Cost Movement() {
        return (Cost) this.absBaseTraces[7];
    }

    public LinkType getLinkType() {
        return this.link.linkType;
    }

    @Override
    public void Initialize(boolean initializeState, boolean initializeStatistics) {
    }

    public DoubleTable_1 DesiredDirection() {
        return (DoubleTable_1) this.absBaseTraces[10];
    }

    public EntityRunSpace getEntityRunSpace() {
        return this.entityRunSpace;
    }

    public void method_88() {
        // TODO: 2022/2/8 
    }
}
