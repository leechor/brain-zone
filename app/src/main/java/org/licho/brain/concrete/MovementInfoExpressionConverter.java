package org.licho.brain.concrete;

import org.licho.brain.concrete.property.ExpressionValue;
import org.licho.brain.enu.UnitType;
import org.licho.brain.utils.simu.IUnit;

/**
 *
 */
public class MovementInfoExpressionConverter implements IExpressionConverter<MovementInfo> {


    public MovementInfo FromExpressionResult(MovementInfo movementInfo, ExpressionValue expressionValue, int param2,
                                             AbsBaseTrace absBaseTrace) {
        switch (param2) {
            case -1:
                movementInfo.method_5(expressionValue.getExpressionResult(),
                        absBaseTrace.getAbsBaseRunSpace().getSomeRun().TimeNow());
                break;
            case 0:
                movementInfo.method_6(expressionValue.getExpressionResult(),
                        absBaseTrace.getAbsBaseRunSpace().getSomeRun().TimeNow());
                break;
            case 1:
                movementInfo.method_8(expressionValue.getExpressionResult(),
                        absBaseTrace.getAbsBaseRunSpace().getSomeRun().TimeNow());
                break;
            case 2:
                movementInfo.method_9(expressionValue.getExpressionResult(),
                        absBaseTrace.getAbsBaseRunSpace().getSomeRun().TimeNow());
                break;
        }
        return movementInfo;
    }

    public ExpressionValue ToExpressionResult(MovementInfo movementInfo, int param1, AbsBaseTrace absBaseTrace) {
        switch (param1) {
            case -1:
                return ExpressionValue.from(movementInfo.method_10(absBaseTrace.getAbsBaseRunSpace().getSomeRun().TimeNow()));
            case 0:
                return ExpressionValue.from(movementInfo.getRate(absBaseTrace.getAbsBaseRunSpace().getSomeRun().TimeNow()));
            case 1:
                return ExpressionValue.from(movementInfo.Acceleration());
            case 2:
                return ExpressionValue.from(movementInfo.AccelerationDuration());
            default:
                return ExpressionValue.Instance;
        }
    }

    public String FormattedValue(IUnit unit, MovementInfo sizeInfo, int param2, AbsBaseTrace absBaseTrace) {
        switch (param2) {
            case -1:
                return AbouleUnitTypeConvertor.smethod_0(sizeInfo.method_10(absBaseTrace.getAbsBaseRunSpace().getSomeRun().TimeNow()), unit, absBaseTrace.getBaseSomeIGridItemProperties().UnitType());
            case 0:
                return AbouleUnitTypeConvertor.smethod_0(sizeInfo.getRate(absBaseTrace.getAbsBaseRunSpace().getSomeRun().TimeNow()), unit, AbouleUnitTypeConvertor.smethod_1(absBaseTrace.getBaseSomeIGridItemProperties().UnitType()));
            case 1:
                return AbouleUnitTypeConvertor.smethod_0(sizeInfo.Acceleration(), unit,
                        AbouleUnitTypeConvertor.smethod_2(absBaseTrace.getBaseSomeIGridItemProperties().UnitType()));
            case 2:
                return AbouleUnitTypeConvertor.smethod_0(sizeInfo.AccelerationDuration(), unit, UnitType.Time);
            default:
                return null;
        }
    }

    public MovementInfo GetInitialValue(AbsBaseTrace absBaseTrace,
                                        IntelligentObjectRunSpace intelligentObjectRunSpace) {
        SecondOrderStatePropertyObject secondOrderStatePropertyObject =
                (SecondOrderStatePropertyObject) absBaseTrace.getBaseSomeIGridItemProperties().StatePropertyObject;
        double num = (intelligentObjectRunSpace != null) ? intelligentObjectRunSpace.getSomeRun().TimeNow() : 0.0;
        return new MovementInfo(secondOrderStatePropertyObject.Value(), secondOrderStatePropertyObject.RateValue(), num,
                secondOrderStatePropertyObject.AccelerationValue(), secondOrderStatePropertyObject.DurationValue(),
                num + secondOrderStatePropertyObject.DurationValue(), secondOrderStatePropertyObject.Value(),
                secondOrderStatePropertyObject.RateValue());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
