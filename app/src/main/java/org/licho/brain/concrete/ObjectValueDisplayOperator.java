package org.licho.brain.concrete;

import com.google.common.base.Strings;
import org.licho.brain.enu.OperatorCharactersEnum;

import java.text.MessageFormat;

/**
 *
 */
public class ObjectValueDisplayOperator {
    private double value;
    private String identifier;
    public OperatorCharactersEnum operatorCharactersEnum;
    private static ObjectValueDisplayOperator.ObjectValueMathOperator[] mathOperators = new ObjectValueMathOperator[]{
            new ObjectValueDisplayOperator.ObjectValueMathOperator("Infinity", Double.POSITIVE_INFINITY),
            new ObjectValueDisplayOperator.ObjectValueMathOperator("True", 1.0),
            new ObjectValueDisplayOperator.ObjectValueMathOperator("False", 0.0),
            new ObjectValueDisplayOperator.ObjectValueMathOperator("Math.EPSILON", Math.ulp(1.0)),
            new ObjectValueDisplayOperator.ObjectValueMathOperator("Math.E", 2.718281828459045),
            new ObjectValueDisplayOperator.ObjectValueMathOperator("Math.PI", 3.141592653589793),
            new ObjectValueDisplayOperator.ObjectValueMathOperator("Math.NaN", Double.NaN)
    };

    public ObjectValueDisplayOperator(double value, String identifier) {
        this.value = value;
        this.identifier = identifier;
        this.operatorCharactersEnum = OperatorCharactersEnum.Two;
    }

    public ObjectValueDisplayOperator() {

    }

    public ObjectValueDisplayOperator(OperatorCharactersEnum operatorCharactersEnum) {
        this.operatorCharactersEnum = operatorCharactersEnum;
        this.value = Double.NaN;
        this.identifier = null;
    }


    public static ObjectValueDisplayOperator.InnerObjectValue smethod_4(String value) {
        try {
            double result = 0.0;
            result = Double.parseDouble(value);
            return new ObjectValueDisplayOperator.InnerObjectValue(new ObjectValueDisplayOperator[]{
                    new ObjectValueDisplayOperator(result, value)
            });
        } catch (NumberFormatException ignored) {
        }

        if ("Nothing".equalsIgnoreCase(value)) {
            return new ObjectValueDisplayOperator.InnerObjectValue(new ObjectValueDisplayOperator[]{
                    ObjectValueDisplayOperator.smethod_2(value)
            });
        }

        for (ObjectValueDisplayOperator.ObjectValueMathOperator nameMathOperator :
                ObjectValueDisplayOperator.mathOperators) {
            if (nameMathOperator.display.equalsIgnoreCase(value)) {
                return new ObjectValueDisplayOperator.InnerObjectValue(new ObjectValueDisplayOperator[]{
                        new ObjectValueDisplayOperator(nameMathOperator.value, nameMathOperator.display)
                });
            }
        }

        if (value.startsWith(".")) {
            value = value.substring(1);
        }

        String[] pots = value.split("\\.");
        if (pots.length > 0) {
            if (pots.length == 3 && "Enum".equalsIgnoreCase(pots[0])) {
                // TODO: 2022/1/29
            }
            ObjectValueDisplayOperator[] parentObjects = new ObjectValueDisplayOperator[pots.length];
            if (pots[0].equalsIgnoreCase("ParentObject")) {
                parentObjects[0] = ObjectValueDisplayOperator.smethod_3(pots[0]);
                for (int k = 1; k < pots.length; k++) {
                    parentObjects[k] = ObjectValueDisplayOperator.smethod_0(pots[k]);
                }
            } else {
                for (int l = 0; l < pots.length; l++) {
                    parentObjects[l] = ObjectValueDisplayOperator.smethod_0(pots[l]);
                }
            }
            return new ObjectValueDisplayOperator.InnerObjectValue(parentObjects);
        }
        return ObjectValueDisplayOperator.InnerObjectValue.instance;
    }

    public static ObjectValueDisplayOperator smethod_1(String identifier) {
        var tmp = new ObjectValueDisplayOperator();
        tmp.operatorCharactersEnum = OperatorCharactersEnum.Three;
        tmp.identifier = identifier;
        tmp.value = Double.NaN;
        return tmp;
    }

    public String Identifier() {
        return this.identifier;
    }

    @Override
    public String toString() {
        if (this.operatorCharactersEnum == OperatorCharactersEnum.Three) {
            return MessageFormat.format("''{0}''", this.identifier);
        }
        if (!Strings.isNullOrEmpty(this.identifier)) {
            return this.identifier;
        }
        if (this.operatorCharactersEnum == OperatorCharactersEnum.Two) {
            return String.valueOf(this.value);
        }
        switch (this.operatorCharactersEnum) {
            case ParathesesPre:
                return "(";
            case ParathesesAfter:
                return ")";
            case Add:
                return " + ";
            case Sub:
                return " - ";
            case Multity:
                return " * ";
            case Divide:
                return " / ";
            case Comma:
                return ", ";
            case StraightEqual:
                return " == ";
            case Lower:
                return " < ";
            case LowerEqu:
                return " <= ";
            case Greater:
                return " > ";
            case GreaterEqu:
                return " >= ";
            case Exclamation:
                return "!";
            case NotEqual:
                return " != ";
            case Or:
                return " || ";
            case BitAnd:
                return " && ";
            case BracketPre:
                return "[";
            case BracketAfter:
                return "]";
            case XOR:
                return " ^ ";
            default:
                return null;
        }
    }

    private static ObjectValueDisplayOperator smethod_0(String display) {
        var result = new ObjectValueDisplayOperator();
        result.operatorCharactersEnum = OperatorCharactersEnum.One;
        result.identifier = display;
        result.value = Double.NaN;
        return result;
    }

    private static ObjectValueDisplayOperator smethod_3(String display) {
        var result = new ObjectValueDisplayOperator();
        result.operatorCharactersEnum = OperatorCharactersEnum.Five;
        result.identifier = display;
        result.value = Double.NaN;
        return result;
    }

    private static ObjectValueDisplayOperator smethod_2(String value) {
        var result = new ObjectValueDisplayOperator();

        result.operatorCharactersEnum = OperatorCharactersEnum.Four;
        result.identifier = value;
        result.value = Double.NaN;
        return result;
    }

    public static class InnerObjectValue {
        public InnerObjectValue() {
        }

        public InnerObjectValue(String objectValue) {
            this.objectValue = objectValue;
            this.NameValueDisplayOperators = null;
        }

        public InnerObjectValue(ObjectValueDisplayOperator[] nameValueDisplayOperators) {
            this.NameValueDisplayOperators = nameValueDisplayOperators;
            this.objectValue = null;
        }

        public static ObjectValueDisplayOperator.InnerObjectValue instance =
                new ObjectValueDisplayOperator.InnerObjectValue();

        public ObjectValueDisplayOperator[] NameValueDisplayOperators;

        public String objectValue;


    }

    private static class ObjectValueMathOperator {
        public ObjectValueMathOperator(String display, double value) {
            this.display = display;
            this.value = value;
        }

        public final String display;
        public final double value;
    }
}
