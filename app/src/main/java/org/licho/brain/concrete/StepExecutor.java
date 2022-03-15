package org.licho.brain.concrete;

import org.licho.brain.IFunction.Action;
import org.licho.brain.enu.ObjectClass;
import org.licho.brain.simioEnums.ElementScope;
import org.licho.brain.simioEnums.TransferFromType;
import org.licho.brain.simioEnums.TransferToType;

/**
 *
 */
public class StepExecutor extends StepWrapper implements IBegin, IStepExecutor {
    public static final String String_0 = "____SUPERSECRETENDLABELTHATISUSEDONLYINTERNALLYANDSTUFF___";
    protected IntelligentObjectDefinition IntelligentObjectDefinition;
    protected String name;
    protected ElementScope elementScope;
    protected String eventProperty;
    protected String tokenClassPropertyName;
    private ObjectClass objectClass;
    private int int_0 = -1;
    private ProcessProperty processProperty;

    public static IBegin create(IntelligentObjectDefinition intelligentObjectDefinition, String name,
                                ElementScope elementScope, String eventProperty, String tokenClassPropertyName) {
        return new StepExecutor(intelligentObjectDefinition, name, elementScope, eventProperty, tokenClassPropertyName);
    }

    private StepExecutor(IntelligentObjectDefinition IntelligentObjectDefinition, String name, ElementScope scope,
                         String eventProperty, String tokenClassPropertyName) {
        this.IntelligentObjectDefinition = IntelligentObjectDefinition;
        this.name = name;
        this.elementScope = scope;
        this.eventProperty = eventProperty;
        this.tokenClassPropertyName = tokenClassPropertyName;
    }

    private StepExecutor(IntelligentObjectDefinition IntelligentObjectDefinition, ObjectClass objectClass, int param2
            , ElementScope elementScope, String tokenClassPropertyName) {
        this.IntelligentObjectDefinition = IntelligentObjectDefinition;
        this.objectClass = objectClass;
        this.int_0 = param2;
        this.elementScope = elementScope;
        this.tokenClassPropertyName = tokenClassPropertyName;
    }

    @Override
    public IStepExecutor imethod_0() {
        return null;
    }

    @Override
    public IStepExecutor add(Action<SubStepWrapper> buildAction) {
        return null;
    }

    @Override
    public IStepExecutor getLabelStep(String label) {
        return null;
    }

    @Override
    public ProcessProperty run() {
        return null;
    }

    @Override
    public IStepExecutor imethod_5(String param0) {
        return null;
    }

    @Override
    public IStepExecutor Assign(String param0, String param1) {
        return null;
    }

    @Override
    public IStepExecutor imethod_6(String param0) {
        return null;
    }

    @Override
    public IStepExecutor Create(String param0) {
        return null;
    }

    @Override
    public IStepExecutor imethod_7() {
        return null;
    }

    @Override
    public IStepExecutor imethod_8(TransferFromType transferFromType, TransferToType transferToType, String param2) {
        return null;
    }

    @Override
    public IStepExecutor imethod_9(TransferFromType transferFromType, TransferToType transferToType, String param2,
                                   String param3) {
        return null;
    }

    @Override
    public IStepExecutor imethod_10(String param0) {
        return null;
    }

    @Override
    public IStepExecutor imethod_11(String param0) {
        return null;
    }

    @Override
    public IStepExecutor imethod_12(String param0) {
        return null;
    }

    @Override
    public IStepExecutor Begin() {
        return this;
    }
}
