package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.cont.EngineResources;
import com.zdpx.cctpp.concrete.property.NameValidOperator;
import com.zdpx.cctpp.enu.OperatorCharactersEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 *
 */
public class ObjectValuePotsOperator {

    private static final char[] specialChars = "()+-*/,=<>!|&[]^\"".toCharArray();

    public static int IndexOfAny(String objectValue, char[] specialChars, int startIndex) {
        for (int i = 0; i < specialChars.length; i++) {
            if (objectValue.indexOf(specialChars[i]) != -1) {
                return i;
            }
        }
        return -1;
    }

    public static ObjectValueWrapper smethod_0(String objectValue) {
        objectValue = NameValidOperator.smethod_0(objectValue);
        List<ObjectValueDisplayOperator> list = new ArrayList<>();
        int index = 0;
        int specialIndex = ObjectValuePotsOperator.IndexOfAny(objectValue, specialChars, index);
        int lastIndex = objectValue.length() - 1;
        Stack<OperatorCharactersEnum> stack = new Stack<>();
        while (index < lastIndex && specialIndex >= 0) {
            if (index < specialIndex) {
                String preValue = objectValue.substring(index, specialIndex - index);
                ObjectValueDisplayOperator.InnerObjectValue innerObjectValue =
                        ObjectValueDisplayOperator.smethod_4(preValue);
                if (innerObjectValue.objectValue != null) {
                    return new ObjectValueWrapper(innerObjectValue.objectValue);
                }
                if (innerObjectValue.NameValueDisplayOperators != null) {
                    list.addAll(Arrays.asList(innerObjectValue.NameValueDisplayOperators));
                }
            }

            char specialChar = objectValue.charAt(specialIndex);
            if (specialChar <= '>') {
                switch (specialChar) {
                    case '!': {
                        if (specialIndex == lastIndex) {
                            return new ObjectValueWrapper(EngineResources.ErrorInvalidCharFollowingNOT);
                        }
                        char c = objectValue.charAt(specialIndex + 1);
                        if (c == '=') {
                            list.add(new ObjectValueDisplayOperator(OperatorCharactersEnum.NotEqual));
                            specialIndex++;
                        } else {
                            list.add(new ObjectValueDisplayOperator(OperatorCharactersEnum.Exclamation));
                        }
                        break;
                    }
                    case '"': {
                        NameValidOperator.InnerError innerError = NameValidOperator.smethod_1(objectValue,
                                specialIndex);
                        if (innerError.Error != null) {
                            return new ObjectValueWrapper(innerError.Error);
                        }
                        String value = objectValue.substring(specialIndex + 1, innerError.index - (specialIndex + 1));
                        list.add(ObjectValueDisplayOperator.smethod_1(value.replace("\"\"", "\"")));
                        specialIndex = innerError.index;
                        break;
                    }
                    case '#':
                    case '$':
                    case '%':
                    case '\'':
                    case '.':
                        break;
                    case '&': {
                        if (specialIndex == lastIndex || objectValue.charAt(++specialIndex) != '&') {
                            return new ObjectValueWrapper(EngineResources.ErrorInvalidCharFollowingAND);
                        }
                        list.add(new ObjectValueDisplayOperator(OperatorCharactersEnum.BitAnd));
                        break;
                    }
                    case '(': {
                        stack.push(OperatorCharactersEnum.ParathesesPre);
                        list.add(new ObjectValueDisplayOperator(OperatorCharactersEnum.ParathesesPre));
                        break;
                    }
                    case ')': {
                        if (stack.size() == 0 || stack.peek() != OperatorCharactersEnum.ParathesesPre) {
                            return new ObjectValueWrapper(EngineResources.ErrorUnbalancedParatheses);
                        }
                        stack.pop();
                        list.add(new ObjectValueDisplayOperator(OperatorCharactersEnum.ParathesesAfter));
                        break;
                    }
                    case '*':
                        list.add(new ObjectValueDisplayOperator(OperatorCharactersEnum.Multity));
                        break;
                    case '+':
                        list.add(new ObjectValueDisplayOperator(OperatorCharactersEnum.Add));
                        break;
                    case ',':
                        list.add(new ObjectValueDisplayOperator(OperatorCharactersEnum.Comma));
                        break;
                    case '-':
                        list.add(new ObjectValueDisplayOperator(OperatorCharactersEnum.Sub));
                        break;
                    case '/':
                        list.add(new ObjectValueDisplayOperator(OperatorCharactersEnum.Divide));
                        break;
                    default:
                        switch (specialChar) {

                            case '<': {
                                if (specialIndex == lastIndex) {
                                    return new ObjectValuePotsOperator.ObjectValueWrapper(EngineResources.ErrorInvalidCharFollowingLT);
                                }
                                char c = objectValue.charAt(specialIndex + 1);
                                if (c == '=') {
                                    list.add(new ObjectValueDisplayOperator(OperatorCharactersEnum.LowerEqu));
                                    specialIndex++;
                                } else {
                                    list.add(new ObjectValueDisplayOperator(OperatorCharactersEnum.Lower));
                                }
                                break;
                            }
                            case '=':
                                if (specialIndex == lastIndex || objectValue.charAt(++specialIndex) != '=') {
                                    return new ObjectValuePotsOperator.ObjectValueWrapper(EngineResources.ErrorInvalidCharFollowingEQ);
                                }
                                list.add(new ObjectValueDisplayOperator(OperatorCharactersEnum.StraightEqual));
                                break;
                            case '>': {
                                if (specialIndex == lastIndex) {
                                    return new ObjectValuePotsOperator.ObjectValueWrapper(EngineResources.ErrorInvalidCharFollowingGT);
                                }
                                char c = objectValue.charAt(specialIndex + 1);
                                if (c == '=') {
                                    list.add(new ObjectValueDisplayOperator(OperatorCharactersEnum.GreaterEqu));
                                    specialIndex++;
                                } else {
                                    list.add(new ObjectValueDisplayOperator(OperatorCharactersEnum.Greater));
                                }
                                break;
                            }
                        }
                }
            } else {

                switch (specialChar) {
                    case '[':
                        stack.push(OperatorCharactersEnum.BracketPre);
                        list.add(new ObjectValueDisplayOperator(OperatorCharactersEnum.BracketPre));
                        break;
                    case '\\':
                        break;
                    case ']':
                        if (stack.size() == 0 || stack.peek() != (OperatorCharactersEnum.BracketPre)) {
                            return new ObjectValuePotsOperator.ObjectValueWrapper(EngineResources.ErrorUnbalancedBrackets);
                        }
                        stack.pop();
                        list.add(new ObjectValueDisplayOperator(OperatorCharactersEnum.BracketAfter));
                        break;
                    case '^':
                        list.add(new ObjectValueDisplayOperator(OperatorCharactersEnum.XOR));
                        break;
                    default:
                        if (specialChar == '|') {
                            if (specialIndex == lastIndex || objectValue.charAt(++specialIndex) != '|') {
                                return new ObjectValuePotsOperator.ObjectValueWrapper(EngineResources.ErrorInvalidCharFollowingOR);
                            }
                            list.add(new ObjectValueDisplayOperator(OperatorCharactersEnum.Or));
                        }
                        break;
                }
            }
            index = specialIndex + 1;
            specialIndex = ObjectValuePotsOperator.IndexOfAny(objectValue, ObjectValuePotsOperator.specialChars, index);
        }

        if (stack.size() > 0) {
            if (stack.peek() == OperatorCharactersEnum.ParathesesPre) {
                return new ObjectValuePotsOperator.ObjectValueWrapper(EngineResources.ErrorUnbalancedParatheses);
            }
            if (stack.peek() == OperatorCharactersEnum.BracketPre) {
                return new ObjectValuePotsOperator.ObjectValueWrapper(EngineResources.ErrorUnbalancedBrackets);
            }
        }
        if (index <= lastIndex) {
            String preObjectValue = objectValue.substring(index, lastIndex - index + 1);
            ObjectValueDisplayOperator.InnerObjectValue innerObjectValue =
                    ObjectValueDisplayOperator.smethod_4(preObjectValue);
            if (innerObjectValue.objectValue != null) {
                return new ObjectValuePotsOperator.ObjectValueWrapper(innerObjectValue.objectValue);
            }
            if (innerObjectValue.NameValueDisplayOperators != null) {
                list.addAll(Arrays.asList(innerObjectValue.NameValueDisplayOperators));
            }
        }
        return new ObjectValuePotsOperator.ObjectValueWrapper(list.toArray(new ObjectValueDisplayOperator[0]));
    }

    public static class ObjectValueWrapper {
        public ObjectValueWrapper(String error) {
            this.ObjectValueDisplayOperators = null;
            this.error = error;
        }

        public ObjectValueWrapper(ObjectValueDisplayOperator[] param0) {
            this.ObjectValueDisplayOperators = param0;
            this.error = null;
        }

        public ObjectValueDisplayOperator[] ObjectValueDisplayOperators;

        public String error;
    }
}
