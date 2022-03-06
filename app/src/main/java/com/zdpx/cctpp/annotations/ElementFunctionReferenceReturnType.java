package com.zdpx.cctpp.annotations;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ElementFunctionReferenceReturnType {

    @AliasFor("Type")
    Class<?> value() default void.class;

    @AliasFor("value")
    Class<?> Type() default void.class;
}
