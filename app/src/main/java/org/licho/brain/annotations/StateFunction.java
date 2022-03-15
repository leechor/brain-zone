package org.licho.brain.annotations;

/**
 *
 */
public @interface StateFunction {
    String NameOverride();
    String[] Arguments() default{};
    String Description();
}
