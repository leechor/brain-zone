package com.zdpx.cctpp.annotations;

public @interface StepCategory {
    String category();

    Class<?> pairedStep() default void.class;
}
