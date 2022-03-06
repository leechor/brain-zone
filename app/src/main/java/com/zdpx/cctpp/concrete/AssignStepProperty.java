package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseStepProperty;

/**
 *
 */
public class AssignStepProperty extends AbsStepProperty<AssignStepDefinition> {
    private StatePropertyRow stateVariableName;
    private ExpressionPropertyRow newValue;
    private RepeatStringPropertyRow assignments;

    public AssignStepProperty(String name) {
        super(AssignStepDefinition.class, name);
    }

    @Override
    protected void initProperties() {
        this.stateVariableName =
                (StatePropertyRow) this.properties.search(t -> "StateVariableName".equals(t.Name()));

        this.newValue =
                (ExpressionPropertyRow) this.properties.search(t -> "NewValue".equals(t.Name()));

        this.assignments =
                (RepeatStringPropertyRow) this.properties.search(t -> "Assignments".equals(t.Name()));
        super.initProperties();
    }

    public StatePropertyRow getStateVariableName() {
        return this.stateVariableName;
    }

    public ExpressionPropertyRow getNewValue() {
        return this.newValue;
    }

    public RepeatStringPropertyRow getAssignments() {
        return this.assignments;
    }

    @Override
    protected AbsBaseStepProperty.Enum15 vmethod_5(IntelligentObjectRunSpace intelligentObjectRunSpace) {
        try {
            if (this.getStateVariableName().isInvalidObjectNameValue(null, intelligentObjectRunSpace) == 0.0 &&
                    this.getAssignments().method_80(null, intelligentObjectRunSpace, null) == 0) {
                return AbsBaseStepProperty.Enum15.One;
            }
        } catch (Exception ignored) {
        }
        return super.vmethod_5(intelligentObjectRunSpace);
    }

    @Override
    public void execute(TokenRunSpace tokenRunSpace){

    }
}
