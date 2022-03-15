package org.licho.brain.concrete;
/**
 *
 */
public class AbsBaseStepDefinition<T> extends AbsStepDefinition {
    public static SingleInstance definition = new SingleInstance();

    public AbsBaseStepDefinition(String name) {
        super(name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public AbsStepDefinition DefaultDefinition(Class<?> clazz) {
        return (AbsStepDefinition)definition.Instance(clazz);
    }

}
