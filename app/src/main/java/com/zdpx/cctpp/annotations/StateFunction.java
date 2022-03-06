package com.zdpx.cctpp.annotations;

/**
 *
 */
public @interface StateFunction {
    String NameOverride();
    String[] Arguments() default{};
    String Description();
}
