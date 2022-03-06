package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.annotations.StepCategory;

@StepCategory(category = "Common Steps")
public class AssignStepDefinition extends AbsBaseStepDefinition<AssignStepDefinition> {
    private AssignStepDefinition() {
        super("Assign");
        super.Description(Resources.StepDescription_Assign);
        StatePropertyDefinition stateVariableName = new StatePropertyDefinition("StateVariableName");
        stateVariableName.Description(Resources.Assign_StateVariableName_Description);
        stateVariableName.DisplayName(Resources.Assign_StateVariableName_DisplayName);
        stateVariableName.DefaultString("");
        stateVariableName.CategoryName(Resources.StepPropertyCategory_BasicLogic);
        stateVariableName.RequiredValue(false);
        stateVariableName.IsAssignable(true);
        ExpressionPropertyDefinition newValue = new ExpressionPropertyDefinition("NewValue");
        newValue.Description(Resources.Assign_NewValue_Description);
        newValue.DisplayName(Resources.Assign_NewValue_DisplayName);
        newValue.DefaultString("0.0");
        newValue.CategoryName(Resources.StepPropertyCategory_BasicLogic);
        newValue.RequiredValue(false);
        newValue.UnitTypePropertyDefinition(stateVariableName);
        RepeatingPropertyDefinition assignments = new RepeatingPropertyDefinition("Assignments", this);
        assignments.Description(Resources.Assign_Assignments_Description);
        assignments.DisplayName(Resources.Assign_Assignments_DisplayName);
        assignments.CategoryName(Resources.StepPropertyCategory_BasicLogic);
        assignments.RequiredValue(false);
        StatePropertyDefinition assignmentsStateVariableName = new StatePropertyDefinition(
				"AssignmentsStateVariableName");
        assignmentsStateVariableName.Description(Resources.Assign_StateVariableName_Description);
        assignmentsStateVariableName.DisplayName(Resources.Assign_StateVariableName_DisplayName);
        assignmentsStateVariableName.DefaultString("");
        assignmentsStateVariableName.CategoryName(Resources.StepPropertyCategory_BasicLogic);
        assignmentsStateVariableName.RequiredValue(false);
        assignmentsStateVariableName.IsAssignable(true);
        ExpressionPropertyDefinition assignmentsNewValue = new ExpressionPropertyDefinition("AssignmentsNewValue");
        assignmentsNewValue.Description(Resources.Assign_NewValue_Description);
        assignmentsNewValue.DisplayName(Resources.Assign_NewValue_DisplayName);
        assignmentsNewValue.DefaultString("0.0");
        assignmentsNewValue.CategoryName(Resources.StepPropertyCategory_BasicLogic);
        assignmentsNewValue.RequiredValue(false);
        assignmentsNewValue.UnitTypePropertyDefinition(assignmentsStateVariableName);
        assignments.propertyDefinitions.add(assignmentsStateVariableName);
        assignments.propertyDefinitions.add(assignmentsNewValue);
        assignments.propertyDefinitions.seperator = " = ";
        super.getPropertyDefinitions().add(stateVariableName);
        super.getPropertyDefinitions().add(newValue);
        super.getPropertyDefinitions().add(assignments);
    }

    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return new AssignStepProperty(name);
    }
}
