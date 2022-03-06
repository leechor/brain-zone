package com.zdpx.cctpp.concrete.property;

import com.google.common.base.Strings;
import com.zdpx.cctpp.concrete.cont.EngineResources;

/**
 *
 */
public class NameValidOperator {

    public static String smethod_0(String value) {
        if (Strings.isNullOrEmpty(value)) {
            return "";
        }
        char[] array = value.toCharArray();
        int length = 0;
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            boolean flag = false;
            if (c == '"') {
                NameValidOperator.InnerError innerError = NameValidOperator.smethod_1(value, i);
                if (innerError.Error == null) {
                    for (int j = i; j <= innerError.index; j++) {
                        array[length++] = value.charAt(j);
                    }
                    i = innerError.index;
                    flag = true;
                }
            }
            if (!flag && c != ' ') {
                array[length++] = c;
            }
        }
        return new String(array, 0, length);
    }

    public static NameValidOperator.InnerError smethod_1(String value, int index) {
        int num = value.length() - 1;
        if (index == num) {
            return new NameValidOperator.InnerError(EngineResources.ErrorInvalidStringLiteral);
        }
        int i;
        for (i = index + 1; i <= num; i++) {
            if (value.charAt(i) == '"') {
                if (i + 1 > num || value.charAt(i + 1) != '"') {
                    break;
                }
                i++;
            }
        }
        if (i > num || value.charAt(i) != '"') {
            return new NameValidOperator.InnerError(EngineResources.ErrorInvalidStringLiteral);
        }
        return new NameValidOperator.InnerError(i);
    }

    public static class InnerError {
        public InnerError(String error) {
            this.Error = error;
            this.index = -1;
        }

        public InnerError(int index) {
            this.Error = null;
            this.index = index;
        }

        public final String Error;

        public final int index;
    }

}
