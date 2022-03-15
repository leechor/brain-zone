package org.licho.brain.concrete;

import org.licho.brain.enu.UnitType;

import java.lang.reflect.Method;

/**
 *
 */
public class FunctionInfoResult {
    public static org.licho.brain.concrete.AbsDefinition.FunctionInfo Instance = new AbsDefinition.FunctionInfo();
    public ExpressionHandler ExpressionHandler;

    public ArgsExpressionHandler ArgsExpressionHandler;

    public String[] arguments;

    public String description;

    public boolean deprecated;

    public UnitType unitType;

    public boolean dynamicUnit;

    public boolean populationFunction;

    public boolean elementFunction;

    public boolean resourceFunction;

    public AbsDefinition AbsDefinition;

    public Class<?> ReturnType;

    public Method methodInfo;

    public Class<?> classType;
}
