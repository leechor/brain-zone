package org.licho.brain.annotations;

public @interface StepCategory {
    String category();

    Class<?> pairedStep() default void.class;
}
