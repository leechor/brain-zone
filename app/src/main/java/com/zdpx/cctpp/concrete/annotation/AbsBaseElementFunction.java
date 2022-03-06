package com.zdpx.cctpp.concrete.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AbsBaseElementFunction {
    @AliasFor(annotation = BaseElementFunction.class, attribute = "value")
    String NameOverride() default "";

    @AliasFor(annotation = BaseElementFunction.class)
    String[] Arguments() default "";

    String Description() default "";

    boolean Deprecated() default false;

    String LocalizedDescriptionResourceManager() default "";
}
