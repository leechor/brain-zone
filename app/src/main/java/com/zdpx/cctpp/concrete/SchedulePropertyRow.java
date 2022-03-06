package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.cont.EngineResources;
import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.enu.Enum38;
import com.zdpx.cctpp.enu.PropertyGridFeel;
import com.zdpx.cctpp.enu.ScheduleType;

/**
 *
 */
public class SchedulePropertyRow extends NumericDataPropertyRow {
    private WorkSchedule workSchedule;

    public SchedulePropertyRow(StringPropertyDefinition propertyDefinitionInfo, Properties properties) {
        super(propertyDefinitionInfo, properties);
    }

    @Override
    public Object GetObjectValue() {
        return this.workSchedule;
    }

    public WorkSchedule getWorkSchedule(IRunSpace runSpace, IntelligentObjectRunSpace intelligentObjectRunSpace) {
        return (WorkSchedule) (super.getObjectValuePure(runSpace, intelligentObjectRunSpace));
    }

    public WorkSchedule getWorkSchedule(IRunSpace runSpace, IntelligentObjectRunSpace intelligentObjectRunSpace,
                                        AbsBaseRunSpace absBaseRunSpace, int index) {
        return (WorkSchedule) super.getObjectValue(runSpace, intelligentObjectRunSpace, absBaseRunSpace, index);
    }

    @Override
    public void UpdateForNewPropertyDefinition() {
        this.StringValue(super.getStringPropertyDefinitionInfo().NullNullString());
    }

    @Override
    public boolean ShouldRecompileWhenGivenNewDefinition() {
        return true;
    }

    @Override
    public String CompileValue() {
        super.clear();
        this.workSchedule = null;
        String name = super.formatName(super.getObjectNameMaybe());
        if (super.isInvalidObjectNameValue(null)) {
            if (super.getStringPropertyDefinitionInfo().RequiredValue()) {
                return EngineResources.ErrorRequiredValueNotSpecified;
            }
            return null;
        } else {
            if (super.method_31(name, SchedulePropertyDefinition.class, 255)) {
                return null;
            }
            SchedulePropertyDefinition schedulePropertyDefinition =
                    (SchedulePropertyDefinition) super.getStringPropertyDefinitionInfo();
            ScheduleType typeOfSchedule = schedulePropertyDefinition.TypeOfSchedule();
            IntelligentObjectDefinition intelligentObjectDefinition =
                    super.getExperimentConstraintsIntelligentObjectDefinition();
            if (intelligentObjectDefinition != null) {
                for (WorkSchedule schedule :
                        intelligentObjectDefinition.getWorkSchedulesUtils().getWorkSchedules().values) {
                    if (StringHelper.equalsLocal(name, schedule.Name())) {
                        if (typeOfSchedule == ScheduleType.CapacitySchedule) {
                            this.workSchedule = schedule;
                            return null;
                        }
                        return EngineResources.CompileError_InvalidScheduleReference;
                    }
                }
            }
            return EngineResources.CompileError_InvalidScheduleName;
        }
    }

    @Override
    public GridItemProperty GetGridItemProperty(PropertyDefinitions definitions) {
        return new GridItemProperty(super.getStringPropertyDefinitionInfo().Name(),
                super.getStringPropertyDefinitionInfo().GetCategoryName(definitions),
                super.getStringPropertyDefinitionInfo().overRidePropertyIndex + 1000, this.StringValue(),
                super.getDefaultName(definitions), PropertyGridFeel.editlist,
                super.getStringPropertyDefinitionInfo().GetDisplayName(definitions),
                super.getStringPropertyDefinitionInfo().GetDescription(definitions),
                new SubPropertyOperator_0<>(String.class, this, this::StringValue,
                        this::StringValue, null, this::GetCandidatePropertyReferences));
    }

    @Override
    public String StringValue() {
        if (this.workSchedule != null) {
            return this.workSchedule.Name();
        }
        return super.StringValue();
    }

    @Override
    public void StringValue(String value) {
        super.StringValue(value);
    }

    @Override
    public void UpdateForParentObjectWorkScheduleChange(WorkSchedule workSchedule, Enum38 enum38) {
        if (workSchedule == this.workSchedule && enum38 == Enum38.Zero) {
            super.processPropertyChange();
            return;
        }
        super.UpdateForParentObjectWorkScheduleChange(workSchedule, enum38);
    }
}

