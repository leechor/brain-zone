package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.entity.EntityRunSpace;
import com.zdpx.cctpp.enu.ConstraintType;
import com.zdpx.cctpp.utils.simu.IAssociatedObjectRunSpace;

/**
 *
 */
public class NodeEntryAssociatedObjectRunSpace implements IAssociatedObjectRunSpace {

    private TokenRunSpace tokenRunSpace;
    private EntityRunSpace entityRunSpace;
    private NodeRunSpace nodeRunSpace;
    private NodeEntryAssociatedObjectRunSpace nodeEntryAssociatedObjectRunSpace;
    private Object constraintRecord;


    void init(TokenRunSpace tokenRunSpace, EntityRunSpace entityRunSpace, NodeRunSpace nodeRunSpace) {
        this.tokenRunSpace = tokenRunSpace;
        this.entityRunSpace = entityRunSpace;
        this.nodeRunSpace = nodeRunSpace;
        MayApplication mayApplication = this.entityRunSpace.getMayApplication();
        this.constraintRecord = mayApplication.getConstraintRecord(this.entityRunSpace,
                ConstraintType.NodeAvailability, this.nodeRunSpace.Name(), this.nodeRunSpace.ConstraintDescription());
    }


    public TokenRunSpace getTokenRunSpace() {
        return this.tokenRunSpace;
    }


    public EntityRunSpace getEntityRunSpace() {
        return this.entityRunSpace;
    }


    public NodeRunSpace getNodeRunSpace() {
        return this.nodeRunSpace;
    }


    public NodeEntryAssociatedObjectRunSpace getNodeEntryAssociatedObjectRunSpace() {
        return this.nodeEntryAssociatedObjectRunSpace;
    }


    public void setNodeEntryAssociatedObjectRunSpace(NodeEntryAssociatedObjectRunSpace nodeEntryAssociatedObjectRunSpace) {
        this.nodeEntryAssociatedObjectRunSpace = nodeEntryAssociatedObjectRunSpace;
    }


    public void method_6() {
        MayApplication application = this.entityRunSpace.getMayApplication();
        application.method_16(this.constraintRecord);
        application.getNodeEntryAssociatedObjectRunSpaceOperator().setNodeEntryAssociatedObjectRunSpace(this);
    }


    void clear() {
        this.tokenRunSpace = null;
        this.entityRunSpace = null;
        this.nodeRunSpace = null;
        this.nodeEntryAssociatedObjectRunSpace = null;
        this.constraintRecord = null;
    }


    public IntelligentObjectRunSpace AssociatedObjectRunSpace() {
        return this.entityRunSpace;

    }


}
