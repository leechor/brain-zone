package org.licho.brain.concrete.property;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.exception.ExpressionResultTypeException;
import org.licho.brain.enu.ExpressionResultType;
import org.licho.brain.utils.ExtensionString;

import java.text.MessageFormat;
import java.util.Arrays;

import static org.licho.brain.concrete.cont.ErrorString.ERROR_EXPRESSION_RESULT_DATA_TYPE_MISMATCH_EXCEPTION;

/**
 *
 */
public class ExpressionValue implements Comparable<ExpressionValue> {

    private final double value;

    private final String valueString;

    private final AbsBaseRunSpace absBaseRunSpace;

    private final ExpressionResultType expressionResultType;

    public static final ExpressionValue Instance = new ExpressionValue(ExpressionResultType.Any);

    public ExpressionValue(ExpressionResultType expressionResultType) {
        this.value = 0.0;
        this.valueString = null;
        this.absBaseRunSpace = null;
        this.expressionResultType = expressionResultType;
    }

    public ExpressionValue(double value) {
        this.value = value;
        this.valueString = null;
        this.absBaseRunSpace = null;
        this.expressionResultType = ExpressionResultType.Number;
    }

    public ExpressionValue(String value) {
        this.value = 0.0;
        this.valueString = value == null ? "" : value;
        this.absBaseRunSpace = null;
        this.expressionResultType = ExpressionResultType.STRING;
    }

    public ExpressionValue(AbsBaseRunSpace value) {
        this.value = 0.0;
        this.valueString = null;
        this.absBaseRunSpace = value;
        this.expressionResultType = ExpressionResultType.ElementReference;
    }

    public String getStringValue() {
        if (this.expressionResultType != ExpressionResultType.STRING) {
            switch (this.expressionResultType) {
                case Number: {
                    throw new ExpressionResultTypeException(
                            MessageFormat.format(ERROR_EXPRESSION_RESULT_DATA_TYPE_MISMATCH_EXCEPTION,
                                    value,
                                    ExtensionString.smethod_0(ExpressionResultType.Number.toString()),
                                    ExtensionString.smethod_0(ExpressionResultType.STRING.toString())));
                }

                case ElementReference:
                    throw new ExpressionResultTypeException(
                            MessageFormat.format(ERROR_EXPRESSION_RESULT_DATA_TYPE_MISMATCH_EXCEPTION,
                                    absBaseRunSpace != null ?
                                            absBaseRunSpace.getHierarchicalDisplayName() : "[Nothing]",
                                    ExtensionString.smethod_0(ExpressionResultType.ElementReference.toString()),
                                    ExtensionString.smethod_0(ExpressionResultType.STRING.toString())));
                case Any:
                    break;
            }
        }
        return this.valueString;
    }

    public static ExpressionValue from(AbsBaseRunSpace absBaseRunSpace) {
        return new ExpressionValue(absBaseRunSpace);
    }

    public static ExpressionValue from(double absBaseRunSpace) {
        return new ExpressionValue(absBaseRunSpace);
    }

    public static ExpressionValue from(String absBaseRunSpace) {
        return new ExpressionValue(absBaseRunSpace);
    }

    public AbsBaseRunSpace toRunSpace() {
        return this.getAbsBaseRunSpace();
    }

    public int toInt() {
        return (int) this.value;
    }

    public double toDouble() {
        return this.value;
    }

    public String getExpressionResultString() {
        if (this.expressionResultType != ExpressionResultType.STRING) {
            switch (this.expressionResultType) {
                case Number:
                    throw new IllegalArgumentException(MessageFormat.format(EngineResources.Error_ExpressionResult_DataTypeMismatchException,
                            this.value, Arrays.toString(ExpressionResultType.Number.toString().split(" ")),
                            Arrays.toString(ExpressionResultType.STRING.toString().split(" "))));
                case ElementReference:
                    throw new IllegalArgumentException(MessageFormat.format(EngineResources.Error_ExpressionResult_DataTypeMismatchException,
                            (this.absBaseRunSpace != null) ? this.absBaseRunSpace.HierarchicalDisplayName() :
                                    "[Nothing]",
                            Arrays.toString(ExpressionResultType.ElementReference.toString().split(" ")),
                            Arrays.toString(ExpressionResultType.STRING.toString().split(" "))));
            }
        }
        return this.valueString;
    }

    public double getExpressionResult() {
        if (this.expressionResultType != ExpressionResultType.Number) {
            switch (this.expressionResultType) {
                case STRING:
                    throw new IllegalArgumentException(MessageFormat.format(EngineResources.Error_ExpressionResult_DataTypeMismatchException,
                            (this.valueString != null) ? this.valueString : "[Nothing]",
                            Arrays.toString(ExpressionResultType.STRING.toString().split(" ")),
                            Arrays.toString(ExpressionResultType.Number.toString().split(" "))));
                case ElementReference:
                    throw new IllegalArgumentException(MessageFormat.format(EngineResources.Error_ExpressionResult_DataTypeMismatchException,
                            (this.absBaseRunSpace != null) ?
                                    this.absBaseRunSpace.HierarchicalDisplayName() : "[Nothing]",
                            Arrays.toString(ExpressionResultType.ElementReference.toString().split("")),
                            Arrays.toString(ExpressionResultType.Number.toString().split(""))));
            }
        }
        return this.value;
    }

    public AbsBaseRunSpace getAbsBaseRunSpace() {
        if (this.expressionResultType != ExpressionResultType.ElementReference) {
            switch (this.expressionResultType) {
                case Number:
                    throw new IllegalArgumentException(MessageFormat.format(EngineResources.Error_ExpressionResult_DataTypeMismatchException,
                            this.value, Arrays.toString(ExpressionResultType.Number.toString().split(" ")),
                            Arrays.toString(ExpressionResultType.ElementReference.toString().split(" "))));
                case STRING:
                    throw new IllegalArgumentException(MessageFormat.format(EngineResources.Error_ExpressionResult_DataTypeMismatchException,
                            (this.valueString != null) ? this.valueString : "[Nothing]",
                            Arrays.toString(ExpressionResultType.STRING.toString().split(" ")),
                            Arrays.toString(ExpressionResultType.ElementReference.toString().split(" "))));
            }
        }
        return this.absBaseRunSpace;
    }

    @Override
    public int compareTo(ExpressionValue o) {
        return 0;
    }


}
