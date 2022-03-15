package org.licho.brain.concrete.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@AbsBaseElementFunction()
public @interface BaseElementFunction {
    @AliasFor(annotation = AbsBaseElementFunction.class, attribute="NameOverride")
    String value() default "";

    String LocalizedDescriptionResourceManager() default "Simio.Properties.EngineResources";

    @AliasFor(annotation = AbsBaseElementFunction.class)
    String[] Arguments() default {};
}
