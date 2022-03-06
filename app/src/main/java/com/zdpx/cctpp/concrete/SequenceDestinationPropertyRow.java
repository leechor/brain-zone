package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.node.Node;
import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.concrete.property.ExpressionValue;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.enu.Enum38;
import com.zdpx.cctpp.enu.PropertyGridFeel;
import com.zdpx.cctpp.enu.UnitType;
import com.zdpx.cctpp.utils.simu.IListeners;

import java.util.List;

/**
 *
 */
public class SequenceDestinationPropertyRow extends IntelligentObjectProperty implements IListeners {
    private IntelligentObject intelligentObjectProperty;

    // Token: 0x04002299 RID: 8857
    private Node node;

    // Token: 0x0400229A RID: 8858
    private IExpression expression;

    public SequenceDestinationPropertyRow(StringPropertyDefinition propertyDefinitionInfo, Properties properties) {
        super(propertyDefinitionInfo, properties);
    }

    @Override
    protected PropertyGridFeel DefaultFeelName() {
        return PropertyGridFeel.editlist;
    }

    @Override
    public String StringValue() {
        if (this.node != null) {
            if (this.intelligentObjectProperty == null) {
                return this.node.InstanceName();
            }
            IntelligentObjectDefinition intelligentObjectDefinition =
                    (IntelligentObjectDefinition) this.intelligentObjectProperty.objectDefinition;
            if (this.node.NodeClassProperty.InputLocationType() != NodeInputLogicType.None && intelligentObjectDefinition.haveOneTransferPoints() && !((SequenceDestinationPropertyDefinition) super.getStringPropertyDefinitionInfo()).AcceptsAnyNode()) {
                return this.intelligentObjectProperty.InstanceName();
            }
            return this.node.NodeClassProperty.InstanceName() + "@" + this.intelligentObjectProperty.InstanceName();
        } else {
            if (this.expression != null) {
                return this.expression.toString();
            }
            return super.StringValue();
        }
    }

    public void StringValue(String value) {
        super.StringValue(value);
    }

    @Override
    public Object GetNativeObject() {
        if (super.isInvalidObjectNameValue(null)) {
            return null;
        }
        return this.StringValue();
    }

    @Override
    public String CompileValue() {
        // TODO: 2022/1/25
        return null;
    }

    public NodeRunSpace getExpressionResult(IRunSpace runSpace, IntelligentObjectRunSpace intelligentObjectRunSpace,
                                            int index, AbsBaseRunSpace baseRunSpace) {
        return (NodeRunSpace) (super.getExpressionResult(runSpace, intelligentObjectRunSpace, baseRunSpace, null,
                intelligentObjectRunSpace != null && intelligentObjectRunSpace.getMayApplication().DisableRandomness()
                , index).getAbsBaseRunSpace());
    }

    @Override
    protected ExpressionValue GetValueCore(IRunSpace runSpace,
                                           IntelligentObjectRunSpace intelligentObjectRunSpace,
                                           AbsBaseRunSpace absBaseRunSpace,
                                           IntelligentObjectRunSpace objectRunSpace, boolean param4) {
        if (this.node != null) {
            return ExpressionValue.from(this.node.GetRunSpaceRecursionOutOfParent(intelligentObjectRunSpace));
        }
        if (this.expression != null) {
            return ExpressionValue.from(this.expression.GetExpressionValue(runSpace, intelligentObjectRunSpace,
                    absBaseRunSpace,
                    UnitType.Unspecified, 0, intelligentObjectRunSpace.getMayApplication().UnitConversions(), false,
                    this).getAbsBaseRunSpace());
        }
        return new ExpressionValue((AbsBaseRunSpace) null);
    }

    @Override
    public void UpdateForParentObjectMemberElementChange(AbsIntelligentPropertyObject absIntelligentPropertyObject,
                                                         Enum38 enum38) {
        if (this.node == absIntelligentPropertyObject || this.intelligentObjectProperty == absIntelligentPropertyObject) {
            if (enum38 == Enum38.Zero || enum38 == Enum38.Two) {
                super.processPropertyChange();
                return;
            }
            if (enum38 == Enum38.One) {
                super.PropertyUpdated();
            }
        } else {
            super.UpdateForParentObjectMemberElementChange(absIntelligentPropertyObject, enum38);
        }
    }

    @Override
    public Iterable<IListener> Listeners() {

        if (this.expression != null) {
            return List.of(ExpressionAction.createExpressionActionListener(this.expression,
                    enum38 -> {
                        if (enum38 == Enum38.Zero || enum38 == Enum38.Two) {
                            super.processPropertyChange();
                            return;
                        }
                        if (enum38 == Enum38.One) {
                            super.setObjectName(this.StringValue());
                            super.PropertyUpdated();
                        }
                    }, null));
        }
        return List.of();

    }

    @Override
    public String getName() {
        return null;
    }

    public Node getNode() {
        return this.node;
    }
}
