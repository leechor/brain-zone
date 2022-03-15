package org.licho.brain.concrete;

import org.licho.brain.annotations.SubDisplayName;
import org.licho.brain.enu.Enum53;
import org.licho.brain.api.containers.SpecialEditor;
import org.licho.brain.utils.simu.system.PropertyDescriptor;

import java.beans.IntrospectionException;
import java.lang.annotation.Annotation;

/**
 *
 */
public class WorkSchedulePropertyDescriptor extends PropertyDescriptor {
    private final int index;

    public WorkSchedulePropertyDescriptor(String propertyName, int number) throws IntrospectionException {
        super(propertyName, new Annotation[]{
                new SubDisplayName() {
                    @Override
                    public Class<? extends Annotation> annotationType() {
                        return SubDisplayName.class;
                    }

                    @Override
                    public String value() {
                        return propertyName;
                    }
                },
                new SpecialEditor() {
                    @Override
                    public Class<? extends Annotation> annotationType() {
                        return SpecialEditor.class;
                    }

                    @Override
                    public Enum53 value() {
                        return Enum53.One;
                    }
                }
        }, WorkSchedule.class);

        this.index = number;

    }

    int Index() {
        return this.index;
    }

    @Override
    public Class<?> ComponentType() {
        return WorkSchedule.class;
    }

    @Override
    public Class<?> PropertyType() {
        return String.class;
    }

    @Override
    public boolean IsReadOnly() {
        return false;
    }

    @Override
    public Object GetValue(Object param0) {
        WorkSchedule workSchedule = null;
        if (param0 instanceof WorkSchedule) {
            workSchedule = (WorkSchedule) param0;
            return null;
        }
        if (this.index >= workSchedule.DayPatternRefs().size()) {
            return null;
        }
        DayPatternRef dayPatternRef = workSchedule.DayPatternRefs().get(this.index);
        if (dayPatternRef == null) {
            return null;
        }
        return dayPatternRef.DayPatternName();
    }

    @Override
    public void SetValue(Object target, Object param1) {
        if (!(target instanceof WorkSchedule)) {
            return;
        }

        WorkSchedule workSchedule = (WorkSchedule) target;
        if (this.index >= workSchedule.DayPatternRefs().size()) {
            return;
        }
        String dayPatternName = (param1 != null) ? param1.toString() : null;
        DayPatternRef dayPatternRef = workSchedule.DayPatternRefs().get(this.index);
        dayPatternRef.DayPatternName(dayPatternName);
        workSchedule.updatePropertyName(this.index);
    }

    @Override
    public boolean CanResetValue(Object param0) {
        return false;
    }

    @Override
    public void ResetValue(Object param0) {
    }

    @Override
    public boolean ShouldSerializeValue(Object param0) {
        return false;
    }


}
