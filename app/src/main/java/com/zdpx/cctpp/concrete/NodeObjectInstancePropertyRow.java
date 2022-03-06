package com.zdpx.cctpp.concrete;

/**
 *
 */
public class NodeObjectInstancePropertyRow extends ObjectInstancePropertyRow {
    public NodeObjectInstancePropertyRow(NodePropertyDefinition exitPointPropertyDefinition, Properties properties) {
        super(exitPointPropertyDefinition, properties);
    }

    public NodeRunSpace getNodeRunSpace(IRunSpace runSpace, IntelligentObjectRunSpace intelligentObjectRunSpace) {
        return this.getExpressionResult(runSpace, intelligentObjectRunSpace, -1);
    }

    public NodeRunSpace getExpressionResult(IRunSpace runSpace, IntelligentObjectRunSpace intelligentObjectRunSpace,
											int index) {
        return (NodeRunSpace) super.getExpressionResult(runSpace, intelligentObjectRunSpace, index, null);
    }

}
