package com.zdpx.cctpp.concrete.annotation;

import org.springframework.core.annotation.AliasFor;

/**
 *
 */
@BaseElementFunction
public @interface ElementFunction {
    @AliasFor(annotation = AbsBaseElementFunction.class, attribute = "NameOverride")
    public String value();

    @AliasFor(annotation = AbsBaseElementFunction.class, attribute = "Arguments")
    String[] Arguments() default {};
}
